package com.setpoint.reservapistes.modules.shared.core.repositories.domain.sort;

public class Sorting {

    public static final String DEFAULT_FIELD = "id";
    public static final Direction DEFAULT_DIRECTION = Direction.ASC;

    private final String field;
    private final Direction direction;

    private Sorting(){
        this.field = DEFAULT_FIELD;
        this.direction = DEFAULT_DIRECTION;
    }

    private Sorting(final String field, final String direction) {
        this.field = defaultField(field);
        this.direction = defaultDirection(direction);
    }

    private String defaultField(String field) {
        return field == null ? DEFAULT_FIELD : field;
    }

    private Direction defaultDirection(String direction) {
        return Direction.fromString(direction);
    }

    public String getField() {
        return field;
    }

    public Direction getDirection() {
        return direction;
    }

    public static Sorting create(final String field, final String direction) {
        return new Sorting(field, direction);
    }

    public enum Direction {
        ASC,
        DESC;

        public static Direction fromString(final String direction) {
            for (Direction direction1 : Direction.values()) {
                if (direction1.name().equalsIgnoreCase(direction)) {
                    return direction1;
                }
            }
            return DEFAULT_DIRECTION;
        }
    }
}
