package org.haffson.adventofcode.utils;

public class UncheckedFileNotFoundException extends RuntimeException {
    public UncheckedFileNotFoundException(final String errorMessage, final Throwable err) {
        super(errorMessage, err);
    }
}
