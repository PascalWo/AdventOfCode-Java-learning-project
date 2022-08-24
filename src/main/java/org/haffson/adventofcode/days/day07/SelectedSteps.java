package org.haffson.adventofcode.days.day07;

public class SelectedSteps {

    private char leastStep;
    private char nextStepToCheck;
    private char stepWhenInstructionIsEmpty;
    private char stepWhenInstructionIsDuplicated;


    public SelectedSteps() {
        this.leastStep = Character.MIN_VALUE;
        this.nextStepToCheck = Character.MIN_VALUE;
        this.stepWhenInstructionIsEmpty = Character.MIN_VALUE;
        this.stepWhenInstructionIsDuplicated = Character.MIN_VALUE;
    }

    public char getLeastStep() {
        return leastStep;
    }

    public void setLeastStep(final char leastStep) {
        this.leastStep = leastStep;
    }

    public char getNextStepToCheck() {
        return nextStepToCheck;
    }

    public void setNextStepToCheck(final char nextStepToCheck) {
        this.nextStepToCheck = nextStepToCheck;
    }

    public char getStepWhenInstructionIsEmpty() {
        return stepWhenInstructionIsEmpty;
    }

    public void setStepWhenInstructionIsEmpty(final char stepWhenInstructionIsEmpty) {
        this.stepWhenInstructionIsEmpty = stepWhenInstructionIsEmpty;
    }

    public char getStepWhenInstructionIsDuplicated() {
        return stepWhenInstructionIsDuplicated;
    }

    public void setStepWhenInstructionIsDuplicated(final char stepWhenInstructionIsDuplicated) {
        this.stepWhenInstructionIsDuplicated = stepWhenInstructionIsDuplicated;
    }
}
