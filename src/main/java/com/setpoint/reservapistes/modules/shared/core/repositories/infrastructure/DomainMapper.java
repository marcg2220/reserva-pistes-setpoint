package com.setpoint.reservapistes.modules.shared.core.repositories.infrastructure;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.event.AggregateRoot;
import com.setpoint.reservapistes.modules.shared.interfaces.Response;

/**
 * Class with definition of domain mapper method responsibilities
 *
 * @param <A> class type of the aggregate
 * @param <E> class type of the entity
 */
public interface DomainMapper<A extends AggregateRoot, E> {

    /**
     * Maps the defined entity to aggregate
     *
     * @param entity to map
     * @return aggregate mapped with the entity
     */
    A mapToAggregate(final E entity);

    /**
     * Maps the defined aggregate to entity
     *
     * @param aggregate to map
     * @return entity mapped with the aggregate
     */
    E mapToEntity(final A aggregate);

    /**
     * Maps the defined entity to search response
     *
     * @param entity to map
     * @return response mapped with the entity
     */
    Response mapToResponse(final E entity);

}
