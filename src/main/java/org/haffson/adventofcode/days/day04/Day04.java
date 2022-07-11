package org.haffson.adventofcode.days.day04;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.haffson.adventofcode.utils.ProblemStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Implementation for <i>Day 4: Chronal Calibration</i>.
 */
@Component
public class Day04 implements Days {

    /**
     * The puzzle status {@code HashMap}
     */
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

        final Map<Integer, Integer[]> minutesAsleep = minutesEachGuardIsAsleep(sortedList);

        final Map.Entry<Integer, Integer[]> mostMinutesAsleep = findEntryWithMostMinutesAsleep(minutesAsleep);

        final int guardId = findGuardWithMostMinutesAsleep(mostMinutesAsleep);

        return mostMinutesAsleep.getKey() * guardId;
    }

    List<TimeStampInformation> convertStringListToTimeStampList(final List<String> stringInput) {
        return stringInput.stream().map(TimeStampInformation::of).toList();
    }

    List<TimeStampInformation> sortListByDate(final List<TimeStampInformation> stringInput) {
        return stringInput.stream().sorted(Comparator.comparing(TimeStampInformation::getTimeStamp)).toList();
    }

    Map<Integer, Integer[]> minutesEachGuardIsAsleep(final List<TimeStampInformation> sortedList) {
        int guard = -1;
        final Map<Integer, Integer[]> minutesAsleep = new HashMap<>();
        LocalDateTime falls = LocalDateTime.MIN;

        for (final TimeStampInformation timeStampInformation : sortedList
        ) {
            if (timeStampInformation.getInformation().contains("shift")) {
                guard = Integer.parseInt(timeStampInformation.getInformation().split("#")[1].split(" ")[0]);
            } else if (timeStampInformation.getInformation().contains("falls")) {
                falls = timeStampInformation.getTimeStamp();
                if (falls.getHour() == 23) {
                    falls.plusMinutes(60 - falls.getMinute());
                }
            } else {
                final LocalDateTime wakes = timeStampInformation.getTimeStamp();
                Integer[] minutes = minutesAsleep.get(guard);
                if (minutes == null) {
                    minutes = new Integer[60];
                    for (int i = 0; i < 60; i++) minutes[i] = 0;
                }
                for (int i = falls.getMinute(); i < wakes.getMinute(); i++)
                    minutes[i]++;
                minutesAsleep.put(guard, minutes);
            }
        }
        return minutesAsleep;
    }

    Map.Entry<Integer, Integer[]> findEntryWithMostMinutesAsleep(final Map<Integer, Integer[]> minutesAsleep) {
        return minutesAsleep
                .entrySet()
                .stream()
                .max((g1, g2) -> Arrays.stream(g1.getValue())
                        .mapToInt(i -> i)
                        .sum() - Arrays
                        .stream(g2.getValue())
                        .mapToInt(i -> i)
                        .sum())
                .get();
    }

    int findGuardWithMostMinutesAsleep(final Map.Entry<Integer, Integer[]> mostMinutesAsleep) {
        return List.of(mostMinutesAsleep.getValue())
                .indexOf((Arrays.stream(mostMinutesAsleep.getValue())
                        .max(Integer::compareTo)
                        .get()));
    }
}
