package com.setpoint.reservapistes.modules.shared.values.date;

import java.time.OffsetDateTime;

public class SharedDateTime {
    private final OffsetDateTime offsetDateTime;

    private SharedDateTime(OffsetDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }

    public static SharedDateTime now() {
        return new SharedDateTime(OffsetDateTime.now());
    }

    public static SharedDateTime create(final String offsetDateTimeString) {
        try {
            return new SharedDateTime(OffsetDateTime.parse(offsetDateTimeString));
        } catch (Exception e) {
            throw new SharedDateTimeInvalid();
        }
    }

    public OffsetDateTime value() { return offsetDateTime; }

    public String asText() { return this.value().toString(); }

    public static SharedDateTime create(final OffsetDateTime offsetDateTime) {
        try {
            return new SharedDateTime(offsetDateTime);
        } catch (final Exception e) {
            throw new SharedDateTimeInvalid();
        }
    }

    public Boolean isBetween(final OffsetDateTime ini, final OffsetDateTime fin) {
        final SharedDateTime inici = create(ini);
        final SharedDateTime fi = create(fin);
        return isBetween(inici, fi);
    }

    public Boolean isBetween(final SharedDateTime inici, final SharedDateTime fi) {
        return offsetDateTime.isAfter(inici.value()) && offsetDateTime.isBefore(fi.value());
    }

    public Boolean isAfter(final OffsetDateTime date) {
        final SharedDateTime sharedDateTime = create(date);
        return isAfter(sharedDateTime);
    }

    public Boolean isAfter(final SharedDateTime date) { return offsetDateTime.isAfter(date.value()); }

    public Boolean isBefore(final OffsetDateTime date) {
        final SharedDateTime sharedDateTime = create(date);
        return isBefore(sharedDateTime);
    }

    public Boolean isBefore(final SharedDateTime date) {
        return offsetDateTime.isBefore(date.value());
    }
}
