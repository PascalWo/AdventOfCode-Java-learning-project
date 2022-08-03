package org.haffson.adventofcode.days.day06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinateTest {

    @DisplayName("of() should return single coordinate")
    @Test
    void of_returnSingleCoordinate() {
        //arrange
        final String input = "78, 335";
        final Coordinate expectedResult = new Coordinate(78, 335);

        //act
        final Coordinate actualResult = Coordinate.of(input);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("of() should return List of Coordinates")
    @Test
    void of_returnCoordinateList() {
        //arrange
        final List<String> stringList = List.of("1, 1", "1, 6", "8, 3", "3, 4", "5, 5", "8, 9");

        final List<Coordinate> expectedResult = List.of(
                new Coordinate(1, 1),
                new Coordinate(1, 6),
                new Coordinate(8, 3),
                new Coordinate(3, 4),
                new Coordinate(5, 5),
                new Coordinate(8, 9));

        //act
        final List<Coordinate> actualResult = Coordinate.of(stringList);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("Constructor with a List should return a Coordinate System")
    @Test
    void constructorForCoordinateArea_shouldReturnAreaWithCoordinateSystem() {
        //arrange
        final List<Coordinate> coordinates = List.of(
                new Coordinate(1, 1),
                new Coordinate(1, 6),
                new Coordinate(8, 3),
                new Coordinate(3, 4),
                new Coordinate(5, 5),
                new Coordinate(8, 9));

        final CoordinateAreas expectedResult = new CoordinateAreas(coordinates, new String[9][10]);
        //act

        final CoordinateAreas actualResult = new CoordinateAreas(coordinates);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
