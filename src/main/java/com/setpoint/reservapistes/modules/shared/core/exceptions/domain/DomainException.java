package com.setpoint.reservapistes.modules.shared.core.exceptions.domain;

public abstract class DomainException extends RuntimeException {

    private static final String DEFAULT_ENTITYNAME = "";
    private static final String DEFAULT_ENTITYFIELD = "";

    public String code() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(category().name());

        if (entityName() != null && !entityName().isEmpty()) {
            stringBuilder.append(String.format("-%s", entityName().toUpperCase()));
        }

        if (entityField() != null && !entityField().isEmpty()) {
            stringBuilder.append(String.format("-%s", entityField().toUpperCase()));
        }

        return stringBuilder.toString();
    }

    public String entityName() { return DEFAULT_ENTITYNAME; }

    public String entityField() { return DEFAULT_ENTITYFIELD; }

    public abstract DomainExceptionCategory category();

    public abstract String apiMessage();

    public String uiMessage() { return null; }

    public final String type() { return category().name(); }

    @Override
    public String getMessage() {
        return String.format("(%s)[%s] : %s", code(), category(), apiMessage());
    }

    public enum DomainExceptionCategory {
        FORMAT,
        BUSINESS,
        PROTOCOL,
        FORBIDEN
    }

    public static DomainExceptionBuilder builder() { return new DomainExceptionBuilder(); }
}
