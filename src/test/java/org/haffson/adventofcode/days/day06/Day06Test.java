package org.haffson.adventofcode.days.day06;

import org.haffson.adventofcode.utils.FileReaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day06Test {

    private Day06 day06;

    @BeforeEach
    void setUp() {
        final FileReaders fileReaders = mock(FileReaders.class);
        day06 = new Day06(fileReaders, 32);

        when(fileReaders.getInputList("src/main/resources/puzzle_input/day6_input.txt"))
                .thenReturn(List.of(
                        "1, 1",
                        "1, 6",
                        "8, 3",
                        "3, 4",
                        "5, 5",
                        "8, 9"
                ));
    }

    @DisplayName("get the current AoC Day")
    @Test
    void testGetDay() {
        final int expectedResult = 6;
        final int actualResult = day06.getDay();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("First part - should return largest coordinate area")
    @Test
    void test_firstPart_returnsExpectedResult() {
        //arrange
        final String expectedResult = "Part 1 - : " + 17;

        //act
        final String actualResult = day06.firstPart();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("Second part - should return largest area between given distance")
    @Test
    void test_secondPart_returnsExpectedResult() {
        //arrange
        final String expectedResult = "Part 2 - : " + 16;

        //act
        final String actualResult = day06.secondPart();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
