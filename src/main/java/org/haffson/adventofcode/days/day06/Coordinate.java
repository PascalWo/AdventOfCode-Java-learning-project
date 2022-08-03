package org.haffson.adventofcode.days.day06;

import javax.annotation.Nonnull;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Helper record for day 6.
 * Is used to work better with the string inputs.
 * Coordinates have an x and y value.
 * Also, you can compare two coordinates with each other.
 */
public record Coordinate(int x, int y) {

    @Nonnull
    public static Coordinate of(@Nonnull final String input) {
        requireNonNull(input, "input");
        final String[] split1 = input.split(",");
        final String[] split2 = split1[1].split(" ");

        final int xCoordinate = Integer.parseInt(split1[0]);
        final int yCoordinate = Integer.parseInt(split2[1]);

        return new Coordinate(xCoordinate, yCoordinate);
    }

    /**
     * Helper method for day 6.
     * Gives a list<Coordinate> of a string-list.
     *
     * @return List<Coordinate>
     */
    @Nonnull
    public static List<Coordinate> of(@Nonnull final List<String> stringList) {
        requireNonNull(stringList, "stringList");
        return stringList.stream().map(Coordinate::of).toList();
    }

    /**
     * Helper method for day 6.
     * Compares the distance of two coordinates.
     *
     * @return int of how many fields are between two coordinates.
     */
    public int coordinateFieldDistance(@Nonnull final Coordinate comparedCoordinate) {
        final int xDistance = Math.abs(this.x - comparedCoordinate.x);
        final int yDistance = Math.abs(this.y - comparedCoordinate.y);

        return xDistance + yDistance;
    }


}
