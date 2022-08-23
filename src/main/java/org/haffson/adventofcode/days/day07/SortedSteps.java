package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SortedSteps {
    private final List<Character> stepSequence;

    public SortedSteps() {
        this.stepSequence = new ArrayList<>();
    }

    public List<Character> getStepSequence() {
        return Collections.unmodifiableList(stepSequence);
    }

    public void addStep(final char nextStepToCheck) {
        if (isStepToCheckEmpty(nextStepToCheck)) {
            return;
        }
        if (!stepSequence.contains(nextStepToCheck)) {
            this.stepSequence.add(nextStepToCheck);
        }
    }

    private boolean isStepToCheckEmpty(final char stepToCheck) {
        return stepToCheck == Character.MIN_VALUE;
    }

    @Nonnull
    public String getSequenceAsString() {
        return stepSequence.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
