package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.*;

public class StepSorter {

    @Nonnull
    private final List<StepInstruction> stepInstructions;
    private List<Character> startingSteps;
    @Nonnull
    private List<StepInstruction> availableStepInstructions = new ArrayList<>();
    @Nonnull
    private List<StepInstruction> availableStepInstructionsNotUsed = new ArrayList<>();

    private StepInstruction nextStepInstruction;
    private char stepWhenInstructionIsEmpty;

    private char stepWhenInstructionIsDuplicated;

    private char leastStep;

    private char nextStepToCheck;
    
    private final SortedSteps sortedSteps;

    public StepSorter(@Nonnull final List<StepInstruction> stepInstructions) {
        this.stepInstructions = stepInstructions;
        this.sortedSteps = new SortedSteps();
    }


    public String getSortedSteps() {
        initialiseStarting();

        for (int i = 0; i < stepInstructions.size(); i++) {
            setAvailableStepInstructions(nextStepToCheck);

            getNextStepInstructionByAlphabeticalOrder();

            setNextStepToCheck();

            addStepToResult();
        }

        return sortedSteps.getSequenceAsString();
    }

    public List<Character> getStartingSteps() {
        return startingSteps;
    }

    private char getStartingStep() {
        return startingSteps.get(0);
    }

    private void setNextStepToCheck() {
        if (nextStepInstruction != null) {
            this.nextStepToCheck = nextStepInstruction.finishedBefore();
            if (nextStepToCheck == leastStep) {
                setNextStepWhenDuplicate();
                this.nextStepToCheck = stepWhenInstructionIsDuplicated;
            }
        } else {
            setNextStepWhenEmpty();
            this.nextStepToCheck = stepWhenInstructionIsEmpty;
        }
    }

    private void addStepToResult() {
        sortedSteps.addStep(nextStepToCheck);
        setLeastStep();
    }

    private void setLeastStep() {
        this.leastStep = nextStepToCheck;
    }

    private void initialiseStarting() {
        findStartingStep();
        setAvailableStarterOptions();
        this.sortedSteps.addStep(getStartingStep());
        this.nextStepToCheck = getStartingStep();
    }

    public void findStartingStep() {
        final List<Character> steps = new ArrayList<>();
        this.stepInstructions.forEach(stepInstruction -> steps.add(stepInstruction.step()));

        final List<Character> stepsFinishedBefore = new ArrayList<>();
        this.stepInstructions.forEach(stepInstruction -> stepsFinishedBefore.add(stepInstruction.finishedBefore()));

        final List<Character> stepsWithoutPreCondition = new ArrayList<>(steps);
        stepsWithoutPreCondition.removeAll(stepsFinishedBefore);
        final Set<Character> stepsWithoutPreConditionDuplicateFree = new HashSet<>(stepsWithoutPreCondition);


        this.startingSteps = new ArrayList<>(stepsWithoutPreConditionDuplicateFree);
    }

    public void setAvailableStarterOptions() {
        for (final char stepToCompare : startingSteps) {
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
        final List<Character> starterSteps = new ArrayList<>(startingSteps);
        final List<Character> actualSteps = new ArrayList<>(sortedSteps.getStepSequence());

        starterSteps.removeAll(actualSteps);

        return starterSteps;
    }

    private void sortInstructionsAlphabeticalByFinishedBefore() {
        this.availableStepInstructions = availableStepInstructions
                .stream()
                .sorted(Comparator.comparing(StepInstruction::finishedBefore))
                .toList();
    }

    private boolean isPrerequisiteComplete(@Nonnull final StepInstruction instructionToCheck) {
        final char stepToCheck = instructionToCheck.finishedBefore();

        if (stepIsAlreadyUsed(stepToCheck)) {
            return false;
        }
        final List<Character> stepsNeededToBeCompleted = getStepsNeedToBeCompleted(stepToCheck);

        return isEveryNeededStepCompleted(stepsNeededToBeCompleted);
    }

    private boolean stepIsAlreadyUsed(final char stepToCheck) {
        return sortedSteps.getStepSequence().contains(stepToCheck);
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
        return new HashSet<>(sortedSteps.getStepSequence()).containsAll(stepsNeededToBeCompleted);
    }
}
