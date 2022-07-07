package org.haffson.adventofcode.days.day03;

import java.util.Objects;

/**
 * Primary class for Day 3.
 * Is used to get rectangles claims out of the string-list.
 * It helps to compare the different claims.
 */

public class RectangleClaim {
    private final String id;
    private final int leftSpace;
    private final int topSpace;
    private final int width;
    private final int height;

    public RectangleClaim(final String id, final int leftSpace, final int topSpace, final int width, final int height) {
        this.id = id;
        this.leftSpace = leftSpace;
        this.topSpace = topSpace;
        this.width = width;
        this.height = height;
    }

    public int getRightXCoordinate(){
        return leftSpace+width;
    }

    public int getBottomYCoordinate(){
        return topSpace+height;
    }
    public static RectangleClaim of(final String input){
        final String[] split1 = input.split("@");
        final String[] splitId = split1[0].split("#")[1].split(" ");
        final String[] split2 = split1[1].split(" ");
        final String[] spaces = split2[1].split(",");
        final String[] topSpace = spaces[1].split(":");
        final String[] recSize = split2[2].split("x");

        final String currentId = splitId[0];
        final int leftSpace = Integer.parseInt(spaces[0]);
        final int topSPace = Integer.parseInt(topSpace[0]);
        final int width = Integer.parseInt(recSize[0]);
        final int height = Integer.parseInt(recSize[1]);

        return new RectangleClaim(currentId, leftSpace, topSPace, width, height);
    }
    public String getId() {
        return id;
    }

    public int getLeftSpace() {
        return leftSpace;
    }

    public int getTopSpace() {
        return topSpace;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RectangleClaim that = (RectangleClaim) o;
        return leftSpace == that.leftSpace && topSpace == that.topSpace && width == that.width && height == that.height && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leftSpace, topSpace, width, height);
    }

    @Override
    public String toString() {
        return "RectangleClaim{" +
                "id='" + id + '\'' +
                ", leftSpace=" + leftSpace +
                ", topSpace=" + topSpace +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}