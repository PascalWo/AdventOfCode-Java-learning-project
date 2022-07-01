package org.haffson.adventofcode.days.day01;

import org.haffson.adventofcode.utils.FileReaders;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class Day01Test {

    @MockBean
    private FileReaders fileReaders;

    @Test
    public void testGetDay() {
        Day01 day01 = new Day01(fileReaders);
        int expectedResult = 1;
        int actualResult = day01.getDay();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_firstPart_returnsExpectedResult() {
        //arrange
        Day01 day01 = new Day01(fileReaders);
        when(fileReaders.getInputArray("src/main/resources/puzzle_input/day1_input.txt"))
                .thenReturn(new int[]{1,2,3,-4});

        String expectedResult = "Part 1 - Frequency: " + 2;

        //act
        String actualResult = day01.firstPart();

        //assert
        Assert.assertEquals(expectedResult, actualResult);
    }
}
