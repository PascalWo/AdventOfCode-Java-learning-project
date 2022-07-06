package org.haffson.adventofcode.days.day03;

import java.util.Objects;

public class RectangleClaim {
    private int id;
    private int leftSpace;
    private int topSpace;
    private int width;
    private int height;

    public RectangleClaim(final int id, final int leftSpace, final int topSpace, final int width, final int height) {
        this.id = id;
        this.leftSpace = leftSpace;
        this.topSpace = topSpace;
        this.width = width;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getLeftSpace() {
        return leftSpace;
    }

    public void setLeftSpace(final int leftSpace) {
        this.leftSpace = leftSpace;
    }

    public int getTopSpace() {
        return topSpace;
    }

    public void setTopSpace(final int topSpace) {
        this.topSpace = topSpace;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RectangleClaim that = (RectangleClaim) o;
        return id == that.id && leftSpace == that.leftSpace && topSpace == that.topSpace && width == that.width && height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leftSpace, topSpace, width, height);
    }

    @Override
    public String toString() {
        return "RectangleClaim{" +
                "id=" + id +
                ", leftSpace=" + leftSpace +
                ", topSpace=" + topSpace +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}