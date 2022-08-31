package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Step {
    private final char stepName;

    private final List<Character> dependsOn;

    public Step(final char stepName, @Nonnull final List<Character> dependsOn) {
        this.stepName = stepName;
        this.dependsOn = dependsOn;
    }

    public static List<Step> of(@Nonnull final Map<Character, List<Character>> dependenciesByStep) {
        return dependenciesByStep.entrySet().stream().map(entry -> new Step(entry.getKey(), entry.getValue())).toList();

    }

    public Step(final char stepName) {
        this.stepName = stepName;
        this.dependsOn = new ArrayList<>();
    }

    public char getStepName() {
        return stepName;
    }

    public List<Character> getDependsOn() {
        return dependsOn;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Step step = (Step) o;
        return stepName == step.stepName && Objects.equals(dependsOn, step.dependsOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepName, dependsOn);
    }

    @Override
    public String toString() {
        return "Step{" +
                "stepName=" + stepName +
                ", dependsOn=" + dependsOn +
                '}';
    }
}
