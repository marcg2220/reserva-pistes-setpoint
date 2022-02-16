package com.setpoint.reservapistes.modules.usuaris.domain.nivell;

import com.setpoint.reservapistes.modules.shared.core.exceptions.domain.DomainFormatException;

public class NivellInvalid extends DomainFormatException {

    @Override
    public String code() {
        return "INVALID-NIVELL";
    }

    @Override
    public String apiMessage() {
        return "Field nivell not match with a valid value";
    }

    @Override
    public String uiMessage() {
        return "El camp nivell no correspon a un valor vàlid.";
    }
}
