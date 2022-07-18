package org.haffson.adventofcode.days.day04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

public class TimeStampInformation {
    private final LocalDateTime timeStamp;
    private final String information;

    public TimeStampInformation(final LocalDateTime timeStamp, final String information) {
        this.timeStamp = timeStamp;
        this.information = information;
    }

    public static TimeStampInformation of(final String input) {

        final String stringDate = input.split(Pattern.quote("["))[1].split(Pattern.quote("]"))[0];

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        final LocalDateTime date = LocalDateTime.parse(stringDate, dateTimeFormatter);
        final String information = input.split("] ")[1];

        return new TimeStampInformation(date, information);
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getInformation() {
        return information;
    }

    public int getGuardID() {
        if (this.getInformation().contains("shift")) {
            return Integer.parseInt(this.getInformation().split("#")[1].split(" ")[0]);
        }
        return 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TimeStampInformation that = (TimeStampInformation) o;
        return Objects.equals(timeStamp, that.timeStamp) && Objects.equals(information, that.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeStamp, information);
    }

    @Override
    public String toString() {
        return "TimeStampInformation{" +
                "timeStamp=" + timeStamp +
                ", information='" + information + '\'' +
                '}';
    }
}
