package com.setpoint.reservapistes.config.core.exceptions.domain;

public abstract class DomainProtocolException extends DomainException{

    @Override
    public DomainExceptionCategory category() {
        return DomainExceptionCategory.PROTOCOL;
    }
}
