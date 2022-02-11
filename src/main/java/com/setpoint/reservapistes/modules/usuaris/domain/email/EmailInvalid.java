package com.setpoint.reservapistes.modules.usuaris.domain.email;

import com.setpoint.reservapistes.config.core.exceptions.domain.DomainFormatException;

public class EmailInvalid extends DomainFormatException {

    @Override
    public String code() {
        return "00001";
    }

    @Override
    public String apiMessage() {
        return "Invalid email value";
    }

    @Override
    public String uiMessage() {
        return "El correu electrònic no té un format correcte.";
    }
}
