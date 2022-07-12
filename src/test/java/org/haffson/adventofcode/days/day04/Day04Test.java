package org.haffson.adventofcode.days.day04;

import org.haffson.adventofcode.utils.FileReaders;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class Day04Test {

    private final FileReaders fileReaders = mock(FileReaders.class);

    @Test
    void testGetDay() {
        final Day04 day04 = new Day04(fileReaders);
        final int expectedResult = 4;
        final int actualResult = day04.getDay();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void test_firstPart_returnsExpectedResult() {
        //arrange
        final Day04 day04 = new Day04(fileReaders);
        when(fileReaders.getInputList("src/main/resources/puzzle_input/day4_input.txt"))
                .thenReturn(List.of(
                        "[1518-11-01 00:00] Guard #10 begins shift",
                        "[1518-11-01 00:05] falls asleep",
                        "[1518-11-01 00:25] wakes up",
                        "[1518-11-01 00:30] falls asleep",
                        "[1518-11-01 00:55] wakes up",
                        "[1518-11-01 23:58] Guard #99 begins shift",
                        "[1518-11-02 00:40] falls asleep",
                        "[1518-11-02 00:50] wakes up",
                        "[1518-11-03 00:05] Guard #10 begins shift",
                        "[1518-11-03 00:24] falls asleep",
                        "[1518-11-03 00:29] wakes up",
                        "[1518-11-04 00:02] Guard #99 begins shift",
                        "[1518-11-04 00:36] falls asleep",
                        "[1518-11-04 00:46] wakes up",
                        "[1518-11-05 00:03] Guard #99 begins shift",
                        "[1518-11-05 00:45] falls asleep",
                        "[1518-11-05 00:55] wakes up"
                ));

        final String expectedResult = "Part 1 - Guard ID multiplied by selected minute: " + 240;

        //act
        final String actualResult = day04.firstPart();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testConvertStringToTimeStampInformation_returnTimeStampInformationInstance() {
        //arrange
        final String inputString = "[1518-11-01 00:25] Guard #10 begins shift";

        final TimeStampInformation expectedResult = new TimeStampInformation(
                LocalDateTime.of(1518, 11, 1, 0, 25),
                "Guard #10 begins shift");

        //act
        final TimeStampInformation actualResult = TimeStampInformation.of(inputString);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testConvertStringListToTimeStampList_returnListOfTimeStampInformation() {
        //arrange
        final Day04 day04 = new Day04(fileReaders);
        final List<String> stringList = List.of(
                "[1518-11-01 00:00] Guard #10 begins shift",
                "[1518-11-01 00:05] falls asleep",
                "[1518-11-01 00:25] wakes up"
        );

        final List<TimeStampInformation> expectedResult = List.of(
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 1, 0, 0),
                        "Guard #10 begins shift"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 1, 0, 5),
                        "falls asleep"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 1, 0, 25),
                        "wakes up"));

        //act
        final List<TimeStampInformation> actualResult = day04.convertStringListToTimeStampList(stringList);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testSortListByDate_returnSortedListOfTimeStampInformation() {
        //arrange
        final Day04 day04 = new Day04(fileReaders);
        final List<TimeStampInformation> inputList = List.of(
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 1, 0, 25),
                        "wakes up"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 1, 0, 5),
                        "falls asleep"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 1, 0, 0),
                        "Guard #10 begins shift")
        );

        final List<TimeStampInformation> expectedResult = List.of(
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 1, 0, 0),
                        "Guard #10 begins shift"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 1, 0, 5),
                        "falls asleep"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 1, 0, 25),
                        "wakes up"));

        //act
        final List<TimeStampInformation> actualResult = day04.sortListByDate(inputList);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void testMinutesEachGuardIsAsleep_returnMapOfGuardsWithMinutesAsleep() {
        //arrange
        final Day04 day04 = new Day04(fileReaders);

        final List<TimeStampInformation> inputList = List.of(
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 1, 0, 0),
                        "Guard #10 begins shift"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 1, 0, 5),
                        "falls asleep"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 1, 0, 25),
                        "wakes up"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 1, 0, 30),
                        "falls asleep"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 1, 0, 55),
                        "wakes up"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 1, 23, 58),
                        "Guard #99 begins shift"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 2, 0, 40),
                        "falls asleep"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 2, 0, 50),
                        "wakes up"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 3, 0, 5),
                        "Guard #10 begins shift"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 3, 0, 24),
                        "falls asleep"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 3, 0, 29),
                        "wakes up"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 4, 0, 2),
                        "Guard #99 begins shift"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 4, 0, 36),
                        "falls asleep"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 4, 0, 46),
                        "wakes up"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 5, 0, 3),
                        "Guard #99 begins shift"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 5, 0, 45),
                        "falls asleep"),
                new TimeStampInformation(
                        LocalDateTime.of(1518, 11, 5, 0, 55),
                        "wakes up"));

        final Map<Integer, Integer[]> expectedResult = new HashMap<>();
        expectedResult.put(99,
                new Integer[]
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0});

        expectedResult.put(10,
                new Integer[]
                        {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0});

        //act
        final Map<Integer, Integer[]> actualResult = day04.minutesEachGuardIsAsleep(inputList);

        //assert
        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);
    }
}
