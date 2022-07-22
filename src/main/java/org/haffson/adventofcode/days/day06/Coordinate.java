package org.haffson.adventofcode.days.day06;

public record Coordinate(int xCoordinate, int yCoordinate) {

    public static Coordinate of(final String input) {
        final String[] split1 = input.split(",");
        final String[] split2 = split1[1].split(" ");

        int xCoordinate = Integer.parseInt(split1[0]);
        int yCoordinate = Integer.parseInt(split2[1]);


        return new Coordinate(xCoordinate, yCoordinate);
    }
}
