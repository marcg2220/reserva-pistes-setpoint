package com.setpoint.reservapistes.modules.shared.core.identifier.infrastructure;

import com.setpoint.reservapistes.modules.shared.core.identifier.domain.Identifier;
import com.setpoint.reservapistes.modules.shared.core.identifier.domain.IdentifierFactory;
import com.setpoint.reservapistes.modules.shared.core.identifier.domain.IdentifierGenerator;

import java.util.UUID;

public class IdentifierJavaGenerator implements IdentifierGenerator {
    @Override
    public Identifier generate() {
        return IdentifierFactory.create(UUID.randomUUID().toString());
    }
}
