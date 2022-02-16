package com.setpoint.reservapistes.modules.shared.core.bus.domain.query;

import com.setpoint.reservapistes.modules.shared.core.exceptions.domain.DomainProtocolException;

public class QueryHandlerNotFound extends DomainProtocolException {

    @Override
    public String code() {
        return "UNDEFINED";
    }

    @Override
    public String apiMessage() {
        return "Handler was not foun to dispatch the query";
    }
}
