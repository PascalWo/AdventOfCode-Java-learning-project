package org.haffson.adventofcode.days.day07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StepConverterTest {

    @DisplayName("Should return a List of steps when stepInstructions are given")
    @Test
    void getSteps() {
        //arrange
        final List<StepInstruction> stepInstructions = List.of(new StepInstruction('C', 'A'),
                new StepInstruction('C', 'F'),
                new StepInstruction('A', 'B'),
                new StepInstruction('A', 'D'),
                new StepInstruction('B', 'E'),
                new StepInstruction('D', 'E'),
                new StepInstruction('F', 'E'));

        final List<Step> expectedResult = List.of(
                new Step('A', List.of('C')),
                new Step('F', List.of('C')),
                new Step('B', List.of('A')),
                new Step('D', List.of('A')),
                new Step('E', List.of('B', 'D', 'F')),
                new Step('C'));
        //actual
        final StepConverter stepConverter = new StepConverter();
        final List<Step> actualResult = stepConverter.getSteps(stepInstructions);

        //assert
        assertThat(actualResult).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedResult);
    }
}
