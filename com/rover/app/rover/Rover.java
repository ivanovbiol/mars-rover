package com.rover.app.rover;

import com.rover.app.direction.Direction;

public class Rover {

    private int x;
    private int y;
    private Direction direction;

    private Rover(Builder builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.direction = builder.direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void executeCommand(int command) {
        if (command == 'L') {
            turnLeft();
        } else if (command == 'R') {
            turnRight();
        } else if (command == 'M') {
            move();
        }
    }

    private void turnLeft() {
        direction = Direction.values()[(direction.ordinal() + 3) % 4];
    }

    private void turnRight() {
        direction = Direction.values()[(direction.ordinal() + 1) % 4];
    }

    private void move() {
        switch (direction) {
            case N:
                y++;
                break;
            case E:
                x++;
                break;
            case S:
                y--;
                break;
            case W:
                x--;
                break;
        }
    }

    // Builder class
    public static class Builder {
        private int x;
        private int y;
        private Direction direction;

        public Builder x(int x) {
            this.x = x;
            return this;
        }

        public Builder y(int y) {
            this.y = y;
            return this;
        }

        public Builder direction(Direction direction) {
            this.direction = direction;
            return this;
        }

        public Rover build() {
            return new Rover(this);
        }
    }
}
