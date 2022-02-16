package com.setpoint.reservapistes.modules.shared.core.identifier;

import com.setpoint.reservapistes.modules.shared.core.identifier.domain.IdentifierGenerator;
import com.setpoint.reservapistes.modules.shared.core.identifier.infrastructure.IdentifierJavaGenerator;
import org.springframework.context.annotation.Bean;

public abstract class IdentifierModuleConfigurationAdapter {
    @Bean
    public IdentifierGenerator identifierGenerator() {
        return new IdentifierJavaGenerator();
    }
}
