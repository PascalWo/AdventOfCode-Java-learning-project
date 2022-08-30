package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class StepInformation {
    private final char step;

    private final List<Character> dependsOn;

    public StepInformation(final char step, @Nonnull final List<Character> dependsOn) {
        this.step = step;
        this.dependsOn = dependsOn;
    }

    public StepInformation(final char step) {
        this.step = step;
        this.dependsOn = new ArrayList<>();
    }

    public char getStep() {
        return step;
    }

    public List<Character> getDependsOn() {
        return dependsOn;
    }

//    @Nonnull
//    public List<StepInformation> getSteps (@Nonnull final List<StepInstruction> stepInstructions) {
//        requireNonNull(stepInstructions, "stepInstructions");
//
//        final List<StepInformation> combinedStepInformations = new ArrayList<>();
//
//        stepInstructions.forEach(stepInstruction -> {
//            final StepInformation stepInformation1 = new StepInformation(stepInstruction.step(), new ArrayList<>());
//            final StepInformation step2 = new StepInformation(stepInstruction.finishedBefore(), List.of(stepInstruction.step()));
//
//            final List<StepInformation> extractedStepInformations = List.of(stepInformation1, step2);
//            combinedStepInformations.addAll(extractedStepInformations);
//        });
//
//
//        return combinedStepInformations;
//    }

//    public char step() {
//        return step;
//    }
//
//    @Nonnull
//    public List<Character> dependsOn() {
//        return dependsOn;
//    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        final var that = (StepInformation) obj;
        return this.step == that.step &&
                Objects.equals(this.dependsOn, that.dependsOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(step, dependsOn);
    }

    @Override
    public String toString() {
        return "Step[" +
                "step=" + step + ", " +
                "dependsOn=" + dependsOn + ']';
    }

}
