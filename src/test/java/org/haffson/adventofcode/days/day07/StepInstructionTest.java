package org.haffson.adventofcode.days.day07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StepInstructionTest {

    @DisplayName("of() should return new StepInstruction")
    @Test
    void of_returnStepInstruction() {
        //arrange
        final String input = "Step C must be finished before step A can begin.";
        final StepInstruction expectedResult = new StepInstruction('C', 'A');
        //act
        final StepInstruction actualResult = StepInstruction.of(input);
        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("of() should return list of new StepInstruction")
    @Test
    void of_returnStepInstructionList() {
        //arrange
        final String input1 = "Step C must be finished before step A can begin.";
        final String input2 = "Step C must be finished before step F can begin.";
        final String input3 = "Step A must be finished before step B can begin.";

        final List<String> inputList = List.of(input1, input2, input3);
        final List<StepInstruction> expectedResult = List.of(new StepInstruction('C', 'A'),
                new StepInstruction('C', 'F'),
                new StepInstruction('A', 'B'));
        //act
        final List<StepInstruction> actualResult = StepInstruction.of(inputList);
        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
