package org.haffson.adventofcode.days.day07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SortedStepsTest {

    @DisplayName("Should return a char List of StepNames")
    @Test
    void getCharacterSequence() {
        //arrange
        final List<Step> steps = List.of(new Step('A', List.of('C')),
                new Step('F', List.of('C')),
                new Step('B', List.of('A')),
                new Step('D', List.of('A')),
                new Step('E', List.of('B', 'D', 'F')),
                new Step('C'));

        final List<Character> expectedResult = List.of('A', 'F', 'B', 'D', 'E', 'C');

        //actual
        final SortedSteps sortedSteps = new SortedSteps(steps);
        final List<Character> actualResult = sortedSteps.getCharacterSequence();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("Should add Step to existing steps")
    @Test
    void addStep() {
        //arrange
        final Step existingStep = new Step('F', List.of('C'));
        final Step step = new Step('A', List.of('C'));

        final SortedSteps expectedResult = new SortedSteps(List.of(existingStep, step));
        //actual
        final SortedSteps actualResult = new SortedSteps();
        actualResult.addStep(existingStep);
        actualResult.addStep(step);

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("Should return a String of the SortedSteps")
    @Test
    void getStepsAsString() {
        //arrange
        final List<Step> steps = List.of(new Step('A', List.of('C')),
                new Step('F', List.of('C')),
                new Step('B', List.of('A')),
                new Step('D', List.of('A')),
                new Step('E', List.of('B', 'D', 'F')),
                new Step('C'));

        final String expectedResult = "AFBDEC";
        //actual
        final SortedSteps sortedSteps = new SortedSteps(steps);
        final String actualResult = sortedSteps.getStepsAsString();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
