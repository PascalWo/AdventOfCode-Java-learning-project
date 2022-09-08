package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
            final Step step1 = new Step(instruction.step(), Collections.singleton(instruction.previousStep()));
            final Step step2 = new Step(instruction.previousStep());

            steps.addAll(List.of(step1, step2));
        });

        return steps;
    }

    @Nonnull
    private List<Step> joinSteps(@Nonnull final List<Step> stepsThatMayContainDuplicates) {
        requireNonNull(stepsThatMayContainDuplicates, "stepsThatMayContainDuplicates");

        final List<Step> joinedSteps = new ArrayList<>();

        stepsThatMayContainDuplicates.forEach(stepToProve -> {
            final Optional<Step> alreadyExistingStep = findDuplicateStep(joinedSteps, stepToProve);
            alreadyExistingStep.ifPresentOrElse(
                    existingStep -> {
                        final Step joinedStep = stepToProve.mergeInDependenciesFrom(existingStep);
                        joinedSteps.remove(existingStep);
                        joinedSteps.add(joinedStep);
                    },
                    () -> joinedSteps.add(stepToProve)
            );
        });

        return joinedSteps;
    }

    @Nonnull
    private Optional<Step> findDuplicateStep(@Nonnull final List<Step> joinedSteps, @Nonnull final Step stepToProve) {
        requireNonNull(joinedSteps, "steps");
        requireNonNull(stepToProve, "step");

        return joinedSteps.stream().filter(duplicateStep -> stepToProve.stepName() == duplicateStep.stepName()).findFirst();
    }
}
