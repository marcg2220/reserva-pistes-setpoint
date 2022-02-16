package com.setpoint.reservapistes.modules.shared.core.exceptions.domain;

public abstract class DomainBusinessException extends DomainException {

    @Override
    public DomainExceptionCategory category() {
        return DomainExceptionCategory.BUSINESS;
    }
}
