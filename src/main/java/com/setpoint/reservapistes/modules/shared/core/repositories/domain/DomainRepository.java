package com.setpoint.reservapistes.modules.shared.core.repositories.domain;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.event.AggregateRoot;
import com.setpoint.reservapistes.modules.shared.core.repositories.domain.filters.Filters;
import com.setpoint.reservapistes.modules.shared.core.repositories.domain.page.Page;
import com.setpoint.reservapistes.modules.shared.core.repositories.domain.page.Pagination;
import com.setpoint.reservapistes.modules.shared.core.repositories.domain.sort.Sorting;
import com.setpoint.reservapistes.modules.shared.interfaces.Response;

import java.util.Optional;

public interface DomainRepository<D extends AggregateRoot> {
    /**
     * Method to find an aggregate using the id field
     *
     * @param id of the aggregate root
     * @return An optional of the aggregate if found by id or an Optional empty if not
     */
    Optional<D> find(final String id);

    /**
     * Method to find an aggregate using the id field returning the specified interface
     *
     * @param id    of the aggregate root
     * @param clazz interface matching entity parameters
     * @param <R>   interface that represents a projection of entity mapped
     * @return An optional of the interface if found by id or an Optional empty if not
     */
    <R extends Response> Optional<R> find(final String id, final Class<R> clazz);

    /**
     * Method to search the aggregates matching filters and page required
     *
     * @param pagination domain object with page number and size to apply on the search
     * @param filters    domain object with the filters to apply on the search
     * @param <R>        interface that represents a projection of entity mapped
     * @return A page of the default response specified
     */
    <R extends Response> Page<R> search(final Pagination pagination, final Filters filters);

    /**
     * Method to search the aggregates matching filters and page and sorting required
     * @param pagination domain object with page number and size to apply on the search
     * @param filters    domain object with the filters to apply on the search
     * @param sorting    domain object with sorting to apply on the search
     * @param <R>        interface that represents a projection of entity mapped
     * @return A page of the default response specified
     */
    <R extends Response> Page<R> search(final Pagination pagination, final Filters filters, final Sorting sorting);

    /**
     * Method to find if exist an aggregate by id
     *
     * @param id of the aggregate root
     * @return true if exist an aggregate matching with this id
     */
    boolean exists(final String id);

    /**
     * Method to save an aggregate
     *
     * @param aggregate to save
     */
    void save(final D aggregate);

    /**
     * Method to delete an aggregate by the id
     *
     * @param id of aggregate to delete
     */
    void delete(final String id);
}
