package org.haffson.adventofcode.utils;

public class UncheckedFileNotFoundException extends RuntimeException {
    public UncheckedFileNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
