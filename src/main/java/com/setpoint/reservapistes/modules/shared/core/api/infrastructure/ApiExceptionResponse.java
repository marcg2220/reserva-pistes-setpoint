package com.setpoint.reservapistes.modules.shared.core.api.infrastructure;

import com.setpoint.reservapistes.modules.shared.core.exceptions.domain.DomainException;
import com.setpoint.reservapistes.modules.shared.interfaces.Response;
import io.swagger.v3.oas.annotations.media.Schema;

public interface ApiExceptionResponse extends Response {

    @Schema(name = "code", description = "Codi de l'excepció")
    String getCode();

    @Schema(name = "type", description = "Tipus de l'excepció", implementation = DomainException.DomainExceptionCategory.class)
    String getType();

    @Schema(name = "api_message", description = "Missatge per a mostrar a la API")
    String getApiMessage();

    @Schema(name = "ui_message", description = "Missatge per mostrar a la UI")
    String getUiMessage();
}
