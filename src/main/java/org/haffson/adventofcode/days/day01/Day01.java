package org.haffson.adventofcode.days.day01;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Implementation for <i>Day 1: Chronal Calibration</i>.
 */
@Component
public class Day01 implements Days {

    /** The puzzle status {@code HashMap} */
    private final HashMap<String, ProblemStatusEnum> problemStatus;
    private final FileReaders fileReaders;

    /**
     * Causes the input file to be parsed into the frequencies array ({@code frequencies}).
     *
     * @param fileReaders {@code @Autowired} fileReader //TODO: inject what you need
     */
    @Autowired
    Day01(FileReaders fileReaders) {
        this.fileReaders = fileReaders;
        this.problemStatus = new HashMap<>();
        this.problemStatus.put("1", ProblemStatusEnum.SOLVED);
        this.problemStatus.put("2", ProblemStatusEnum.SOLVED);
    }

    @Override
    public int getDay() {
        return 1;
    }

    @Override
    public HashMap<String, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Override
    public String firstPart() {
        String fileName = "src/main/resources/puzzle_input/day1_input.txt";
        
        return "Part 1 - Frequency: " + calculateFrequency(fileReaders.getInputArray(fileName));
    }

    @Override
    public String secondPart() {
        String fileName = "src/main/resources/puzzle_input/day1_input.txt";
        return "Part 2 - Frequency: " + calculateFrequencyPart2(fileReaders.getInputArray(fileName));
    }

    /**
     * Primary method for Day 1, Part 1.
     * Calculates the final frequency as the sum of all frequencies.
     *
     * @return the final frequency
     */
    private int calculateFrequency(List<Integer> myIntArrayList) {
        return myIntArrayList.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int calculateFrequencyPart2(List<Integer> myIntArrayList){
        int sumOfLookedUpListEntries = 0;
        HashSet<Integer> previousSums = new HashSet<>();
        boolean foundDuplicate = false;
        while (!foundDuplicate) {
            for (int f : myIntArrayList) {
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
