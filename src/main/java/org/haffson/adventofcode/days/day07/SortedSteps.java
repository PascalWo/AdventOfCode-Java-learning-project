package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortedSteps {
    private final List<Character> characterSequence;
    private final List<Step> stepSequence;

    public SortedSteps() {
        this.characterSequence = new ArrayList<>();
        this.stepSequence = new ArrayList<>();
    }

    public List<Character> getCharacterSequence() {
        final List<Character> characters = new ArrayList<>();
        stepSequence.forEach(step -> {
            characters.add(step.getStepName());
        });
        return characters;
    }

    public void addStep(final Step nextStepToCheck) {
        if (isStepToCheckEmpty(nextStepToCheck)) {
            return;
        }
        if (!stepSequence.contains(nextStepToCheck)) {
            this.stepSequence.add(nextStepToCheck);
        }
    }

    private boolean isStepToCheckEmpty(final Step stepToCheck) {
        return stepToCheck.getStepName() == Character.MIN_VALUE;
    }

    @Nonnull
    public String getStepsAsString() {
        List<Character> characters = getCharacterSequence();
        return characters.stream().map(String::valueOf).collect(Collectors.joining());
    }
}


