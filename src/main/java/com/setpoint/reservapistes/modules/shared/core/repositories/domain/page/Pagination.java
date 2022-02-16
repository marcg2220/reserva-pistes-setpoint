package com.setpoint.reservapistes.modules.shared.core.repositories.domain.page;

import java.util.Objects;

public class Pagination {
    public static final Long MAX_PAGE = Long.MAX_VALUE;
    public static final Long MIN_PAGE = 0L;

    public static final Long MAX_SIZE = 1000L;
    public static final Long MIN_SIZE = 5L;

    public static final Long DEFAULT_SIZE = 20L;
    public static final Long DEFAULT_PAGE = 0L;

    private final Long page;
    private final Long size;

    private boolean isUnpagged;

    private Pagination(){
        this.page = DEFAULT_PAGE;
        this.size = DEFAULT_SIZE;
    }

    private Pagination(final Long page, final Long size) {
        final Long sPage = defaultValue(page, DEFAULT_PAGE);
        final Long sSize = defaultValue(size, DEFAULT_SIZE);

        this.page = minMax(MIN_PAGE, MAX_PAGE, sPage);
        this.size = minMax(MIN_SIZE, MAX_SIZE, sSize);
    }

    public static Pagination of(final Long page, final Long size) {
        return new Pagination(page, size);
    }

    public static Pagination of(final Integer page, final Integer size) {
        final Long lPage = page == null ? null : page.longValue();
        final Long lSize = size == null ? null : size.longValue();
        return new Pagination(lPage, lSize);
    }

    public static Pagination withoutPagination() {
        final Pagination pagination = new Pagination();
        pagination.unpaged();
        return new Pagination();
    }

    public Long page() {
        return page;
    }

    public Long size() {
        return size;
    }

    public boolean isUnpagged() {
        return isUnpagged;
    }

    private Long defaultValue(final Long value, final Long defaultValue) {
        return value == null ? defaultValue : value;
    }

    private Long minMax(final Long min, final Long max, final Long current) {
        return current < min ? min : current > max ? max : current;
    }

    private void unpaged(){
        this.isUnpagged = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagination that = (Pagination) o;
        return Objects.equals(page, that.page) &&
                Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, size);
    }
}
