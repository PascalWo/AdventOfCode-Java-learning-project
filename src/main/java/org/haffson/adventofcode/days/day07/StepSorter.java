package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class StepSorter {
    private final SortedSteps sortedSteps;
    private final SelectedSteps selectedSteps;
    private final Starter starter;
    private final SelectedInstructions selectedInstructions;

    public StepSorter(@Nonnull final List<StepInstruction> stepInstructions) {
        this.sortedSteps = new SortedSteps();
        this.selectedInstructions = new SelectedInstructions(stepInstructions, sortedSteps);
        this.selectedSteps = new SelectedSteps();
        this.starter = new Starter(stepInstructions);
    }

    public String getSortedSteps() {
        initialiseStarting();

        for (int i = 0; i < selectedInstructions.getAmountOfInstructions(); i++) {
            selectedInstructions.setAvailableStepInstructions(selectedSteps.getNextStepToCheck());

            selectedInstructions.getNextStepInstructionByAlphabeticalOrder();

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
