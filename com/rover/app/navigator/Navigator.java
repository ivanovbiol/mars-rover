package com.rover.app.navigator;

import com.rover.app.plateau.Plateau;
import com.rover.app.rover.Rover;

public class Navigator {
    private final Plateau plateau;

    public Navigator(Plateau plateau) {
        this.plateau = plateau;
    }

    public void navigate(Rover rover, String instructions) {
        instructions.chars()
                .forEach(instruction -> {
                    if (instruction == 'M') {
                        int nextX = rover.getX();
                        int nextY = rover.getY();
                        switch (rover.getDirection()) {
                            case N:
                                nextY++;
                                break;
                            case E:
                                nextX++;
                                break;
                            case S:
                                nextY--;
                                break;
                            case W:
                                nextX--;
                                break;
                        }
                        if (isValidPosition(nextX, nextY)) {
                            rover.executeCommand(instruction);
                        }
                    } else {
                        rover.executeCommand(instruction);
                    }
                });
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x <= plateau.getWidth() && y >= 0 && y <= plateau.getHeight();
    }
}
