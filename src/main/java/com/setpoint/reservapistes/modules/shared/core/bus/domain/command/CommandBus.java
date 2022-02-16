package com.setpoint.reservapistes.modules.shared.core.bus.domain.command;

import com.setpoint.reservapistes.modules.shared.interfaces.Command;

public interface CommandBus {
    void dispatch(final Command command);
}
