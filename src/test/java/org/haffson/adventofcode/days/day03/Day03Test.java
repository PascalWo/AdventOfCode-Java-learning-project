package org.haffson.adventofcode.days.day03;

import org.haffson.adventofcode.utils.FileReaders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class Day03Test {

    private final FileReaders fileReaders=mock(FileReaders.class);

    @Test
    void testGetDay() {
        final Day03 day03 = new Day03(fileReaders);
        final int expectedResult = 3;
        final int actualResult = day03.getDay();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_firstPart_returnsExpectedResult() {
        //arrange
        final Day03 day03 = new Day03(fileReaders);
        when(fileReaders.getInputList("src/main/resources/puzzle_input/day3_input.txt"))
                .thenReturn(new ArrayList<>(Arrays.asList(
                        "#1 @ 1,3: 4x4",
                        "#2 @ 3,1: 4x4",
                        "#3 @ 5,5: 2x2",
                        "#4 @ 1,1: 6x6",
                        "#5 @ 3,2: 3x3"
                )));

        final String expectedResult = "Part 1 - two or more claims: " + 32;

        //act
        final String actualResult = day03.firstPart();

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testConvertStringToRectangleList_returnListOfRectangles() {
        //arrange
        final Day03 day03 = new Day03(fileReaders);
        final List<String> stringList = Arrays.asList(
                "#1 @ 1,3: 4x4",
                "#2 @ 3,1: 4x4",
                "#3 @ 5,5: 2x2"
        );

        final List<RectangleClaim> expectedResult = Arrays.asList(
                new RectangleClaim("1", 1, 3, 4, 4),
                new RectangleClaim("2", 3, 1, 4, 4),
                new RectangleClaim("3", 5, 5, 2, 2));

        //act
        final List<RectangleClaim> actualResult = day03.convertStringToRectangleList(stringList);

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_secondPart_returnsExpectedResult() {
        //arrange
        final Day03 day03 = new Day03(fileReaders);
        when(fileReaders.getInputList("src/main/resources/puzzle_input/day3_input.txt"))
                .thenReturn(new ArrayList<>(Arrays.asList(
                        "#1 @ 1,3: 4x4",
                        "#2 @ 3,1: 4x4",
                        "#3 @ 5,5: 2x2",
                        "#5 @ 3,2: 3x3"
                )));

        final String expectedResult = "Part 2 - not overlapping claim: " + 3;

        //act
        final String actualResult = day03.secondPart();

        //assert
        assertEquals(expectedResult, actualResult);
    }
}
