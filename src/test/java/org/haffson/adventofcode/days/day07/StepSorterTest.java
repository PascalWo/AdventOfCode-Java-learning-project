package org.haffson.adventofcode.days.day07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StepSorterTest {

    @BeforeEach
    void setUp() {

    }

//    @DisplayName("find starting step character")
//    @Test
//    void findStartingStep_shouldReturnStartingStep() {
//        //arrange
//        final List<StepInstruction> stepInstructions = List.of(new StepInstruction('C', 'A'),
//                new StepInstruction('C', 'F'),
//                new StepInstruction('A', 'B'));
//        final StepSorter stepSorter = new StepSorter(stepInstructions);
//        final List<Character> expectedResult = List.of('C');
//        //act
//        stepSorter.findStartingStep();
//        final List<Character> actualResult = stepSorter.getStartingSteps();
//        //assert
//        assertThat(actualResult).isEqualTo(expectedResult);
//    }

    @Test
    void getSortedSteps_shouldReturnSortedStepsAsString() {
        //arrange
//        final List<StepInstruction> stepInstructions = List.of(new StepInstruction('C', 'A'),
//                new StepInstruction('C', 'F'),
//                new StepInstruction('A', 'B'),
//                new StepInstruction('A', 'D'),
//                new StepInstruction('B', 'E'),
//                new StepInstruction('D', 'E'),
//                new StepInstruction('F', 'E'));

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
