package org.haffson.adventofcode.days.day07;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.haffson.adventofcode.utils.ProblemStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * Implementation for <i>Day 7: The Sum of Its Parts</i>.
 */
@Component
public class Day07 implements Days {

    /**
     * The puzzle status {@code HashMap}
     */
    @Nonnull
    private final Map<Integer, ProblemStatusEnum> problemStatus;
    @Nonnull
    private final FileReaders fileReaders;

    /**
     * Causes the input file to be parsed into the frequencies array ({@code frequencies}).
     *
     * @param fileReaders {@code @Autowired} fileReader //TODO: inject what you need
     */
    public Day07(@Nonnull final FileReaders fileReaders) {
        this.fileReaders = requireNonNull(fileReaders, "fileReaders");
        this.problemStatus = ProblemStatus.getProblemStatusMap(1, 2,
                ProblemStatusEnum.SOLVED, ProblemStatusEnum.SOLVED);
    }

    @Override
    public int getDay() {
        return 7;
    }

    @Nonnull
    @Override
    public Map<Integer, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Nonnull
    @Override
    public String firstPart() {
        final String fileName = "src/main/resources/puzzle_input/day7_input.txt";
        final String part1Result = "Part 1 - : ";
        return part1Result + calculateFirstPart(fileReaders.getInputList(fileName));
    }

    @Nonnull
    @Override
    public String secondPart() {
        final String fileName = "src/main/resources/puzzle_input/day7_input.txt";
        final String part2Result = "Part 2 - : ";
        return part2Result + calculateSecondPart(fileReaders.getInputList(fileName));
    }

    /**
     * Primary method for Day 7, Part 1.
     *
     * @return sorted String of Steps.
     */
    @Nonnull
    private String calculateFirstPart(@Nonnull final List<String> inputStringList) {
        final List<StepInstruction> inputList = StepInstruction.of(inputStringList);
        final List<Step> steps = getSteps(inputList);
        final StepSorter stepSorter = new StepSorter();

        return stepSorter.getSortedSteps(steps);
    }

    private long calculateSecondPart(@Nonnull final List<String> inputStringList) {
        return 0;
    }

    @Nonnull
    private List<Step> getSteps(@Nonnull final List<StepInstruction> instructions) {
        requireNonNull(instructions, "instructions");
        final Map<Character, List<Character>> dependenciesByStep = convertInstructionsToSortedSteps(instructions);
        return Step.of(dependenciesByStep);
    }

    @Nonnull
    private Map<Character, List<Character>> convertInstructionsToSortedSteps(@Nonnull final List<StepInstruction> stepInstructions) {
        requireNonNull(stepInstructions, "stepInstructions");

        final List<Step> steps = convertInstructionsToSteps(stepInstructions);

        final Map<Character, List<List<Character>>> dependenciesByStep = new HashMap<>();

        steps.forEach(step -> {
            if (!dependenciesByStep.containsKey(step.stepName())) {
                final List<List<Character>> dependencyList = new ArrayList<>();

                dependencyList.add(step.dependsOn());

                dependenciesByStep.put(step.stepName(), dependencyList);
            } else {
                dependenciesByStep.get(step.stepName()).add(step.dependsOn());
            }
        });

        return duplicateFreeDependenciesByStep(dependenciesByStep);
    }


    @Nonnull
    private List<Step> convertInstructionsToSteps(@Nonnull final List<StepInstruction> stepInstructions) {
        requireNonNull(stepInstructions, "stepInstructions");

        final List<Step> completeSteps = new ArrayList<>();

        stepInstructions.forEach(stepInstruction -> {
            final Step step1 = new Step(stepInstruction.previousStep(), new ArrayList<>());
            final Step step2 = new Step(stepInstruction.step(), List.of(stepInstruction.previousStep()));

            final List<Step> transformedSteps = List.of(step1, step2);
            completeSteps.addAll(transformedSteps);
        });

        return completeSteps;
    }

    @Nonnull
    private Map<Character, List<Character>> duplicateFreeDependenciesByStep(
            @Nonnull final Map<Character, List<List<Character>>> duplicatedDependenciesByStep) {
        requireNonNull(duplicatedDependenciesByStep, "duplicatedDependenciesByStep");

        final Map<Character, List<Character>> dependenciesByStep = new HashMap<>();

        duplicatedDependenciesByStep.forEach((stepName, stepDependencies) -> dependenciesByStep
                .put(stepName, stepDependencies.stream().flatMap(List::stream).toList()));

        return dependenciesByStep;
    }
}
