package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public record Step(char stepName, @Nonnull Set<Character> dependsOn) {
    public Step {
        requireNonNull(dependsOn, "dependsOn");
    }

    public Step(final char stepName) {
        this(stepName, new HashSet<>());
    }

    @Nonnull
    public Step mergeInDependenciesFrom(@Nonnull final Step stepToMerge) {
        requireNonNull(stepToMerge, "stepToMerge");

        final Set<Character> dependencies = new HashSet<>();
        dependencies.addAll(this.dependsOn());
        dependencies.addAll(stepToMerge.dependsOn());

        return new Step(this.stepName, dependencies);
    }
}
