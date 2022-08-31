package org.haffson.adventofcode.days.day07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StepTest {

    @DisplayName("of() should return new List of Steps")
    @Test
    void of_returnListOfSteps() {
        //arrange
        final Map<Character, List<Character>> inputMap = Map.of('A', List.of('C'),
                'F', List.of('C'),
                'B', List.of('A'),
                'D', List.of('A'),
                'E', List.of('B', 'D', 'F'),
                'C', List.of());

        final List<Step> expectedResult = List.of(new Step('A', List.of('C')),
                new Step('F', List.of('C')),
                new Step('B', List.of('A')),
                new Step('D', List.of('A')),
                new Step('E', List.of('B', 'D', 'F')),
                new Step('C'));
        //act
        final List<Step> actualResult = Step.of(inputMap);
        //assert
        assertThat(actualResult).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedResult);
    }
}
