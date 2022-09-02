package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public record SortedSteps(@Nonnull List<Step> stepSequence) {
    public SortedSteps() {
        this(new ArrayList<>());
    }

    @Nonnull
    public List<Character> getCharacterSequence() {
        final List<Character> characterSequence = new ArrayList<>();
        stepSequence.forEach(step -> characterSequence.add(step.stepName()));

        return characterSequence;
    }

    public void addStep(@Nonnull final Step nextStepToCheck) {
        requireNonNull(nextStepToCheck, "nextStepToCheck");

        this.stepSequence.add(nextStepToCheck);
    }

    @Nonnull
    public String getStepsAsString() {
        final List<Character> characters = getCharacterSequence();

        return characters.stream().map(String::valueOf).collect(Collectors.joining());
    }
}


