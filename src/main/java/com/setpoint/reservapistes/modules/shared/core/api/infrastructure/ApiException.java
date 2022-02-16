package com.setpoint.reservapistes.modules.shared.core.api.infrastructure;

import com.setpoint.reservapistes.modules.shared.core.exceptions.domain.DomainException;
import com.setpoint.reservapistes.modules.shared.core.logger.domain.Logger;
import com.setpoint.reservapistes.modules.shared.core.logger.infrastructure.LoggerFactory;
import com.setpoint.reservapistes.modules.shared.interfaces.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiException implements ApiExceptionResponse {

    private static final Logger loger = LoggerFactory.getLogger(ApiException.class);

    public static final String UNHANDLED_EXCEPTION = "00001";
    private final String code;
    private final String type;
    private final String apiMessage;
    private final String uiMessage;

    public ApiException(String code, String type, String apiMessage, String uiMessage) {
        this.code = code;
        this.type = type;
        this.apiMessage = apiMessage;
        this.uiMessage = uiMessage;
    }

    public static ResponseEntity<Response> response(Exception e) {
        return ResponseEntity
                .status(ApiException.status(e))
                .body(ApiException.from(e));
    }

    public static HttpStatus status(Exception exception) {

        if (exception instanceof DomainException) {
            return HttpStatus.BAD_REQUEST;

        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public static ApiException from(DomainException domainException) {
        return new ApiException(domainException.code(), domainException.type(), domainException.apiMessage(), domainException.uiMessage());
    }

    public static ApiException from(Exception exception) {
        if (exception instanceof DomainException) {
            loger.warn(exception.getMessage());
            return ApiException.from((DomainException) exception);
        }
        loger.error(exception.getMessage(), exception);
        return new ApiException(UNHANDLED_EXCEPTION, "INTERNAL", exception.getMessage(), "");
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getApiMessage() {
        return apiMessage;
    }

    public String getUiMessage() {
        return uiMessage;
    }
}
