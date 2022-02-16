package com.setpoint.reservapistes.modules.shared.core.bus.infrastructure;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.BusHandlerRegister;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.command.CommandHandler;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.event.EventHandler;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.query.QueryHandler;
import com.setpoint.reservapistes.modules.shared.interfaces.Command;
import com.setpoint.reservapistes.modules.shared.interfaces.Event;
import com.setpoint.reservapistes.modules.shared.interfaces.Query;
import com.setpoint.reservapistes.modules.shared.interfaces.Response;

import java.util.*;

public class BusHandlerRegisterInMemory implements BusHandlerRegister {

    private final Map<String, QueryHandler<? extends Query, ? extends Response>> queryHandlerMap = new HashMap<>();
    private final Map<String, CommandHandler<? extends Command>> commandHandlerMap = new HashMap<>();
    private final Map<String, List<EventHandler<? extends Event>>> eventHandlerMap = new HashMap<>();

    @Override
    public void register(final Class<? extends Query> clazz, final QueryHandler<? extends Query, ? extends Response> queryHandler) {
        this.queryHandlerMap.put(clazz.getName(), queryHandler);
    }

    @Override
    public void register(final Class<? extends Command> clazz, final CommandHandler<? extends Command> commandHandler) {
        this.commandHandlerMap.put(clazz.getName(), commandHandler);
    }

    @Override
    public void register(final Class<? extends Event> clazz, final List<EventHandler<? extends Event>> eventHandlers) {
        final List<EventHandler<? extends Event>> addHandlers = new ArrayList<>(eventHandlers);

        if (this.eventHandlerMap.containsKey(clazz.getName())) {
            addHandlers.addAll(this.eventHandlerMap.get(clazz.getName()));
        }

        this.eventHandlerMap.put(clazz.getName(), addHandlers);
    }

    @Override
    public Optional<QueryHandler<? extends Query, ? extends Response>> search(final Query query) {
        return Optional.ofNullable(queryHandlerMap.get(query.getClass().getInterfaces()[0].getName()));
    }

    @Override
    public Optional<CommandHandler<? extends Command>> search(final Command command) {
        return Optional.ofNullable(commandHandlerMap.get(command.getClass().getInterfaces()[0].getName()));
    }

    @Override
    public List<EventHandler<? extends Event>> search(final Event event) {
        return eventHandlerMap.get(event.getClass().getInterfaces()[0].getName());
    }


}

