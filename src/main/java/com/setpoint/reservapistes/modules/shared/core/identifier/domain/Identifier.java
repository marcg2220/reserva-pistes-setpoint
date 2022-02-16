package com.setpoint.reservapistes.modules.shared.core.identifier.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Identifier implements Serializable {
    private String valor;

    protected Identifier(String uuid) {
        validUuid(uuid);
        this.valor = uuid;
    }

    public String valor() {
        return valor;
    }

    private void validUuid(final String uuid) {
        try {
            UUID.fromString(uuid);
        } catch (Exception e) {
            throw new IdentifierInvalidValueException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identifier that = (Identifier) o;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
