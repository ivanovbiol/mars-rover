package com.rover.app;

import com.rover.app.direction.Direction;
import com.rover.app.navigator.Navigator;
import com.rover.app.plateau.Plateau;
import com.rover.app.rover.Rover;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] plateauDimensions = readPlateauDimensions(scanner);

        String[] firstRoverPosition = readRoverPosition(scanner, plateauDimensions);
        String firstRoverCommandSet = readLine(scanner);

        String[] secondRoverPosition = readRoverPosition(scanner, plateauDimensions);
        String secondRoverCommandSet = readLine(scanner);

        Rover firstRover = new Rover.Builder()
                .x(Integer.parseInt(firstRoverPosition[0]))
                .y(Integer.parseInt(firstRoverPosition[1]))
                .direction(Direction.fromString(firstRoverPosition[2]))
                .build();

        Rover secondRover = new Rover.Builder()
                .x(Integer.parseInt(secondRoverPosition[0]))
                .y(Integer.parseInt(secondRoverPosition[1]))
                .direction(Direction.fromString(secondRoverPosition[2]))
                .build();

        Plateau plateau = new Plateau(plateauDimensions[0], plateauDimensions[1]);

        Navigator navigator = new Navigator(plateau);
        navigator.navigate(firstRover, firstRoverCommandSet);
        navigator.navigate(secondRover, secondRoverCommandSet);

        System.out.printf("%s %s %s", firstRover.getX(), firstRover.getY(), firstRover.getDirection().toString()).println();
        System.out.printf("%s %s %s", secondRover.getX(), secondRover.getY(), secondRover.getDirection().toString()).println();
    }

    private static int[] readPlateauDimensions(Scanner scanner) {
        return Arrays.stream(readLineAsArray(scanner)).mapToInt(Integer::parseInt).toArray();
    }

    private static String[] readRoverPosition(Scanner scanner, int[] plateauDimensions) {
        String[] commands = readLineAsArray(scanner);

        if (Arrays.stream(commands)
                .map(Integer::parseInt)
                .limit(2)
                .anyMatch(e -> e > Arrays.stream(plateauDimensions).max().orElseThrow(() -> new NoSuchElementException("Missing plateau coordinates")))) {
            throw new IllegalArgumentException("Rover coordinates can't exceed the plateau dimensions");
        } else if (Arrays.stream(Direction.values()).noneMatch(direction -> direction.name().equals(commands[commands.length - 1]))) {
            throw new IllegalArgumentException("Incorrect Rover destination");
        }

        return commands;
    }

    private static String readLine(Scanner scanner) {
        return scanner.nextLine();
    }

    private static String[] readLineAsArray(Scanner scanner) {
        return scanner.nextLine().split(" ");
    }
}
