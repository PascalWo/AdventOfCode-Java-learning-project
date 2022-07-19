package org.haffson.adventofcode.days.day05;

import org.haffson.adventofcode.utils.FileReaders;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        when(fileReaders.getInputList("src/main/resources/puzzle_input/day5_input.txt"))
                .thenReturn(List.of("dabAcCaCBAcCcaDA"));

        final String expectedResult = "Part 1 - Remaining Units after fully reacting the polymer: " + 10;

        //act
        final String actualResult = day05.firstPart();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void convertStringToCharacterList_returnListOfChars() {
        //arrange
        final Day05 day05 = new Day05(fileReaders);
        final String inputString = "dabAcCaCBAcCcaDA";

//        final List<Character> expectedResult = List.of('d',"a","b","A","c","C","c","a","C","B","A","c","C","c","a","D","A");
        final List<Character> expectedResult = List.of('d','a','b','A','c','C','a','C','B','A','c','C','c','a','D','A');

        //act
        final List<Character> actualResult = day05.convertStringToCharacterList(inputString);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void convertStringListToString_returnString() {
        //arrange
        final Day05 day05 = new Day05(fileReaders);
        final List<String> inputStringList = List.of("dabAcCaCBAcCcaDA");

        final String expectedResult = "dabAcCaCBAcCcaDA";

        //act
        final String actualResult =day05.convertStringListToString(inputStringList);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void removeCharDuplicatesWithDifferentCases_returnListWithoutDuplicatesInDifferentCases() {
        //arrange
        final Day05 day05 = new Day05(fileReaders);
        // new ArrayList because I need a mutable list
        final List<Character> inputList = new ArrayList<>(List.of('d', 'a', 'b', 'A', 'c', 'C', 'a', 'C', 'B', 'A', 'c', 'C', 'c', 'a', 'D', 'A'));

        final List<Character> expectedResult = List.of('d', 'a', 'b', 'C', 'B', 'A', 'c', 'a', 'D', 'A');

        //act
        final List<Character> actualResult = day05.removeCharDuplicatesWithDifferentCases(inputList);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
