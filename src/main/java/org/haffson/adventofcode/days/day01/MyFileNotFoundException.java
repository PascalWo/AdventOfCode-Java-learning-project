package org.haffson.adventofcode.days.day01;

public class MyFileNotFoundException extends RuntimeException {
    public MyFileNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
