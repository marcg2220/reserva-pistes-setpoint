package com.setpoint.reservapistes.modules.usuaris.domain;

import com.setpoint.reservapistes.modules.shared.core.identifier.domain.Identifier;

public class UsuariId extends Identifier {
    public UsuariId(final String uuid) { super(uuid); }

    public static UsuariId create(final String valor) {
        return new UsuariId(valor);
    }
}
