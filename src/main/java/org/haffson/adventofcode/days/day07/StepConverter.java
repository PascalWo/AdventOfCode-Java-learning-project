package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Objects.requireNonNull;

public class StepConverter {

    @Nonnull
    public List<Step> getSteps(@Nonnull final List<StepInstruction> instructions) {
        requireNonNull(instructions, "instructions");

        final List<Step> steps = convertInstructionsToSteps(instructions);

        return joinSteps(steps);
    }

    @Nonnull
    private List<Step> convertInstructionsToSteps(@Nonnull final List<StepInstruction> instructions) {
        requireNonNull(instructions, "instructions");

        final List<Step> steps = new ArrayList<>();

        instructions.forEach(instruction -> {
            final Step step1 = new Step(instruction.step(), Collections.singletonList(instruction.previousStep()));
            final Step step2 = new Step(instruction.previousStep());

            steps.addAll(List.of(step1, step2));
        });

        return steps;
    }

    @Nonnull
    private List<Step> joinSteps(@Nonnull final List<Step> duplicatedSteps) {
        requireNonNull(duplicatedSteps, "duplicatedSteps");

        final List<Step> joinedSteps = new ArrayList<>();

        duplicatedSteps.forEach(stepToProve -> {
            if (isStepAlreadyIncluded(joinedSteps, stepToProve)) {
                final Step alreadyExistingStep = findDuplicateStep(joinedSteps, stepToProve);
                final Step joinedStep = getStepWithJoinedDependencies(stepToProve, alreadyExistingStep);

                joinedSteps.remove(alreadyExistingStep);
                joinedSteps.add(joinedStep);
            } else {
                joinedSteps.add(stepToProve);
            }
        });

        return joinedSteps;
    }

    private boolean isStepAlreadyIncluded(@Nonnull final List<Step> joinedSteps, @Nonnull final Step stepToProve) {
        requireNonNull(joinedSteps, "joinedSteps");
        requireNonNull(stepToProve, "duplicatedStep");

        return joinedSteps.stream().anyMatch(step -> step.stepName() == stepToProve.stepName());
    }

    @Nonnull
    private Step findDuplicateStep(@Nonnull final List<Step> joinedSteps, @Nonnull final Step stepToProve) {
        requireNonNull(joinedSteps, "steps");
        requireNonNull(stepToProve, "step");

        return joinedSteps.stream().filter(duplicateStep -> stepToProve.stepName() == duplicateStep.stepName()).findFirst().orElseThrow(NoSuchElementException::new);
    }

    @Nonnull
    private Step getStepWithJoinedDependencies(@Nonnull final Step step1, @Nonnull final Step step2) {
        requireNonNull(step1, "step1");
        requireNonNull(step2, "step2");

        final List<Character> dependencies = new ArrayList<>();
        dependencies.addAll(step1.dependsOn());
        dependencies.addAll(step2.dependsOn());

        return new Step(step1.stepName(), dependencies);
    }
}
