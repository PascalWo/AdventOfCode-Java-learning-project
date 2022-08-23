package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class StepSorter {

    //    @Nonnull
//    private final List<StepInstruction> stepInstructions;
//    @Nonnull
//    private List<StepInstruction> availableStepInstructions = new ArrayList<>();
//    @Nonnull
//    private List<StepInstruction> availableStepInstructionsNotUsed = new ArrayList<>();
//    private StepInstruction nextStepInstruction;
    private final SortedSteps sortedSteps;
    private final SelectedSteps selectedSteps;
    private final Starter starter;
    private final SelectedInstructions selectedInstructions;

    public StepSorter(@Nonnull final List<StepInstruction> stepInstructions) {
        this.selectedInstructions = new SelectedInstructions(stepInstructions);
        this.sortedSteps = new SortedSteps();
        this.selectedSteps = new SelectedSteps();
        this.starter = new Starter(stepInstructions);
    }

    public String getSortedSteps() {
        initialiseStarting();

        for (int i = 0; i < selectedInstructions.getAmountOfInstructions(); i++) {
            selectedInstructions.setAvailableStepInstructions(selectedSteps.getNextStepToCheck());

            selectedInstructions.getNextStepInstructionByAlphabeticalOrder(sortedSteps);

            setNextStepToCheck();

            addStepToResult();
        }

        return sortedSteps.getSequenceAsString();
    }

    private void initialiseStarting() {
        starter.setStartingSteps();
        selectedInstructions.setAvailableStarterOptions(starter);
        this.sortedSteps.addStep(starter.getStartingStep());
        this.selectedSteps.setNextStepToCheck(starter.getStartingStep());
    }
//    public void setAvailableStarterOptions() {
//        for (final char stepToCompare : startingSteps.getStartingSteps()) {
//            selectedInstructions.availableStepInstructionsNotUsed.addAll(getAvailableStepInstructions(stepToCompare));
//        }
//    }

//    private void setAvailableStepInstructions(final char stepToCompare) {
//        final Set<StepInstruction> availableInstructions = new HashSet<>();
//
//        availableInstructions.addAll(getAvailableStepInstructions(stepToCompare));
//        availableInstructions.addAll(availableStepInstructionsNotUsed);
//
//        this.availableStepInstructions = new ArrayList<>(availableInstructions);
//    }

//    @Nonnull
//    private List<StepInstruction> getAvailableStepInstructions(final char stepToCompare) {
//        return List.copyOf(stepInstructions).stream()
//                .filter(stepInstruction -> stepToCompare == stepInstruction.step()).
//                toList();
//    }

//    private void getNextStepInstructionByAlphabeticalOrder() {
//        selectedInstructions.sortInstructionsAlphabeticalByFinishedBefore();
//        for (int i = 0; i < selectedInstructions.getAmountOfAvailableStepInstructions(); i++) {
//            if (isNextStepInstruction(i)) {
//                setNextStepInstruction(i);
//                setUnusedStepInstructions(i);
//                break;
//            }
//        }
//    }

//    private void sortInstructionsAlphabeticalByFinishedBefore() {
//        this.availableStepInstructions = availableStepInstructions
//                .stream()
//                .sorted(Comparator.comparing(StepInstruction::finishedBefore))
//                .toList();
//    }

//    private boolean isNextStepInstruction(final int i) {
//        final StepInstruction possibleNextInstruction = availableStepInstructions.get(i);
//
//        return isPrerequisiteComplete(possibleNextInstruction);
//    }

//    private boolean isPrerequisiteComplete(@Nonnull final StepInstruction instructionToCheck) {
//        final char stepToCheck = instructionToCheck.finishedBefore();
//
//        if (stepIsAlreadyUsed(stepToCheck)) {
//            return false;
//        }
//        final List<Character> stepsNeededToBeCompleted = getStepsNeedToBeCompleted(stepToCheck);
//
//        return isEveryNeededStepCompleted(stepsNeededToBeCompleted);
//    }

//    private boolean stepIsAlreadyUsed(final char stepToCheck) {
//        return sortedSteps.getStepSequence().contains(stepToCheck);
//    }

//    @Nonnull
//    private List<Character> getStepsNeedToBeCompleted(final char stepToCheck) {
//        return List.copyOf(stepInstructions)
//                .stream()
//                .filter(stepInstruction -> stepToCheck == stepInstruction.finishedBefore())
//                .map(StepInstruction::step)
//                .toList();
//    }

//    private boolean isEveryNeededStepCompleted(@Nonnull final List<Character> stepsNeededToBeCompleted) {
//        return new HashSet<>(sortedSteps.getStepSequence()).containsAll(stepsNeededToBeCompleted);
//    }

//    private void setNextStepInstruction(final int i) {
//        this.nextStepInstruction = availableStepInstructions.get(i);
//    }
//
//    private void setUnusedStepInstructions(final int i) {
//        final List<StepInstruction> stepInstructionsNotUsed = new ArrayList<>(availableStepInstructions);
//        stepInstructionsNotUsed.remove(i);
//        this.availableStepInstructionsNotUsed = stepInstructionsNotUsed;
//    }

    private void setNextStepToCheck() {
        if (selectedInstructions.getNextStepInstruction() != null) {
            selectedSteps.setNextStepToCheck(selectedInstructions.getNextStepInstruction().finishedBefore());
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
        final List<Character> starterSteps = new ArrayList<>(starter.getStartingSteps());
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
