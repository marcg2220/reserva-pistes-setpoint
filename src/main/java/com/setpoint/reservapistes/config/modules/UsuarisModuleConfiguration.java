package com.setpoint.reservapistes.config.modules;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.BusHandlerRegister;
import com.setpoint.reservapistes.modules.shared.interfaces.usuaris.commands.UsuariCreateCommand;
import com.setpoint.reservapistes.modules.usuaris.UsuarisModuleConfigurationAdapter;
import com.setpoint.reservapistes.modules.usuaris.domain.UsuariRepository;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class UsuarisModuleConfiguration extends UsuarisModuleConfigurationAdapter {
    private BusHandlerRegister busHandlerRegister;
    private UsuariRepository usuariRepository;

    public UsuarisModuleConfiguration(final BusHandlerRegister busHandlerRegister, final UsuariRepository usuariRepository) {
        this.busHandlerRegister = busHandlerRegister;
        this.usuariRepository = usuariRepository;
    }

    @PostConstruct
    private void registerHandlers() {
        busHandlerRegister.register(UsuariCreateCommand.class, usuariCreateHandler());
    }

    @Override
    protected UsuariRepository usuariRepository() {
        return usuariRepository;
    }

}
