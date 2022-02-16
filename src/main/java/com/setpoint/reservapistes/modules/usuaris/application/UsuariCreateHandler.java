package com.setpoint.reservapistes.modules.usuaris.application;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.command.CommandHandler;
import com.setpoint.reservapistes.modules.shared.interfaces.usuaris.commands.UsuariCreateCommand;
import com.setpoint.reservapistes.modules.usuaris.domain.UsuariRepository;

public class UsuariCreateHandler implements CommandHandler<UsuariCreateCommand> {

    private final UsuariRepository usuariRepository;

    public UsuariCreateHandler(UsuariRepository usuariRepository) {
        this.usuariRepository = usuariRepository;
    }

    @Override
    public void handle(UsuariCreateCommand command) {

    }
}
