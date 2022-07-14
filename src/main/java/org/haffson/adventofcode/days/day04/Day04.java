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
                ProblemStatusEnum.SOLVED, ProblemStatusEnum.SOLVED);
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
        final String fileName = "src/main/resources/puzzle_input/day4_input.txt";
        return "Part 2 - Guard ID multiplied by selected minute: " + calculateMostAsleepMinuteMultipliedByGuardId(fileReaders.getInputList(fileName));
    }

    /**
     * Primary method for Day 4, Part 1.
     * Calculates the guard id who sleeps the most minutes and multiply the id with the minute which he slept the most.
     *
     * @return the multiplication of guard id by selected minute
     */
    private int calculateSearchedMinute(final List<String> inputList) {
        final List<TimeStampInformation> convertedInput = convertStringListToTimeStampList(inputList);
        final List<TimeStampInformation> sortedList = sortListByDate(convertedInput);

        final Map<Integer, List<Integer>> minutesAsleepByGuard = minutesEachGuardIsAsleep(sortedList);

        final Map.Entry<Integer, List<Integer>> guardWithMostMinutesAsleep = findGuardEntryWithMostMinutesAsleep(minutesAsleepByGuard);

        final int minuteWhichGuardIsMostlyAsleep = findMinuteWhichGuardIsMostlyAsleep(guardWithMostMinutesAsleep);

        return guardWithMostMinutesAsleep.getKey() * minuteWhichGuardIsMostlyAsleep;
    }

    /**
     * Auxiliary method for Day 4, Part 1.
     * Converts the String-list to a List of TimeStampInformation.class
     *
     * @return List<TimeStampInformation>
     */
    List<TimeStampInformation> convertStringListToTimeStampList(final List<String> stringInput) {
        return stringInput
                .stream()
                .map(TimeStampInformation::of)
                .toList();
    }

    /**
     * Auxiliary method for Day 4, Part 1.
     * Sorts the List<TimeStampInformation> by its LocalDayDate property.
     *
     * @return (sorted) List<TimeStampInformation>
     */
    List<TimeStampInformation> sortListByDate(final List<TimeStampInformation> stringInput) {
        return stringInput
                .stream()
                .sorted(Comparator.comparing(TimeStampInformation::getTimeStamp))
                .toList();
    }

    /**
     * Auxiliary method for Day 4, Part 1.
     * Maps the TimeStampInformation's to a Map with Key= GuardID, Value= List of Minutes.
     * Each List Entry equals a minute.
     * If a guard is asleep at a certain minute, the value of the minute-index counts up.
     *
     * @return Map<Integer, List<Integer>> Map of minutes each guard is asleep
     */
    Map<Integer, List<Integer>> minutesEachGuardIsAsleep(final List<TimeStampInformation> sortedList) {
        int guardId = -1;
        final Map<Integer, List<Integer>> minutesAsleepByGuard = new HashMap<>();
        LocalDateTime fallsAsleep = LocalDateTime.MIN;

        for (final TimeStampInformation timeStampInformation : sortedList
        ) {
            if (timeStampInformation.getInformation().contains("shift")) {
                guardId = timeStampInformation.getGuardID();
            } else if (timeStampInformation.getInformation().contains("falls")) {
                fallsAsleep = timeStampInformation.getTimeStamp();
            } else {
                final LocalDateTime wakesUp = timeStampInformation.getTimeStamp();
                List<Integer> minutes = minutesAsleepByGuard.get(guardId);
                if (minutes == null) {
                    minutes = new ArrayList<>();
                    for (int i = 0; i < 60; i++) {
                        minutes.add(i, 0);
                    }
                }
                for (int i = fallsAsleep.getMinute(); i < wakesUp.getMinute(); i++) {
                    final Integer minuteIndex = minutes.get(i);
                    minutes.set(i, minuteIndex + 1);
                }

                minutesAsleepByGuard.put(guardId, minutes);
            }
        }
        return minutesAsleepByGuard;
    }

    /**
     * Auxiliary method for Day 4, Part 1.
     * Sums all List-values and compares them to each other.
     * Finds the Entry and List with the most combined minutes asleep.
     *
     * @return Map.Entry<Integer, List<Integer>> guard entry with most minutes asleep
     */
    Map.Entry<Integer, List<Integer>> findGuardEntryWithMostMinutesAsleep(final Map<Integer, List<Integer>> minutesAsleepByGuard) {
        return minutesAsleepByGuard
                .entrySet()
                .stream()
                .max(
                        (guard1, guard2) ->
                                guard1.getValue()
                                        .stream()
                                        .mapToInt(i -> i)
                                        .sum()
                                        - guard2.getValue()
                                        .stream()
                                        .mapToInt(i -> i)
                                        .sum()
                ).orElseThrow(NoSuchElementException::new);
    }

    /**
     * Auxiliary method for Day 4, Part 1.
     * After the Entry with the most asleep minutes is found, this method calculates
     * the minute which the guard is mostly asleep.
     *
     * @return int minute which guard is mostly asleep
     */
    int findMinuteWhichGuardIsMostlyAsleep(final Map.Entry<Integer, List<Integer>> guardEntryWithMostMinutesAsleep) {
        final List<Integer> guardsAsleepMinutes = guardEntryWithMostMinutesAsleep.getValue();

        return guardsAsleepMinutes.indexOf(Collections.max(guardEntryWithMostMinutesAsleep.getValue()));
    }

    /**
     * Primary method for Day 4, Part 2.
     * Calculates the guard id who sleeps more than all other guards at a certain minute and multiply the id with the
     * minute which he slept the most.
     *
     * @return the multiplication of guard id by selected minute
     */
    private int calculateMostAsleepMinuteMultipliedByGuardId(List<String> inputList) {
        final List<TimeStampInformation> convertedInput = convertStringListToTimeStampList(inputList);
        final List<TimeStampInformation> sortedList = sortListByDate(convertedInput);

        final Map<Integer, List<Integer>> minutesAsleepByGuard = minutesEachGuardIsAsleep(sortedList);
        final Map.Entry<Integer, List<Integer>> guardWithCertainMinuteMostAsleep = findGuardEntryWithCertainMinuteMostAsleep(minutesAsleepByGuard);

        final int minuteWhichGuardIsMostlyAsleep = findMinuteWhichGuardIsMostlyAsleep(guardWithCertainMinuteMostAsleep);

        return guardWithCertainMinuteMostAsleep.getKey() * minuteWhichGuardIsMostlyAsleep;
    }

    /**
     * Auxiliary method for Day 4, Part 1.
     * Sums all List-values and compares them to each other.
     * Finds the Entry and List with the highest entry.
     * This is the minute which the guard is mostly asleep.
     *
     * @return Map.Entry<Integer, List < Integer>> guard entry with most minutes asleep
     */
    Map.Entry<Integer, List<Integer>> findGuardEntryWithCertainMinuteMostAsleep(Map<Integer, List<Integer>> minutesAsleepByGuard) {
        return minutesAsleepByGuard
                .entrySet()
                .stream()
                .max(
                        Comparator.comparingInt(guard -> guard.getValue()
                                .stream()
                                .max(Integer::compareTo).orElseThrow(NoSuchElementException::new))
                ).orElseThrow(NoSuchElementException::new);
    }
}
