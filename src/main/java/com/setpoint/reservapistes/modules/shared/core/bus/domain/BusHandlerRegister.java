package com.setpoint.reservapistes.modules.shared.core.bus.domain;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.command.CommandHandler;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.event.EventHandler;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.query.QueryHandler;
import com.setpoint.reservapistes.modules.shared.interfaces.Command;
import com.setpoint.reservapistes.modules.shared.interfaces.Event;
import com.setpoint.reservapistes.modules.shared.interfaces.Query;
import com.setpoint.reservapistes.modules.shared.interfaces.Response;

import java.util.List;
import java.util.Optional;

public interface BusHandlerRegister {

    void register(final Class<? extends Query> clazz,
                  final QueryHandler<? extends Query, ? extends Response> queryHandler
    );

    void register(final Class<? extends Command> clazz,
                  final CommandHandler<? extends Command> commandHandler
    );

    void register(final Class<? extends Event> clazz,
                  final List<EventHandler<? extends Event>> eventHandlers
    );

    Optional<QueryHandler<? extends Query, ? extends Response>> search(final Query query);

    Optional<CommandHandler<? extends Command>> search(final Command command);

    List<EventHandler<? extends Event>> search(final Event event);
}
