package com.setpoint.reservapistes.modules.shared.core.bus.domain.event;

import com.setpoint.reservapistes.modules.shared.interfaces.Event;

import java.util.List;

public interface EventBus {
    void publish(final List<Event> events);

    void publish(final Event event);

    void dispatch(final Event event);
}
