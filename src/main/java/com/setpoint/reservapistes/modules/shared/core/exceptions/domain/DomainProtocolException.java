package com.setpoint.reservapistes.modules.shared.core.exceptions.domain;

public abstract class DomainProtocolException extends DomainException{

    @Override
    public DomainExceptionCategory category() {
        return DomainExceptionCategory.PROTOCOL;
    }
}
