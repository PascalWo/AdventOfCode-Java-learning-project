package org.haffson.adventofcode.days.day07;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.haffson.adventofcode.utils.ProblemStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
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
        final StepSorter stepSorter = new StepSorter(inputList);

        return stepSorter.getSortedSteps();
    }


    private long calculateSecondPart(@Nonnull final List<String> inputStringList) {
        return 0;
    }
}
