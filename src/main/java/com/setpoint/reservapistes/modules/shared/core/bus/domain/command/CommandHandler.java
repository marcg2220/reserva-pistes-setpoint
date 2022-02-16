package com.setpoint.reservapistes.modules.shared.core.bus.domain.command;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.Handler;
import com.setpoint.reservapistes.modules.shared.interfaces.Command;

public interface CommandHandler<C extends Command> extends Handler {
    void handle(final C command);
}
