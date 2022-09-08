package org.haffson.adventofcode.days.day07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class StepSorterTest {

    @DisplayName("Should return the steps in correct order")
    @Test
    void getSortedSteps_shouldReturnSortedStepsAsString() {
        //arrange
        final List<Step> steps = List.of(new Step('A', Set.of('C')),
                new Step('F', Set.of('C')),
                new Step('B', Set.of('A')),
                new Step('D', Set.of('A')),
                new Step('E', Set.of('B', 'D', 'F')),
                new Step('C'));

        final StepSorter stepSorter = new StepSorter();

        final SortedSteps expectedResult = new SortedSteps();
        expectedResult.addStep(new Step('C'));
        expectedResult.addStep(new Step('A', Set.of('C')));
        expectedResult.addStep(new Step('B', Set.of('A')));
        expectedResult.addStep(new Step('D', Set.of('A')));
        expectedResult.addStep(new Step('F', Set.of('C')));
        expectedResult.addStep(new Step('E', Set.of('B', 'D', 'F')));
        //act
        final SortedSteps actualResult = stepSorter.getSortedSteps(steps);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
