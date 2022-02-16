package com.setpoint.reservapistes.modules.shared.core.repositories.domain.filters;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Filters {

    private final Map<String, Object> filters;
    private boolean isUnfiltered;

    public Filters() {
        this.filters = new HashMap<>();
    }

    public static Filters create() {
        return new Filters();
    }

    public static Filters withoutFilters() {
        final Filters filters = new Filters();
        filters.unfiltered();
        return filters;
    }

    private void unfiltered() {
        this.isUnfiltered = true;
    }

    public <T> void put(final String key, T object) {
        this.filters.put(key, object);
    }

    public <T> Optional<T> get(final String key, final Class<T> clazz) {
        return Optional.ofNullable(filters.get(key)).map(clazz::cast);
    }

    public boolean isUnfiltered() {
        return isUnfiltered;
    }
}
