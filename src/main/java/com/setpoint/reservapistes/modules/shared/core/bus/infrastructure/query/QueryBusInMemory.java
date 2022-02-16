package com.setpoint.reservapistes.modules.shared.core.bus.infrastructure.query;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.BusHandlerRegister;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.query.QueryBus;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.query.QueryHandler;
import com.setpoint.reservapistes.modules.shared.core.bus.domain.query.QueryHandlerNotFound;
import com.setpoint.reservapistes.modules.shared.core.logger.domain.Logger;
import com.setpoint.reservapistes.modules.shared.core.logger.infrastructure.LoggerFactory;
import com.setpoint.reservapistes.modules.shared.interfaces.Query;
import com.setpoint.reservapistes.modules.shared.interfaces.Response;

import java.util.Optional;

public class QueryBusInMemory implements QueryBus {

    private final Logger logger = LoggerFactory.getLogger(QueryBusInMemory.class);

    private final BusHandlerRegister busHandlerRegister;

    public QueryBusInMemory(final BusHandlerRegister busHandlerRegister) {
        this.busHandlerRegister = busHandlerRegister;
    }

    @Override
    public <R extends Response> R ask(final Query query) {
        final Optional<QueryHandler<?, ?>> oQueryHandler = busHandlerRegister.search(query);
        final String queryName = query.getClass().getInterfaces()[0].getSimpleName();

        if (oQueryHandler.isPresent()) {
            logger.info(String.format("Dispatching %s to handler", queryName));
            final QueryHandler queryHandler = oQueryHandler.get();
            return (R) queryHandler.handle(query);
        }

        logger.warn(String.format("Handler was not found to dispatch %s", queryName));
        throw new QueryHandlerNotFound();
    }
}
