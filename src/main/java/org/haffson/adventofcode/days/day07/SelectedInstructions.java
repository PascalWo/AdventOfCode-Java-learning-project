package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.*;

import static java.util.Objects.requireNonNull;

public class SelectedInstructions {

    @Nonnull
    private final List<StepInstruction> stepInstructions;
    @Nonnull
    private List<StepInstruction> availableStepInstructions = new ArrayList<>();
    @Nonnull
    private List<StepInstruction> availableStepInstructionsNotUsed = new ArrayList<>();
    private StepInstruction nextStepInstruction;
    @Nonnull
    private final SortedSteps sortedSteps;

    public SelectedInstructions(@Nonnull final List<StepInstruction> stepInstructions, @Nonnull final SortedSteps sortedSteps) {
        this.stepInstructions = requireNonNull(stepInstructions, "stepInstructions");
        this.sortedSteps = requireNonNull(sortedSteps, "sortedSteps");
    }

    public StepInstruction getNextStepInstruction() {
        return nextStepInstruction;
    }

    public int getAmountOfInstructions() {
        return stepInstructions.size();
    }

    public void setAvailableStarterOptions(@Nonnull final Starter starter) {
        for (final char stepToCompare : starter.getStartingSteps()) {

            availableStepInstructionsNotUsed.addAll(getAvailableStepInstructions(stepToCompare));
        }
    }

    @Nonnull
    private List<StepInstruction> getAvailableStepInstructions(final char stepToCompare) {
        return List.copyOf(stepInstructions).stream()
                .filter(stepInstruction -> stepToCompare == stepInstruction.step()).
                toList();
    }

    public void setAvailableStepInstructions(final char stepToCompare) {
        final Set<StepInstruction> availableInstructions = new HashSet<>();

        availableInstructions.addAll(getAvailableStepInstructions(stepToCompare));
        availableInstructions.addAll(availableStepInstructionsNotUsed);

        this.availableStepInstructions = new ArrayList<>(availableInstructions);
    }

    public int getAmountOfAvailableStepInstructions() {
        return availableStepInstructions.size();
    }

    public void getNextStepInstructionByAlphabeticalOrder() {
        sortInstructionsAlphabeticalByFinishedBefore();
        for (int i = 0; i < getAmountOfAvailableStepInstructions(); i++) {
            if (isNextStepInstruction(i)) {
                setNextStepInstruction(i);
                setUnusedStepInstructions(i);
                break;
            }
        }
    }

    private void sortInstructionsAlphabeticalByFinishedBefore() {
        this.availableStepInstructions = availableStepInstructions
                .stream()
                .sorted(Comparator.comparing(StepInstruction::finishedBefore))
                .toList();
    }

    private boolean isNextStepInstruction(final int i) {
        final StepInstruction possibleNextInstruction = availableStepInstructions.get(i);

        return isPrerequisiteComplete(possibleNextInstruction);
    }

    private boolean isPrerequisiteComplete(@Nonnull final StepInstruction instructionToCheck) {
        final char stepToCheck = instructionToCheck.finishedBefore();

        if (sortedSteps.stepIsAlreadyUsed(stepToCheck)) {
            return false;
        }
        final List<Character> stepsNeededToBeCompleted = getStepsNeedToBeCompleted(stepToCheck);

        return sortedSteps.isEveryNeededStepCompleted(stepsNeededToBeCompleted);
    }

    @Nonnull
    private List<Character> getStepsNeedToBeCompleted(final char stepToCheck) {
        return List.copyOf(stepInstructions)
                .stream()
                .filter(stepInstruction -> stepToCheck == stepInstruction.finishedBefore())
                .map(StepInstruction::step)
                .toList();
    }

    private void setNextStepInstruction(final int i) {
        this.nextStepInstruction = availableStepInstructions.get(i);
    }

    private void setUnusedStepInstructions(final int i) {
        final List<StepInstruction> stepInstructionsNotUsed = new ArrayList<>(availableStepInstructions);
        stepInstructionsNotUsed.remove(i);
        this.availableStepInstructionsNotUsed = stepInstructionsNotUsed;
    }
}
