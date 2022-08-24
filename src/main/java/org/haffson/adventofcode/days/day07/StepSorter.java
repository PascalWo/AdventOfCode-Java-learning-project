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

//        for (int i = 0; i < selectedInstructions.getAmountOfInstructions(); i++) {
        selectedInstructions.getStepInstructions().forEach(stepInstruction -> {
            addNextStepToResult();

//            setAvailableStepInstructions();
//            selectedInstructions.setNextStepInstructionByAlphabeticalOrder();
//            setNextStepToCheck();
//            addStepToResult();
        });

        return sortedSteps.getSequenceAsString();
    }

    private void addNextStepToResult() {
//        final List<StepInstruction> availableStepInstructions = getAvailableStepInstructions();
        setAvailableStepInstructions();

        selectedInstructions.setNextStepInstructionByAlphabeticalOrder();

        setNextStepToCheck();

        addStepToResult();
    }
    private void setAvailableStepInstructions(){
        selectedInstructions.setAvailableStepInstructions(selectedSteps.getNextStepToCheck());
    }

//    private List<StepInstruction> getAvailableStepInstructions(){
//        return selectedInstructions.setAvailableStepInstructions(selectedSteps.getNextStepToCheck());
//    }

    private void initialiseStarting() {
        starter.setStartingSteps();
        selectedInstructions.setAvailableStarterOptions(starter);
        sortedSteps.addStep(starter.getStartingStep());
        selectedSteps.setNextStepToCheck(starter.getStartingStep());
    }

    private void setNextStepToCheck() {
        if (isNextStepInstructionEmpty()) {
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

    private boolean isNextStepInstructionEmpty(){
        return selectedInstructions.getNextStepInstruction() != null;
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
        selectedSteps.setLeastStep();
    }
}
