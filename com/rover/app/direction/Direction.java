package com.rover.app.direction;

public enum Direction {
    N, E, S, W;

    public static Direction fromString(String directionStr) {
        for (Direction direction : Direction.values()) {
            if (direction.name().equalsIgnoreCase(directionStr)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Invalid direction string: " + directionStr);
    }

}
