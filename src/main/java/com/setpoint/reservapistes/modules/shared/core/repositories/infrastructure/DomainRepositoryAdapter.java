package com.setpoint.reservapistes.modules.shared.core.repositories.infrastructure;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.event.AggregateRoot;
import com.setpoint.reservapistes.modules.shared.core.repositories.domain.DomainRepository;
import com.setpoint.reservapistes.modules.shared.core.repositories.domain.filters.Filters;
import com.setpoint.reservapistes.modules.shared.core.repositories.domain.page.Page;
import com.setpoint.reservapistes.modules.shared.core.repositories.domain.page.Pagination;
import com.setpoint.reservapistes.modules.shared.core.repositories.domain.sort.Sorting;
import com.setpoint.reservapistes.modules.shared.interfaces.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Expression;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Automatically generates the implementation of default CRUD methods for any aggregate root
 *
 * @param <A> class type of some aggregate root to implement
 * @param <E> class type of entity to implement
 */
public abstract class DomainRepositoryAdapter<A extends AggregateRoot, E> implements DomainRepository<A> {

    /**
     * Dependency of required Jpa repository
     *
     * @return a Jpa repository matching the parametrized entity
     */
    protected abstract JpaRepository<E, String> repository();

    /**
     * Dependency of required Jpa specification executor
     *
     * @return a Jpa specification repository matching the parametrized entity
     */
    protected abstract JpaSpecificationExecutor<E> specificationExecutor();

    /**
     * Dependency of required Domain mapper
     *
     * @return a Domain mapper matching the parametrized aggregate,search response and entity
     */
    protected abstract DomainMapper<A, E> domainMapper();


    /**
     * Method used to find aggregates and mapping to specified class
     *
     * @param id    of the aggregate
     * @param clazz interface matching fields with the entity
     * @param <R>   type of the interface to be returned
     * @return an interface representing a projection of the entity
     */
    protected abstract <R extends Response> Optional<R> findTemplate(final String id, final Class<R> clazz);

    /**
     * Method used on search to apply custom specification using domain filters
     *
     * @param filters domain filters to apply on the specification
     * @return a Jpa specification applied on search method
     */
    protected abstract List<Specification<E>> specificationFromFiltersTemplate(final Filters filters);


    @Override
    public final Optional<A> find(final String id) {
        return this.repository()
                .findById(id)
                .map(this::mapToAggregateWrapper);
    }

    @Override
    public final <R extends Response> Optional<R> find(final String id, final Class<R> clazz) {
        return this.findTemplate(id, clazz);
    }

    @Override
    public final Page<Response> search(final Pagination pagination, final Filters filters) {
        return this.mapToPage(
                this.specificationExecutor()
                        .findAll(
                                specificationFromFilters(filters),
                                pageableFromPagination(pagination)
                        ),
                this::mapToResponseWrapper
        );
    }

    @Override
    public final Page<Response> search(final Pagination pagination, final Filters filters, final Sorting sorting) {
        return this.mapToPage(
                this.specificationExecutor()
                        .findAll(
                                specificationFromFilters(filters),
                                pageableAndSorting(pagination, sorting)
                        ),
                this::mapToResponseWrapper
        );
    }



    @Override
    public final boolean exists(final String id) {
        return this.repository()
                .existsById(id);
    }

    @Override
    public final void save(final A aggregate) {
        this.repository()
                .save(mapToEntityWrapper(aggregate));
    }

    @Override
    public final void delete(final String id) {
        this.repository()
                .deleteById(id);
    }

    public void saveAll(final Set<A> aggregateSet) {
        this.repository().saveAll(aggregateSet.stream()
                .map(this.domainMapper()::mapToEntity)
                .collect(Collectors.toSet()));
    }


    protected final A mapToAggregateWrapper(final E entity) {
        return this.domainMapper()
                .mapToAggregate(entity);
    }

    protected final E mapToEntityWrapper(final A aggregate) {
        return this.domainMapper()
                .mapToEntity(aggregate);
    }

    protected final Response mapToResponseWrapper(final E entity) {
        return this.domainMapper()
                .mapToResponse(entity);
    }

    private <G extends Response> List<G> mapToList(final List<E> list, final Function<E, G> mappingFunction) {
        return list.stream().map(mappingFunction).collect(Collectors.toList());
    }

    protected  <R extends Response> Page<R> mapToPage(
            final org.springframework.data.domain.Page<E> page,
            final Function<E, R> mappingFunction
    ) {
        return new Page<>() {
            @Override
            public List<R> getContent() {
                return mapToList(page.getContent(), mappingFunction);
            }

            @Override
            public Integer getTotalPages() {
                return page.getTotalPages();
            }

            @Override
            public Long getTotalElements() {
                return page.getTotalElements();
            }

            @Override
            public Boolean isLast() {
                return page.isLast();
            }

            @Override
            public Integer getSize() {
                return page.getSize();
            }

            @Override
            public Boolean isFirst() {
                return page.isFirst();
            }

            @Override
            public Integer getNumberOfElements() {
                return page.getNumberOfElements();
            }
        };
    }

    protected Pageable pageableFromPagination(final Pagination pagination) {
        if (pagination.isUnpagged()) {
            return Pageable.unpaged();
        }

        return PageRequest.of(pagination.page().intValue(), pagination.size().intValue());
    }

    protected Pageable pageableAndSorting(final Pagination pagination, final Sorting sorting) {
        final Sort.Direction direction = Sort.Direction.fromString(sorting.getDirection().name());
        final Sort sort1 = Sort.by(direction, isValidField(sorting.getField()) ? sorting.getField() : Sorting.DEFAULT_FIELD);
        return PageRequest.of(pagination.page().intValue(), pagination.size().intValue(), sort1);
    }

    private boolean isValidField(final String field) {
        return validFields().contains(field);
    }

    private final List<String> validFields() {
        List<String> validFields = validFieldsTemplate(new ArrayList<>());
        return validFields == null ? new ArrayList<>() : validFields;
    }

    protected List<String> validFieldsTemplate(final List<String> fields) {
        return fields;
    }

    protected Pageable pageableFromPagination(final Pagination pagination, final Sort sort) {
        if (pagination.isUnpagged()) {
            return Pageable.unpaged();
        }

        return PageRequest.of(pagination.page().intValue(), pagination.size().intValue(), sort);
    }

    protected Specification<E> specificationFromFilters(final Filters filters) {
        if (filters == null || filters.isUnfiltered()) {
            return null;
        }

        final List<Specification<E>> specifications = this.specificationFromFiltersTemplate(filters)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if(specifications.isEmpty()){
            return null;
        }

        Specification<E> specification = specifications.get(0);
        specifications.remove(0);

        for (Specification<E> spec : specifications) {
            specification = specification.and(spec);
        }

        return specification;
    }

    public Specification<E> groupBy(Specification<E> specification, List<String> columnNames) {
        return (Specification<E>) (root, query, criteriaBuilder) -> {
            List<Expression<?>> columnNamesExpression = columnNames.stream().map(x -> root.get(x))
                    .collect(Collectors.toList());

            query.groupBy(columnNamesExpression);
            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }
}
