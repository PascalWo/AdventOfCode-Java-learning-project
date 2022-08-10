package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.*;

public class StepSorter {

    private final List<StepInstruction> stepInstructions;

    private List<Character> startingStep;

    private List<StepInstruction> availableStepInstructions = new ArrayList<>();
    private List<StepInstruction> availableStepInstructionsNotUsed = new ArrayList<>();
    private StepInstruction nextStepInstruction;
    private char stepWhenInstructionIsEmpty;

    private char stepWhenInstructionIsDuplicated;

    private char leastStep;

    public List<Character> getStartingStep() {
        return startingStep;
    }


    List<Character> resultString = new ArrayList<>();

    public StepSorter(final List<StepInstruction> stepInstructions) {
        this.stepInstructions = stepInstructions;
    }


    public String getSortedSteps() {
        char stepToCheck;

        findStartingStep();

        checkStarterAvailable();

        resultString.add(startingStep.get(0));

        stepToCheck = startingStep.get(0);

        for (int i = 0; i < stepInstructions.size(); i++) {
            setAvailableStepInstructions(stepToCheck);

            getNextStepInstructionByAlphabeticalOrder();

            if (nextStepInstruction != null) {
                stepToCheck = nextStepInstruction.finishedBefore();

                if (stepToCheck == leastStep) {
                    setNextStepWhenDuplicate();
                    stepToCheck = stepWhenInstructionIsDuplicated;
                }
            } else {
                setNextStepWhenEmpty();
                stepToCheck = stepWhenInstructionIsEmpty;
            }


            if (!resultString.contains(stepToCheck)) {
                resultString.add(stepToCheck);
                leastStep = stepToCheck;
            }
        }

        return getStringRepresentation(resultString);
    }

    @Nonnull
    private String getStringRepresentation(@Nonnull final List<Character> list) {
        final StringBuilder builder = new StringBuilder(list.size());
        for (final Character character : list) {
            builder.append(character);
        }
        return builder.toString();
    }

    public void findStartingStep() {
        final List<Character> steps = new ArrayList<>();
        this.stepInstructions.forEach(stepInstruction -> steps.add(stepInstruction.step()));

        final List<Character> stepsFinishedBefore = new ArrayList<>();
        this.stepInstructions.forEach(stepInstruction -> stepsFinishedBefore.add(stepInstruction.finishedBefore()));

        final List<Character> stepsWithoutPreCondition = new ArrayList<>(steps);
        stepsWithoutPreCondition.removeAll(stepsFinishedBefore);
        final Set<Character> stepsWithoutPreConditionDuplicateFree = new HashSet<>(stepsWithoutPreCondition);


        this.startingStep = new ArrayList<>(stepsWithoutPreConditionDuplicateFree);
    }

    public void checkStarterAvailable() {
        for (final char stepToCompare : startingStep) {
            availableStepInstructionsNotUsed.addAll(getAvailableStepInstructions(stepToCompare));
        }
    }

    private void setAvailableStepInstructions(final char stepToCompare) {
        final Set<StepInstruction> availableInstructions = new HashSet<>();

        availableInstructions.addAll(getAvailableStepInstructions(stepToCompare));
        availableInstructions.addAll(availableStepInstructionsNotUsed);

        this.availableStepInstructions = new ArrayList<>(availableInstructions);
    }

    @Nonnull
    private List<StepInstruction> getAvailableStepInstructions(final char stepToCompare) {
        return List.copyOf(stepInstructions).stream()
                .filter(stepInstruction -> stepToCompare == stepInstruction.step()).
                toList();
    }

    private void getNextStepInstructionByAlphabeticalOrder() {
        sortInstructionsAlphabeticalByFinishedBefore();
        for (int i = 0; i < availableStepInstructions.size(); i++) {
            if (isNextStepInstruction(i)) {
                setNextStepInstruction(i);
                setUnusedStepInstructions(i);
                break;
            }
        }
    }

    private boolean isNextStepInstruction(final int i) {
        final StepInstruction possibleNextInstruction = availableStepInstructions.get(i);

        return isPrerequisiteComplete(possibleNextInstruction);
    }

    private void setNextStepInstruction(final int i) {
        this.nextStepInstruction = availableStepInstructions.get(i);
    }

    private void setUnusedStepInstructions(final int i) {
        final List<StepInstruction> stepInstructionsNotUsed = new ArrayList<>(availableStepInstructions);
        stepInstructionsNotUsed.remove(i);
        this.availableStepInstructionsNotUsed = stepInstructionsNotUsed;
    }


    private void setNextStepWhenEmpty() {
        this.stepWhenInstructionIsEmpty = getAvailableStarterSteps().get(0);
    }

    private void setNextStepWhenDuplicate() {
        if (!getAvailableStarterSteps().isEmpty()) {
            this.stepWhenInstructionIsDuplicated = getAvailableStarterSteps().get(0);
        }
    }

    @Nonnull
    private List<Character> getAvailableStarterSteps() {
        final List<Character> starterSteps = new ArrayList<>(startingStep);
        final List<Character> actualSteps = new ArrayList<>(resultString);

        starterSteps.removeAll(actualSteps);

        return starterSteps;
    }

    private void sortInstructionsAlphabeticalByFinishedBefore() {
        this.availableStepInstructions = availableStepInstructions
                .stream()
                .sorted(Comparator.comparing(StepInstruction::finishedBefore))
                .toList();
    }

    private boolean isPrerequisiteComplete(@Nonnull final StepInstruction toCheck) {
        final char stepToCheck = toCheck.finishedBefore();

        if (stepIsAlreadyUsed(stepToCheck)) {
            return false;
        }
        final List<Character> stepsNeededToBeCompleted = getStepsNeedToBeCompleted(stepToCheck);

        return isEveryNeededStepCompleted(stepsNeededToBeCompleted);
    }

    private boolean stepIsAlreadyUsed(final char stepToCheck) {
        return resultString.contains(stepToCheck);
    }

    @Nonnull
    private List<Character> getStepsNeedToBeCompleted(final char stepToCheck) {
        return List.copyOf(stepInstructions)
                .stream()
                .filter(stepInstruction -> stepToCheck == stepInstruction.finishedBefore())
                .map(StepInstruction::step)
                .toList();
    }

    private boolean isEveryNeededStepCompleted(@Nonnull final List<Character> stepsNeededToBeCompleted) {
        return new HashSet<>(resultString).containsAll(stepsNeededToBeCompleted);
    }
}
