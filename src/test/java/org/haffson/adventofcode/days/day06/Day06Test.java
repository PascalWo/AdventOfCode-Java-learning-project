package org.haffson.adventofcode.days.day06;

import org.haffson.adventofcode.utils.FileReaders;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day06Test {

    private final FileReaders fileReaders = mock(FileReaders.class);

    @Test
    void testGetDay() {
        final Day06 day06 = new Day06(fileReaders, 32);
        final int expectedResult = 6;
        final int actualResult = day06.getDay();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void test_firstPart_returnsExpectedResult() {
        //arrange
        final Day06 day06 = new Day06(fileReaders, 32);
        when(fileReaders.getInputList("src/main/resources/puzzle_input/day6_input.txt"))
                .thenReturn(List.of(
                        "1, 1",
                        "1, 6",
                        "8, 3",
                        "3, 4",
                        "5, 5",
                        "8, 9"
                ));

        final String expectedResult = "Part 1 - : " + 17;

        //act
        final String actualResult = day06.firstPart();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void test_secondPart_returnsExpectedResult() {
        //arrange
        final Day06 day06 = new Day06(fileReaders, 32);
//        day06.maxDistance = 32;


        when(fileReaders.getInputList("src/main/resources/puzzle_input/day6_input.txt"))
                .thenReturn(List.of(
                        "1, 1",
                        "1, 6",
                        "8, 3",
                        "3, 4",
                        "5, 5",
                        "8, 9"
                ));

        final String expectedResult = "Part 2 - : " + 16;

        //act
        final String actualResult = day06.secondPart();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
