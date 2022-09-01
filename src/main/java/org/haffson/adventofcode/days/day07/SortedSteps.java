package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class SortedSteps {
    private final List<Step> stepSequence;

    public SortedSteps() {
        this.stepSequence = new ArrayList<>();
    }
    @Nonnull
    public List<Character> getCharacterSequence() {
        final List<Character> characters = new ArrayList<>();
        stepSequence.forEach(step -> characters.add(step.getStepName()));
        return characters;
    }

    public void addStep(@Nonnull final Step nextStepToCheck) {
        requireNonNull(nextStepToCheck, "nextStepToCheck");
        if (isStepToCheckEmpty(nextStepToCheck)) {
            return;
        }
        if (!stepSequence.contains(nextStepToCheck)) {
            this.stepSequence.add(nextStepToCheck);
        }
    }

    private boolean isStepToCheckEmpty(@Nonnull final Step stepToCheck) {
        requireNonNull(stepToCheck, "stepToCheck");
        return stepToCheck.getStepName() == Character.MIN_VALUE;
    }

    @Nonnull
    public String getStepsAsString() {
        final List<Character> characters = getCharacterSequence();
        return characters.stream().map(String::valueOf).collect(Collectors.joining());
    }
}


