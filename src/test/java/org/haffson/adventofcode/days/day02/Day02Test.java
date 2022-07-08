package org.haffson.adventofcode.days.day02;

import org.haffson.adventofcode.utils.FileReaders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

class Day02Test {

    @MockBean
    private FileReaders fileReaders;

    @Test
    void testGetDay() {
        final Day02 day02 = new Day02(fileReaders);
        final int expectedResult = 2;
        final int actualResult = day02.getDay();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_firstPart_returnsExpectedResult() {
        //arrange
        final Day02 day02 = new Day02(fileReaders);
        when(fileReaders.getInputList("src/main/resources/puzzle_input/day2_input.txt"))
                .thenReturn(new ArrayList<>(Arrays.asList("abcdef","bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")));

        final String expectedResult = "Part 1 - checksum: " + 12;

        //act
        final String actualResult = day02.firstPart();

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_secondPart_returnsExpectedResult() {
        //arrange
        final Day02 day02 = new Day02(fileReaders);
        when(fileReaders.getInputList("src/main/resources/puzzle_input/day2_input.txt"))
                .thenReturn(new ArrayList<>(Arrays.asList("abcde","fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz")));

        final String expectedResult = "Part 2 - common letters: " + "fgij";

        //act
        final String actualResult = day02.secondPart();

        //assert
        assertEquals(expectedResult, actualResult);
    }
}
