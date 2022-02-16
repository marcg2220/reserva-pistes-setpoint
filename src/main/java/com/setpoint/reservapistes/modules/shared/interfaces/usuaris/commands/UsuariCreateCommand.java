package com.setpoint.reservapistes.modules.shared.interfaces.usuaris.commands;

import com.setpoint.reservapistes.modules.shared.interfaces.Command;

public interface UsuariCreateCommand extends Command {

    String getId();

    String getNom();

    String getCognoms();

    String getDni();

    String getEmail();

    String getNivell();
}
