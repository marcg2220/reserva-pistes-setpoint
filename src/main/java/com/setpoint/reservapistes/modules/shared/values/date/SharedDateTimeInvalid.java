package com.setpoint.reservapistes.modules.shared.values.date;

import com.setpoint.reservapistes.modules.shared.core.exceptions.domain.DomainFormatException;

public class SharedDateTimeInvalid extends DomainFormatException {
    @Override
    public String code() {
        return "INVALID_DATE";
    }

    @Override
    public String apiMessage() {
        return "Invalid date time format";
    }
}
