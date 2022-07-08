package org.haffson.adventofcode.days.day04;

import java.util.Objects;

public class TimeStampInformation {
    private final int year;
    private final int month;
    private final int day;
    private final int hour;
    private final int minute;
    private final String action;

    public TimeStampInformation(final int year, final int month, final int day, final int hour, final int minute, final String action) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.action = action;
    }

    public static TimeStampInformation of(final String input){

        final int year = 0;
        final int month = 0;
        final int day = 0;
        final int hour = 0;
        final int minute = 0;
        final String action = null;

        return new TimeStampInformation(year, month, day, hour, minute, action);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getAction() {
        return action;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TimeStampInformation that = (TimeStampInformation) o;
        return year == that.year && month == that.month && day == that.day && hour == that.hour && minute == that.minute && Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day, hour, minute, action);
    }

    @Override
    public String toString() {
        return "TimeStampInformation{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", action='" + action + '\'' +
                '}';
    }
}
