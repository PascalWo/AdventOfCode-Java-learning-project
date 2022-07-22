package org.haffson.adventofcode.days.day05;

import org.junit.jupiter.api.Test;

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
    void removeCharDuplicatesWithDifferentCases_returnReactedPolymer() {
        //arrange
        final String inputString = "dabAcCaCBAcCcaDA";
        final Polymer polymer = new Polymer(inputString);

        final Polymer expectedResult = new Polymer("dabCBAcaDA");

        //act
        final Polymer actualResult = polymer.react();

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
