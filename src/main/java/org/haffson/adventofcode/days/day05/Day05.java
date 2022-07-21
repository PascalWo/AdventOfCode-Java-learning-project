package org.haffson.adventofcode.days.day05;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.haffson.adventofcode.utils.ProblemStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Implementation for <i>Day 5: Alchemical Reduction</i>.
 */
@Component
public class Day05 implements Days {

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
    public Day05(@Nonnull final FileReaders fileReaders) {
        this.fileReaders = fileReaders;
        this.problemStatus = ProblemStatus.getProblemStatusMap(1, 2,
                ProblemStatusEnum.SOLVED, ProblemStatusEnum.SOLVED);
    }

    @Override
    public int getDay() {
        return 5;
    }

    @Nonnull
    @Override
    public Map<Integer, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Nonnull
    @Override
    public String firstPart() {
        final String fileName = "src/main/resources/puzzle_input/day5_input.txt";
        final String part1Result = "Part 1 - Remaining Units after fully reacting the polymer: ";
        return part1Result + calculateRemainingUnits(fileReaders.getInputString(fileName));
    }

    @Nonnull
    @Override
    public String secondPart() {
        final String fileName = "src/main/resources/puzzle_input/day5_input.txt";
        final String part2Result = "Part 2 - Shortest remaining Units after removing one letter and fully reacting the polymer: ";
        return part2Result + calculateShortestRemainingUnits(fileReaders.getInputString(fileName));
    }

    /**
     * Primary method for Day 5, Part 1.
     * Calculates the remaining Units after the scanned polymer fully react.
     *
     * @return Int of remaining Units
     */
    private int calculateRemainingUnits(@Nonnull final String inputString) {
        Polymer polymer = new Polymer(inputString);

        return polymer.react().length();
    }

    /**
     * Primary method for Day 5, Part 2.
     * Eliminates one letter from the polymer and let it fully react.
     * Searches for the letter which produces the shortest remaining units and returns its length.
     *
     * @return Int of remaining Units
     */
    private int calculateShortestRemainingUnits(@Nonnull final String inputString) {
        Polymer polymer = new Polymer(inputString);

        return polymer.getShortestRemainingPolymerLength();
    }
}
