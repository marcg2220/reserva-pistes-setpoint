package com.setpoint.reservapistes.modules.shared.core;

import com.setpoint.reservapistes.modules.shared.core.bus.BusModuleConfigurationAdapter;
import com.setpoint.reservapistes.modules.shared.core.identifier.IdentifierModuleConfigurationAdapter;
import org.springframework.context.annotation.Configuration;

public abstract class SharedModuleConfigurationAdapter {

    @Configuration
    public static class BusModuleConfiguration extends BusModuleConfigurationAdapter {
    }

    @Configuration
    public static class IdentifierModuleConfiguration extends IdentifierModuleConfigurationAdapter {
        //Nothing to configure
    }
}
