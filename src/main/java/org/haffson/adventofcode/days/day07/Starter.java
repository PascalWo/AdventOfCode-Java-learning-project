package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class Starter {
    List<Character> startingSteps;
    private final List<StepInstruction> stepInstructions;

    public Starter(@Nonnull final List<StepInstruction> stepInstructions) {
        this.stepInstructions = requireNonNull(stepInstructions, "stepInstructions");
    }

    public void setStartingSteps() {
        final List<Character> steps = new ArrayList<>();
        this.stepInstructions.forEach(stepInstruction -> steps.add(stepInstruction.step()));

        final List<Character> stepsFinishedBefore = new ArrayList<>();
        this.stepInstructions.forEach(stepInstruction -> stepsFinishedBefore.add(stepInstruction.finishedBefore()));

        final List<Character> stepsWithoutPreCondition = new ArrayList<>(steps);
        stepsWithoutPreCondition.removeAll(stepsFinishedBefore);
        final Set<Character> stepsWithoutPreConditionDuplicateFree = new HashSet<>(stepsWithoutPreCondition);

        this.startingSteps = new ArrayList<>(stepsWithoutPreConditionDuplicateFree);
    }

    public List<Character> getStartingSteps() {
        return startingSteps;
    }

    public char getStartingStep() {
        return startingSteps.get(0);
    }
}
