package org.haffson.adventofcode.days.day04;

import org.haffson.adventofcode.utils.FileReaders;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class Day04Test {

    private final FileReaders fileReaders=mock(FileReaders.class);

    @Test
    void testGetDay() {
        final Day04 day04 = new Day04(fileReaders);
        final int expectedResult = 4;
        final int actualResult = day04.getDay();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_firstPart_returnsExpectedResult() {
        //arrange
        final Day04 day04 = new Day04(fileReaders);
        when(fileReaders.getInputList("src/main/resources/puzzle_input/day3_input.txt"))
                .thenReturn(List.of("""
                        [1518-11-01 00:00] Guard #10 begins shift
                        [1518-11-01 00:05] falls asleep
                        [1518-11-01 00:25] wakes up
                        [1518-11-01 00:30] falls asleep
                        [1518-11-01 00:55] wakes up
                        [1518-11-01 23:58] Guard #99 begins shift
                        [1518-11-02 00:40] falls asleep
                        [1518-11-02 00:50] wakes up
                        [1518-11-03 00:05] Guard #10 begins shift
                        [1518-11-03 00:24] falls asleep
                        [1518-11-03 00:29] wakes up
                        [1518-11-04 00:02] Guard #99 begins shift
                        [1518-11-04 00:36] falls asleep
                        [1518-11-04 00:46] wakes up
                        [1518-11-05 00:03] Guard #99 begins shift
                        [1518-11-05 00:45] falls asleep
                        [1518-11-05 00:55] wakes up
                        """));

        final String expectedResult = "Part 1 - Guard ID multiplied by selected minute: " + 240;

        //act
        final String actualResult = day04.firstPart();

        //assert
        assertEquals(expectedResult, actualResult);
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
        assertEquals(expectedResult, actualResult);
    }
}
