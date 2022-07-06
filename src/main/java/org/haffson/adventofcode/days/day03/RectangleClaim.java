package org.haffson.adventofcode.days.day03;

import java.util.Objects;

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