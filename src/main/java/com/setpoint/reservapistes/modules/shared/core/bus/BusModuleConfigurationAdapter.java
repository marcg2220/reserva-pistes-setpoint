package com.setpoint.reservapistes.modules.shared.core.bus;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.BusHandlerRegister;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.command.CommandBus;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.event.EventBus;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.query.QueryBus;
import com.setpoint.reservapistes.modules.shared.core.bus.infrastructure.BusHandlerRegisterInMemory;
import com.setpoint.reservapistes.modules.shared.core.bus.infrastructure.command.CommandBusInMemory;
import com.setpoint.reservapistes.modules.shared.core.bus.infrastructure.event.EventBusInMemory;
import com.setpoint.reservapistes.modules.shared.core.bus.infrastructure.query.QueryBusInMemory;
import org.springframework.context.annotation.Bean;

public abstract class BusModuleConfigurationAdapter {

    @Bean
    public BusHandlerRegister busHandlerRegister() {
        return new BusHandlerRegisterInMemory();
    }

    @Bean
    public QueryBus queryBus(final BusHandlerRegister busHandlerRegister) {
        return new QueryBusInMemory(busHandlerRegister);
    }

    @Bean
    public CommandBus commandBus(final BusHandlerRegister busHandlerRegister) {
        return new CommandBusInMemory(busHandlerRegister);
    }

    @Bean
    public EventBus eventBus(final BusHandlerRegister busHandlerRegister) {
        return new EventBusInMemory(busHandlerRegister);
    }

}
