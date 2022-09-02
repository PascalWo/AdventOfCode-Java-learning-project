package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class StepSorter {
    @Nonnull
    private final SortedSteps sortedSteps;

    public StepSorter() {
        this.sortedSteps = new SortedSteps();
    }

    @Nonnull
    public String getSortedSteps(@Nonnull final List<Step> stepsInput) {
        requireNonNull(stepsInput, "stepsInput");

        final List<Step> steps = new ArrayList<>(stepsInput);

        stepsInput.forEach(step -> addNextStepToResult(steps));

        return sortedSteps.getStepsAsString();
    }


    private void addNextStepToResult(@Nonnull final List<Step> steps) {
        requireNonNull(steps, "steps");
        final List<Step> availableSteps = getAvailableSteps(steps);
        final List<Step> sortedAvailableSteps = new ArrayList<>(availableSteps);
        sortedAvailableSteps.sort(Comparator.comparing(Step::stepName));
        final Step nextStep = sortedAvailableSteps.get(0);
        sortedSteps.addStep(nextStep);
        steps.remove(nextStep);
    }

    @Nonnull
    private List<Step> getAvailableSteps(@Nonnull final List<Step> steps) {
        requireNonNull(steps, "steps");
        final List<Step> availableSteps = new ArrayList<>();

        steps.forEach(step -> {
            if (new HashSet<>(sortedSteps.getCharacterSequence()).containsAll(step.dependsOn())) {
                availableSteps.add(step);
            }
        });
        return availableSteps;
    }
}
