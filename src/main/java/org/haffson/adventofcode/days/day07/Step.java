package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public record Step(char stepName, @Nonnull List<Character> dependsOn) {
    public Step {
        requireNonNull(dependsOn, "dependsOn");
    }

    public Step(final char stepName) {
        this(stepName, new ArrayList<>());
    }
}
