package org.haffson.adventofcode.days.day01;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.haffson.adventofcode.utils.ProblemStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

import java.util.HashSet;
import java.util.List;

/**
 * Implementation for <i>Day 1: Chronal Calibration</i>.
 */
@Component
public class Day01 implements Days {

    /** The puzzle status {@code HashMap} */
    private final Map<Integer, ProblemStatusEnum> problemStatus;
    private final FileReaders fileReaders;

    /**
     * Causes the input file to be parsed into the frequencies array ({@code frequencies}).
     *
     * @param fileReaders {@code @Autowired} fileReader //TODO: inject what you need
     */
    public Day01(final FileReaders fileReaders) {
        this.fileReaders = fileReaders;
        this.problemStatus = ProblemStatus.getProblemStatusMap(1, 2,
                ProblemStatusEnum.SOLVED, ProblemStatusEnum.SOLVED);
    }

    @Override
    public int getDay() {
        return 1;
    }

    @Override
    public Map<Integer, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Override
    public String firstPart() {
        final String fileName = "src/main/resources/puzzle_input/day1_input.txt";
        return "Part 1 - Frequency: " + calculateFrequency(fileReaders.getInputList(fileName));
    }

    @Override
    public String secondPart() {
        final String fileName = "src/main/resources/puzzle_input/day1_input.txt";
        return "Part 2 - Frequency: " + calculateFrequencyPart2(fileReaders.getInputList(fileName));
    }

    /**
     * Primary method for Day 1, Part 1.
     * Calculates the final frequency as the sum of all frequencies.
     *
     * @return the final frequency
     */
    private int calculateFrequency(final List<String> myArrayList) {

        return myArrayList.stream().map(Integer::parseInt).toList().stream().mapToInt(Integer::intValue)
                .sum();
    }

    private int calculateFrequencyPart2(final List<String> myArrayList){
        final List<Integer> myIntArrayList = myArrayList.stream().map(Integer::parseInt).toList();

        int sumOfLookedUpListEntries = 0;
        final HashSet<Integer> previousSums = new HashSet<>();
        boolean foundDuplicate = false;
        while (!foundDuplicate) {
            for (final int f : myIntArrayList) {
                sumOfLookedUpListEntries += f;
                if (previousSums.contains(sumOfLookedUpListEntries)) {
                    foundDuplicate = true;
                    break;
                } else {
                    previousSums.add(sumOfLookedUpListEntries);
                }
            }
        }
        return sumOfLookedUpListEntries;
    }
}
