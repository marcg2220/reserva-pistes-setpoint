package com.setpoint.reservapistes.modules.shared.core.bus.infrastructure.event;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.BusHandlerRegister;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.event.EventBus;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.event.EventHandler;
import com.setpoint.reservapistes.modules.shared.core.logger.domain.Logger;
import com.setpoint.reservapistes.modules.shared.core.logger.infrastructure.LoggerFactory;
import com.setpoint.reservapistes.modules.shared.interfaces.Event;

import java.util.List;

public class EventBusInMemory implements EventBus {

    private final Logger logger = LoggerFactory.getLogger(EventBusInMemory.class);

    private final BusHandlerRegister busHandlerRegister;

    public EventBusInMemory(final BusHandlerRegister busHandlerRegister) {
        this.busHandlerRegister = busHandlerRegister;
    }

    @Override
    public void publish(final List<Event> events) {
        events.forEach(this::publish);
    }

    @Override
    public void publish(final Event event) {
        // Forwards dispatch cause no broker to publish.
        this.dispatch(event);
    }

    @Override
    public void dispatch(Event event) {
        final List<EventHandler<? extends Event>> eventHandlers = busHandlerRegister.search(event);
        final String eventName = event.getClass().getInterfaces()[0].getSimpleName();

        if (eventHandlers != null) {
            logger.info(String.format("Dispatching %s to handlers", eventName));
            for (EventHandler eventHandler : eventHandlers) {
                eventHandler.handle(event);
            }
        }

    }
}
