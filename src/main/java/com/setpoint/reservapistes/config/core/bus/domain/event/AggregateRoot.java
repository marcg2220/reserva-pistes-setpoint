package com.setpoint.reservapistes.config.core.bus.domain.event;

import jdk.jfr.Event;

import java.util.ArrayList;
import java.util.List;

public class AggregateRoot {
    private final List<Event> events = new ArrayList<>();

    public final List<Event> pullEvents() {
        final List<Event> events = new ArrayList<>(this.events);
        this.events.clear();

        return events;
    }

    protected final void recordEvent(final Event event) { this.events.add(event); }
}
