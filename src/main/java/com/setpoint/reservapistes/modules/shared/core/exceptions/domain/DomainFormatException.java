package com.setpoint.reservapistes.modules.shared.core.exceptions.domain;

public abstract class DomainFormatException extends DomainException {

    @Override
    public DomainExceptionCategory category() {
        return DomainExceptionCategory.FORMAT;
    }
}
