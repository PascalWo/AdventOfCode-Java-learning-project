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
        final Step startingStep = getStartingStep(steps);
        sortedSteps.addStep(startingStep);
        steps.remove(startingStep);
        final List<Step> stepsWithoutStart = new ArrayList<>(steps);

        stepsWithoutStart.forEach(step -> addNextStepToResult(steps));

        return sortedSteps.getStepsAsString();
    }


    private void addNextStepToResult(@Nonnull final List<Step> steps){
        requireNonNull(steps, "steps");
        final List<Step> availableSteps = getAvailableSteps(steps);
        final List<Step> sortedAvailableSteps = new ArrayList<>(availableSteps);
        sortedAvailableSteps.sort(Comparator.comparing(Step::getStepName));
        final Step nextStep = sortedAvailableSteps.get(0);
        sortedSteps.addStep(nextStep);
        steps.remove(nextStep);
    }

    @Nonnull
    public List<Step> getStartingSteps(@Nonnull final List<Step> steps) {
        requireNonNull(steps, "steps");
        final List<Step> startingSteps = new ArrayList<>();
        steps.forEach(step -> {
            if (step.getDependsOn().isEmpty()) {
                startingSteps.add(step);
            }
        });

        return startingSteps;
    }

    @Nonnull
    private Step getStartingStep(@Nonnull final List<Step> steps) {
        requireNonNull(steps, "steps");
        final List<Step> startingSteps = getStartingSteps(steps);
        startingSteps.sort(Comparator.comparing(Step::getStepName));
        return startingSteps.get(0);
    }

    @Nonnull
    private List<Step> getAvailableSteps(@Nonnull final List<Step> steps) {
        requireNonNull(steps, "steps");
        final List<Step> availableSteps = new ArrayList<>();

        steps.forEach(step -> {
            if (new HashSet<>(sortedSteps.getCharacterSequence()).containsAll(step.getDependsOn())) {
                availableSteps.add(step);
            }
        });
        return availableSteps;
    }
}
