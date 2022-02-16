package com.setpoint.reservapistes.modules.shared.core.bus.infrastructure.command;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.BusHandlerRegister;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.command.CommandBus;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.command.CommandHandler;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.command.CommandHandlerNotFound;
import com.setpoint.reservapistes.modules.shared.core.logger.domain.Logger;
import com.setpoint.reservapistes.modules.shared.core.logger.infrastructure.LoggerFactory;
import com.setpoint.reservapistes.modules.shared.interfaces.Command;

import java.util.Optional;

public class CommandBusInMemory implements CommandBus {

    private final Logger logger = LoggerFactory.getLogger(CommandBusInMemory.class);

    private final BusHandlerRegister busHandlerRegister;

    public CommandBusInMemory(final BusHandlerRegister busHandlerRegister) {
        this.busHandlerRegister = busHandlerRegister;
    }

    @Override
    public void dispatch(final Command command) {
        final Optional<CommandHandler<?>> oCommandHandler = busHandlerRegister.search(command);
        final String commandName = command.getClass().getInterfaces()[0].getSimpleName();

        if (oCommandHandler.isPresent()) {
            logger.info(String.format("Dispatching %s to handler", commandName));
            final CommandHandler commandHandler = oCommandHandler.get();
            commandHandler.handle(command);
        } else {
            logger.warn(String.format("Handler was not found to dispatch %s", commandName));
            throw new CommandHandlerNotFound();
        }
    }
}
