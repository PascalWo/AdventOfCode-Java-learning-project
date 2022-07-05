package org.haffson.adventofcode.days.day02;

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
public class Day02Test {

    @MockBean
    private FileReaders fileReaders;

    @Test
    public void testGetDay() {
        Day02 day02 = new Day02(fileReaders);
        int expectedResult = 2;
        int actualResult = day02.getDay();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_firstPart_returnsExpectedResult() {
        //arrange
        Day02 day02 = new Day02(fileReaders);
        when(fileReaders.getInputList("src/main/resources/puzzle_input/day2_input.txt"))
                .thenReturn(new ArrayList<>(Arrays.asList("abcdef","bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")));

        String expectedResult = "Part 1 - checksum: " + 12;

        //act
        String actualResult = day02.firstPart();

        //assert
        Assert.assertEquals(expectedResult, actualResult);
    }
}
