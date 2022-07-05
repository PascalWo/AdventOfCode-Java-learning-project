package org.haffson.adventofcode.days.day03;

import org.haffson.adventofcode.days.day01.Day01;
import org.haffson.adventofcode.utils.FileReaders;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class Day03Test {

    @MockBean
    private FileReaders fileReaders;

    @Test
    public void testGetDay() {
        final Day03 day03 = new Day03(fileReaders);
        final int expectedResult = 3;
        final int actualResult = day03.getDay();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_firstPart_returnsExpectedResult() {
        //arrange
        final Day03 day03 = new Day03(fileReaders);
        when(fileReaders.getInputList("src/main/resources/puzzle_input/day3_input.txt"))
                .thenReturn(new ArrayList<>(Arrays.asList(
                        "#1 @ 1,3: 4x4",
                        "#2 @ 3,1: 4x4",
                        "#3 @ 5,5: 2x2",
                        "#5 @ 3,2: 3x3"
                )));

        final String expectedResult = "Part 1 - two or more claims: " + 9;

        //act
        final String actualResult = day03.firstPart();

        //assert
        Assert.assertEquals(expectedResult, actualResult);
    }


}
