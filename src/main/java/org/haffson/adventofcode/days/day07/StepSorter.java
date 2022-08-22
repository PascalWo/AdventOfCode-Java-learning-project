package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.*;

import static java.util.Objects.requireNonNull;

public class StepSorter {

    @Nonnull
    private final List<StepInstruction> stepInstructions;
    @Nonnull
    private List<StepInstruction> availableStepInstructions = new ArrayList<>();
    @Nonnull
    private List<StepInstruction> availableStepInstructionsNotUsed = new ArrayList<>();
    private StepInstruction nextStepInstruction;
    private final SortedSteps sortedSteps;
    private final SelectedSteps selectedSteps;

    public StepSorter(@Nonnull final List<StepInstruction> stepInstructions) {
        this.stepInstructions = requireNonNull(stepInstructions, "stepInstructions");
        this.sortedSteps = new SortedSteps();
        this.selectedSteps = new SelectedSteps();
    }


    public String getSortedSteps() {
        initialiseStarting();

        for (int i = 0; i < stepInstructions.size(); i++) {
            setAvailableStepInstructions(selectedSteps.getNextStepToCheck());

            getNextStepInstructionByAlphabeticalOrder();

            setNextStepToCheck();

            addStepToResult();
        }

        return sortedSteps.getSequenceAsString();
    }

    private void initialiseStarting() {
        findStartingStep();
        setAvailableStarterOptions();
        this.sortedSteps.addStep(selectedSteps.getStartingStep());
        this.selectedSteps.setNextStepToCheck(selectedSteps.getStartingStep());
    }

    public void findStartingStep() {
        final List<Character> steps = new ArrayList<>();
        this.stepInstructions.forEach(stepInstruction -> steps.add(stepInstruction.step()));

        final List<Character> stepsFinishedBefore = new ArrayList<>();
        this.stepInstructions.forEach(stepInstruction -> stepsFinishedBefore.add(stepInstruction.finishedBefore()));

        final List<Character> stepsWithoutPreCondition = new ArrayList<>(steps);
        stepsWithoutPreCondition.removeAll(stepsFinishedBefore);
        final Set<Character> stepsWithoutPreConditionDuplicateFree = new HashSet<>(stepsWithoutPreCondition);

        this.selectedSteps.setStartingSteps(new ArrayList<>(stepsWithoutPreConditionDuplicateFree));
    }


    public void setAvailableStarterOptions() {
        for (final char stepToCompare : selectedSteps.getStartingSteps()) {
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

    private void setNextStepInstruction(final int i) {
        this.nextStepInstruction = availableStepInstructions.get(i);
    }

    private void setUnusedStepInstructions(final int i) {
        final List<StepInstruction> stepInstructionsNotUsed = new ArrayList<>(availableStepInstructions);
        stepInstructionsNotUsed.remove(i);
        this.availableStepInstructionsNotUsed = stepInstructionsNotUsed;
    }

    private void setNextStepToCheck() {
        if (nextStepInstruction != null) {
            selectedSteps.setNextStepToCheck(nextStepInstruction.finishedBefore());
            if (selectedSteps.getNextStepToCheck() == selectedSteps.getLeastStep()) {
                setNextStepWhenDuplicate();
                selectedSteps.setNextStepToCheck(selectedSteps.getStepWhenInstructionIsDuplicated());
            }
        } else {
            setNextStepWhenEmpty();
            selectedSteps.setNextStepToCheck(selectedSteps.getStepWhenInstructionIsEmpty());
        }
    }

    private void setNextStepWhenDuplicate() {
        if (!getAvailableStarterSteps().isEmpty()) {
            selectedSteps.setStepWhenInstructionIsDuplicated(getAvailableStarterSteps().get(0));
        }
    }

    private void setNextStepWhenEmpty() {
        selectedSteps.setStepWhenInstructionIsEmpty(getAvailableStarterSteps().get(0));
    }

    @Nonnull
    private List<Character> getAvailableStarterSteps() {
        final List<Character> starterSteps = new ArrayList<>(selectedSteps.getStartingSteps());
        final List<Character> actualSteps = new ArrayList<>(sortedSteps.getStepSequence());

        starterSteps.removeAll(actualSteps);

        return starterSteps;
    }

    private void addStepToResult() {
        sortedSteps.addStep(selectedSteps.getNextStepToCheck());
        setLeastStep();
    }

    private void setLeastStep() {
        selectedSteps.setLeastStep(selectedSteps.getNextStepToCheck());
    }
}
