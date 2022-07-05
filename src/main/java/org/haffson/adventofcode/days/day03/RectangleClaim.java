package org.haffson.adventofcode.days.day03;

import java.util.Objects;

public class RectangleClaim {
    private String id;
    private long leftSpace;
    private long topSpace;
    private long width;
    private long height;

    public RectangleClaim(final String id, final long leftSpace, final long topSpace, final long width, final long height) {
        this.id = id;
        this.leftSpace = leftSpace;
        this.topSpace = topSpace;
        this.width = width;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public long getLeftSpace() {
        return leftSpace;
    }

    public void setLeftSpace(final long leftSpace) {
        this.leftSpace = leftSpace;
    }

    public long getTopSpace() {
        return topSpace;
    }

    public void setTopSpace(final long topSpace) {
        this.topSpace = topSpace;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(final long width) {
        this.width = width;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(final long height) {
        this.height = height;
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