package org.haffson.adventofcode.days.day01;

import org.haffson.adventofcode.utils.FileReaders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

class Day01Test {

    private final FileReaders fileReaders=mock(FileReaders.class);

    @Test
    void testGetDay() {
        final Day01 day01 = new Day01(fileReaders);
        final int expectedResult = 1;
        final int actualResult = day01.getDay();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_firstPart_returnsExpectedResult() {
        //arrange
        final Day01 day01 = new Day01(fileReaders);
        when(fileReaders.getInputList("src/main/resources/puzzle_input/day1_input.txt"))
                .thenReturn(new ArrayList<>(Arrays.asList("1","2","3","-4")));

        final String expectedResult = "Part 1 - Frequency: " + 2;

        //act
        final String actualResult = day01.firstPart();

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_secondPart_returnsExpectedResult() {
        //arrange
        final Day01 day01 = new Day01(fileReaders);
        when(fileReaders.getInputList("src/main/resources/puzzle_input/day1_input.txt"))
                .thenReturn(new ArrayList<>(Arrays.asList("3","3","4","-2","-4")));

        final String expectedResult = "Part 2 - Frequency: " + 10;

        //act
        final String actualResult = day01.secondPart();

        //assert
        assertEquals(expectedResult, actualResult);
    }
}
