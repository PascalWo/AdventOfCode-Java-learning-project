package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class StepSorter {
    private final SortedSteps sortedSteps;

    private final Starter starter;

    public StepSorter() {
        this.sortedSteps = new SortedSteps();
        this.starter = new Starter();
    }

    public String getSortedSteps(@Nonnull final List<Step> stepsInput) {

        final List<Step> steps = new ArrayList<>(stepsInput);
        final Step startingStep = getStartingStep(steps);
        sortedSteps.addStep(startingStep);
        steps.remove(startingStep);
        final List<Step> stepsWithoutStart = new ArrayList<>(steps);

        stepsWithoutStart.forEach(step -> {
            final List<Step> availableSteps = getAvailableSteps(steps);
            final List<Step> sortedAvailableSteps = new ArrayList<>(availableSteps);
            sortedAvailableSteps.sort(Comparator.comparing(Step::getStepName));
            final Step nextStep = sortedAvailableSteps.get(0);
            sortedSteps.addStep(nextStep);
            steps.remove(nextStep);
        });

        return sortedSteps.getStepsAsString();
    }

    private Step getStartingStep(@Nonnull final List<Step> steps) {
        final List<Step> startingSteps = starter.getStartingSteps(steps);
        startingSteps.sort(Comparator.comparing(Step::getStepName));
        return startingSteps.get(0);
    }

    private List<Step> getAvailableSteps(@Nonnull final List<Step> steps) {
        final List<Step> availableSteps = new ArrayList<>();

        steps.forEach(step -> {
            if (new HashSet<>(sortedSteps.getCharacterSequence()).containsAll(step.getDependsOn())) {
                availableSteps.add(step);
            }
        });
        return availableSteps;
    }
}
