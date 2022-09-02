package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public record Step(char stepName, @Nonnull List<Character> dependsOn) {
    public Step {
        requireNonNull(dependsOn, "dependsOn");
    }

    public Step(final char stepName) {
        this(stepName, new ArrayList<>());
    }

    public static List<Step> of(@Nonnull final Map<Character, List<Character>> dependenciesByStep) {
        requireNonNull(dependenciesByStep, "dependenciesByStep");
        return dependenciesByStep.entrySet().stream().map(entry -> new Step(entry.getKey(), entry.getValue())).toList();
    }
}
