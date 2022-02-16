package com.setpoint.reservapistes.modules.shared.core.repositories.infrastructure;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.event.AggregateRoot;
import com.setpoint.reservapistes.modules.shared.interfaces.Response;

/**
 * Abstract class to define the custom mappings used on domain repository adapter
 *
 * @param <A> class type of the aggregate root
 * @param <E> class type of the entity that aggregate use to persist
 */
public abstract class DomainMapperAdapter<A extends AggregateRoot, E> implements DomainMapper<A, E> {

    @Override
    public final A mapToAggregate(final E entity) {
        final A aggregate = mapToAggregateTemplate(entity);
        aggregate.pullEvents();
        return aggregate;
    }

    @Override
    public final E mapToEntity(final A aggregate) {
        return this.mapToEntityTemplate(aggregate);
    }

    @Override
    public Response mapToResponse(final E entity) {
        return this.mapToResponseTemplate(entity);
    }

    /**
     * Maps the defined entity to aggregate
     *
     * @param entity to map
     * @return aggregate mapped with the entity
     */
    protected abstract A mapToAggregateTemplate(final E entity);

    /**
     * Maps the defined aggregate to entity
     *
     * @param aggregate to map
     * @return entity mapped with the aggregate
     */
    protected abstract E mapToEntityTemplate(final A aggregate);

    /**
     * Maps the defined entity to response
     *
     * @param entity to map
     * @return response mapped with the entity
     */
    protected abstract Response mapToResponseTemplate(final E entity);

}
