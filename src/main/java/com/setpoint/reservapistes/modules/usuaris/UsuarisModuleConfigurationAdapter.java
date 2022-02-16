package com.setpoint.reservapistes.modules.usuaris;

import com.setpoint.reservapistes.modules.usuaris.application.UsuariCreateHandler;
import com.setpoint.reservapistes.modules.usuaris.domain.UsuariRepository;
import org.springframework.context.annotation.Bean;

public abstract class UsuarisModuleConfigurationAdapter {

    @Bean
    public UsuariCreateHandler usuariCreateHandler() {
        return new UsuariCreateHandler(usuariRepository());
    }

    protected abstract UsuariRepository usuariRepository();

}
