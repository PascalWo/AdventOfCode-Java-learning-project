package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;


public class Starter {

    private List<Step> startingSteps;

    public Starter() {
        this.startingSteps = new ArrayList<>();
    }

    public List<Step> getStartingSteps(@Nonnull final List<Step> steps) {
        final List<Step> startingSteps = new ArrayList<>();
        steps.forEach(step -> {
            if (step.getDependsOn().isEmpty()) {
                startingSteps.add(step);
            }
        });

        return startingSteps;
    }

    public Step getStartingStep(@Nonnull final List<Step> startingSteps) {
        return startingSteps.get(0);
    }
}
