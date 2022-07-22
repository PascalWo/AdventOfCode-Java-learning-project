package org.haffson.adventofcode.days.day05;

import org.haffson.adventofcode.utils.FileReaders;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class Day05Test {

    private final FileReaders fileReaders = mock(FileReaders.class);

    @Test
    void testGetDay() {
        final Day05 day05 = new Day05(fileReaders);
        final int expectedResult = 5;
        final int actualResult = day05.getDay();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void test_firstPart_returnsExpectedResult() {
        //arrange
        final Day05 day05 = new Day05(fileReaders);
        when(fileReaders.getInputString("src/main/resources/puzzle_input/day5_input.txt"))
                .thenReturn("dabAcCaCBAcCcaDA");

        final String expectedResult = "Part 1 - Remaining Units after fully reacting the polymer: " + 10;

        //act
        final String actualResult = day05.firstPart();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void test_secondPart_returnsExpectedResult() {
        //arrange
        final Day05 day05 = new Day05(fileReaders);
        when(fileReaders.getInputString("src/main/resources/puzzle_input/day5_input.txt"))
                .thenReturn("dabAcCaCBAcCcaDA");

        final String expectedResult = "Part 2 - Shortest remaining Units after removing one letter and fully reacting the polymer: " + 4;

        //act
        final String actualResult = day05.secondPart();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
