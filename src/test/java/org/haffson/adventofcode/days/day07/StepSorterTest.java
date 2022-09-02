package org.haffson.adventofcode.days.day07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StepSorterTest {

    @DisplayName("Should return the steps in correct order")
    @Test
    void getSortedSteps_shouldReturnSortedStepsAsString() {
        //arrange
        final List<Step> steps = List.of(new Step('A', List.of('C')),
                new Step('F', List.of('C')),
                new Step('B', List.of('A')),
                new Step('D', List.of('A')),
                new Step('E', List.of('B', 'D', 'F')),
                new Step('C'));

        final StepSorter stepSorter = new StepSorter();

        final String expectedResult = "CABDFE";
        //act
        final String actualResult = stepSorter.getSortedSteps(steps);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
