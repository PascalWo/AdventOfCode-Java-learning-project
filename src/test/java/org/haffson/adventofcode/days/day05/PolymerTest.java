package org.haffson.adventofcode.days.day05;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

class PolymerTest {

    @Test
    void length_returnPolymerValueLength() {
        //arrange
        String inputString = "dabAcCaCBAcCcaDA";
        final Polymer polymer = new Polymer(inputString);

        //act
        final int actualResult = polymer.length();

        //assert
        assertThat(actualResult).isEqualTo(16);
    }

    @Test
    void convertPolymerToCharacterList_returnExpectedList() {
        //arrange
        String inputString = "dabAcCaCBAcCcaDA";
        Polymer polymer = new Polymer(inputString);

        final List<Character> expectedResult = List.of('d', 'a', 'b', 'A', 'c', 'C', 'a', 'C', 'B', 'A', 'c', 'C', 'c', 'a', 'D', 'A');

        //act
        final List<Character> actualResult = polymer.convertPolymerToCharacterList();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void convertCharacterListToString_returnExpectedString() {
        //arrange
        final String inputString = "dabAcCaCBAcCcaDA";
        final Polymer polymer = new Polymer(inputString);

        List<Character> inputCharacterList = List.of('d', 'a', 'b', 'A', 'c', 'C', 'a', 'C', 'B', 'A', 'c', 'C', 'c', 'a', 'D', 'A');

        //act
        final String actualResult = polymer.convertCharacterListToString(inputCharacterList);

        //assert
        assertThat(actualResult).isEqualTo(inputString);
    }

    @Test
    void removeCharDuplicatesWithDifferentCases_returnReactedPolymer() {
        //arrange
        final String inputString = "dabAcCaCBAcCcaDA";
        final Polymer polymer = new Polymer(inputString);

        final Polymer expectedResult = new Polymer("dabCBAcaDA");

        //act
        final Polymer actualResult = polymer.removeCharDuplicatesWithDifferentCases();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void getPolymerLengthByRemovedChar_returnMapOfPolymerLengthByLetter() {
        //arrange
        final String inputString = "dabAcCaCBAcCcaDA";
        final Polymer polymer = new Polymer(inputString);


        final Map<Character, Integer> expectedResult = Map.ofEntries(
                entry('a', 6),
                entry('b', 8),
                entry('c', 4),
                entry('d', 6));

        //act
        final Map<Character, Integer> actualResult = polymer.getPolymerLengthByRemovedChar();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void reactedPolymerLengthWithRemovedLetter_returnPolymerLength() {
        //arrange
        final String inputString = "dabAcCaCBAcCcaDA";
        final Polymer polymer = new Polymer(inputString);
        final Character inputLetter = 'a';

        final int expectedResult = 6;

        //act
        final int actualResult = polymer.reactedPolymerLengthWithRemovedLetter(inputLetter);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void getShortestRemainingUnitEntry_returnShortestMapEntry() {
        //arrange
        final String inputString = "dabAcCaCBAcCcaDA";
        final Polymer polymer = new Polymer(inputString);


        final Map.Entry<Character, Integer> expectedResult = Map.entry('c', 4);

        //act
        final Map.Entry<Character, Integer> actualResult = polymer.getShortestRemainingUnitEntry();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void getShortestRemainingPolymerLength_returnIntOfShortestPolymerLength() {
        //arrange
        final String inputString = "dabAcCaCBAcCcaDA";
        final Polymer polymer = new Polymer(inputString);

        final int expectedResult = 4;

        //act
        final int actualResult = polymer.getShortestRemainingPolymerLength();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void value_returnPolymerValue() {
        //arrange
        final String inputString = "dabAcCaCBAcCcaDA";
        final Polymer polymer = new Polymer(inputString);

        final String expectedResult = "dabAcCaCBAcCcaDA";

        //act
        final String actualResult = polymer.value();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
