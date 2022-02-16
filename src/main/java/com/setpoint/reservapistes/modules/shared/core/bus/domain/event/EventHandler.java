package com.setpoint.reservapistes.modules.shared.core.bus.domain.event;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.Handler;
import com.setpoint.reservapistes.modules.shared.interfaces.Event;

public interface EventHandler<E extends Event> extends Handler {
    void handle(final E event);
}
