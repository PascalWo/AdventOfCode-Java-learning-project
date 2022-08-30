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
import java.util.stream.Collectors;

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
        final StepSorter stepSorter = new StepSorter(inputList);

        return stepSorter.getSortedSteps();
    }

    private long calculateSecondPart(@Nonnull final List<String> inputStringList) {
        return 0;
    }

    List<StepInstruction> getInstructions(@Nonnull final List<String> inputStringList) {
        return StepInstruction.of(inputStringList);

    }

    List<StepInformation> convertInstructionsToSteps(@Nonnull final List<StepInstruction> stepInstructions) {
        return getSteps(stepInstructions);
    }

    @Nonnull
    public List<StepInformation> getSteps(@Nonnull final List<StepInstruction> stepInstructions) {
        requireNonNull(stepInstructions, "stepInstructions");

        final List<StepInformation> combinedStepInformations = new ArrayList<>();

        stepInstructions.forEach(stepInstruction -> {
            final StepInformation stepInformation1 = new StepInformation(stepInstruction.step(), new ArrayList<>());
            final StepInformation step2 = new StepInformation(stepInstruction.finishedBefore(), List.of(stepInstruction.step()));

            final List<StepInformation> extractedStepInformations = List.of(stepInformation1, step2);
            combinedStepInformations.addAll(extractedStepInformations);
        });


        return combinedStepInformations;
    }

    Map<Character,List<Character>> convertInstructionsToSortedSteps(@Nonnull final List<StepInstruction> stepInstructions) {
        List<StepInformation> stepInformations = getSteps(stepInstructions);

        Map<Character,List<List<Character>>> dependenciesByStep = new HashMap<>();

        for (StepInformation stepInformation: stepInformations
             ) {
            if (!dependenciesByStep.containsKey(stepInformation.getStep())) {
                List<List<Character>> dependencyList = new ArrayList<>();

//                List<Character> informatonsList = stepInformation.getDependsOn().stream().flatMap(x -> x.charValue()).toList();

                dependencyList.add(stepInformation.getDependsOn());
//                dependencyList.add(informatonsList);

                dependenciesByStep.put(stepInformation.getStep(), dependencyList);
            } else {
                dependenciesByStep.get(stepInformation.getStep()).add(stepInformation.getDependsOn());
            }
        }

//        Map<Character,List<Character>> cleanedList = dependenciesByStep.values().stream().flatMap(List::stream).toList();
//        Map<Character,List<Character>> cleanedList = dependenciesByStep.forEach(x -> {
//           x.charValue();
//        });

        Map<Character,List<Character>> cleanedList = new HashMap<>();
//
//        for (List<List<Character>> characterList: dependenciesByStep.values()
//             ) {
////            characterList.stream().flatMap(List::stream).toList();
//           dependenciesByStep.values().stream().flatMap(List::stream).toList();
//        }

//        dependenciesByStep.values().forEach(List::stream);

        for (Map.Entry<Character,List<List<Character>>> entry: dependenciesByStep.entrySet()
             ) {
            cleanedList.put(entry.getKey(),entry.getValue().stream().flatMap(List::stream).toList());
        }

        return cleanedList;
    }



    //    public List<StepInformation> cleanSteps(@Nonnull final List<StepInformation> stepInformations){
//        List<StepInformation> cleanedSteps = stepInformations.stream().collect(Collectors.groupingBy(StepInformation::getStep, ))
//
//    }
//    public List<StepInformation> cleanSteps(@Nonnull final List<StepInformation> stepInformations) {
//        List<StepInformation> cleanedSteps = stepInformations.stream()
//
//    }
}
