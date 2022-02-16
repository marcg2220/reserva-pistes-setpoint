package com.setpoint.reservapistes.modules.shared.core.identifier.domain;

import com.setpoint.reservapistes.modules.shared.core.exceptions.domain.DomainProtocolException;

public class IdentifierInvalidValueException extends DomainProtocolException {
    @Override
    public String code() {
        return "INVALID-UUID";
    }

    @Override
    public String apiMessage() {
        return "Invalid UUID value";
    }

    @Override
    public String uiMessage() {
        return "L'identificador no és correcte.";
    }
}
