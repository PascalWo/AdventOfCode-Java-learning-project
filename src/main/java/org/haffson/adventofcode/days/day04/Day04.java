package org.haffson.adventofcode.days.day04;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.haffson.adventofcode.utils.ProblemStatus;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Implementation for <i>Day 4: Chronal Calibration</i>.
 */
@Component
public class Day04 implements Days {

    /** The puzzle status {@code HashMap} */
    private final Map<Integer, ProblemStatusEnum> problemStatus;
    private final FileReaders fileReaders;

    /**
     * Causes the input file to be parsed into the frequencies array ({@code frequencies}).
     *
     * @param fileReaders {@code @Autowired} fileReader //TODO: inject what you need
     */
    public Day04(final FileReaders fileReaders) {
        this.fileReaders = fileReaders;
        this.problemStatus = ProblemStatus.getProblemStatusMap(1, 2,
                ProblemStatusEnum.SOLVED, ProblemStatusEnum.UNSOLVED);
    }

    @Override
    public int getDay() {
        return 4;
    }

    @Override
    public Map<Integer, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Override
    public String firstPart() {
        final String fileName = "src/main/resources/puzzle_input/day4_input.txt";
        return "Part 1 - Guard ID multiplied by selected minute: " + calculateSearchedMinute(fileReaders.getInputList(fileName));
    }

    @Override
    public String secondPart() {
        return null;
    }

    /**
     * Primary method for Day 4, Part 1.
     * Calculates the guard id who sleeps the most minutes and multiply the id with the minute which he slept the most.
     *
     * @return the multiplication of guard id by selected minute
     */
    private int calculateSearchedMinute(final List<String> myArrayList) {
        final List<TimeStampInformation> convertedInput = convertStringListToTimeStampList(myArrayList);
        final List<TimeStampInformation> sortedList = sortListByDate(convertedInput);
        return 0;
    }

    List<TimeStampInformation> convertStringListToTimeStampList(final List<String> stringInput){
        return stringInput.stream().map(TimeStampInformation::of).toList();
    }

    List<TimeStampInformation> sortListByDate(final List<TimeStampInformation> stringInput){
        return stringInput.stream().sorted(Comparator.comparing(TimeStampInformation::getTimeStamp)).toList();
    }
}
