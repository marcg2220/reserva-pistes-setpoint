package com.setpoint.reservapistes.modules.shared.core.bus.domain.command;

import com.setpoint.reservapistes.modules.shared.core.exceptions.domain.DomainProtocolException;

public class CommandHandlerNotFound extends DomainProtocolException {

    @Override
    public String code() {
        return "UNDEFINED";
    }

    @Override
    public String apiMessage() {
        return "Handler was not found to dispatch the command";
    }
}
