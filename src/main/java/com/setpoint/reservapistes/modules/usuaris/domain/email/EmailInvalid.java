package com.setpoint.reservapistes.modules.usuaris.domain.email;

import com.setpoint.reservapistes.modules.shared.core.exceptions.domain.DomainFormatException;

public class EmailInvalid extends DomainFormatException {

    @Override
    public String code() {
        return "INVALID-EMAIL";
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
