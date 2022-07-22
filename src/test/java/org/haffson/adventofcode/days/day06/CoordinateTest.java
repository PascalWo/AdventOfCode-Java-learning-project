package org.haffson.adventofcode.days.day06;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void of() {
        //arrange
        String input = "78, 335";
        Coordinate expectedResult = new Coordinate(78, 335);

        //act
        Coordinate actualResult = Coordinate.of(input);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}