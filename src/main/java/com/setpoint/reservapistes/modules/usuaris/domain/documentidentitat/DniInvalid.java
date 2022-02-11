package com.setpoint.reservapistes.modules.usuaris.domain.documentidentitat;

import com.setpoint.reservapistes.config.core.exceptions.domain.DomainFormatException;

public class DniInvalid extends DomainFormatException {

    @Override
    public String code() {
        return "00002";
    }

    @Override
    public String apiMessage() {
        return "Invalid DNI value";
    }

    @Override
    public String uiMessage() {
        return "El DNI que heu introduit no és valid.";
    }
}
