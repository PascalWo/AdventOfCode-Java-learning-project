package org.haffson.adventofcode.days.day06;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void of_returnSingleCoordinate() {
        //arrange
        String input = "78, 335";
        Coordinate expectedResult = new Coordinate(78, 335);

        //act
        Coordinate actualResult = Coordinate.of(input);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void of_returnCoordinateList() {
        //arrange
        List<String> stringList = List.of("1, 1", "1, 6", "8, 3", "3, 4", "5, 5", "8, 9");

        List<Coordinate> expectedResult = List.of(
                new Coordinate(1, 1),
                new Coordinate(1, 6),
                new Coordinate(8, 3),
                new Coordinate(3, 4),
                new Coordinate(5, 5),
                new Coordinate(8, 9));

        //act
        List<Coordinate> actualResult = Coordinate.of(stringList);
        System.out.println(actualResult);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}