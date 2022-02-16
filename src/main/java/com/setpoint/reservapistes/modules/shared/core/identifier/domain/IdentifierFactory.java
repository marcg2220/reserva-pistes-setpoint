package com.setpoint.reservapistes.modules.shared.core.identifier.domain;

public class IdentifierFactory {

    private IdentifierFactory() { throw new IllegalStateException("Factory class"); }

    public static Identifier create(String uuid) { return new Identifier(uuid); }
}
