package org.haffson.adventofcode.days.day07;

import org.haffson.adventofcode.utils.FileReaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day07Test {

    private Day07 day07;

    @BeforeEach
    void setUp() {
        final FileReaders fileReaders = mock(FileReaders.class);
        day07 = new Day07(fileReaders);

        when(fileReaders.getInputList("src/main/resources/puzzle_input/day7_input.txt"))
                .thenReturn(List.of(
                        "Step P must be finished before step Z can begin.",
                        "Step E must be finished before step O can begin.",
                        "Step X must be finished before step T can begin.",
                        "Step W must be finished before step V can begin.",
                        "Step K must be finished before step Y can begin.",
                        "Step C must be finished before step M can begin.",
                        "Step S must be finished before step R can begin.",
                        "Step T must be finished before step H can begin.",
                        "Step Z must be finished before step V can begin.",
                        "Step F must be finished before step L can begin.",
                        "Step V must be finished before step A can begin.",
                        "Step I must be finished before step A can begin.",
                        "Step J must be finished before step M can begin.",
                        "Step N must be finished before step Y can begin.",
                        "Step A must be finished before step B can begin.",
                        "Step H must be finished before step Q can begin.",
                        "Step Q must be finished before step O can begin.",
                        "Step D must be finished before step O can begin.",
                        "Step Y must be finished before step O can begin.",
                        "Step G must be finished before step L can begin.",
                        "Step B must be finished before step M can begin.",
                        "Step L must be finished before step U can begin.",
                        "Step M must be finished before step O can begin.",
                        "Step O must be finished before step U can begin.",
                        "Step R must be finished before step U can begin.",
                        "Step M must be finished before step U can begin.",
                        "Step Q must be finished before step U can begin.",
                        "Step K must be finished before step U can begin.",
                        "Step D must be finished before step R can begin.",
                        "Step A must be finished before step M can begin.",
                        "Step A must be finished before step Q can begin.",
                        "Step V must be finished before step Y can begin.",
                        "Step H must be finished before step G can begin.",
                        "Step P must be finished before step K can begin.",
                        "Step N must be finished before step A can begin.",
                        "Step P must be finished before step H can begin.",
                        "Step X must be finished before step Z can begin.",
                        "Step X must be finished before step K can begin.",
                        "Step Y must be finished before step U can begin.",
                        "Step F must be finished before step Q can begin.",
                        "Step W must be finished before step M can begin.",
                        "Step B must be finished before step L can begin.",
                        "Step E must be finished before step L can begin.",
                        "Step N must be finished before step O can begin.",
                        "Step I must be finished before step G can begin.",
                        "Step J must be finished before step H can begin.",
                        "Step Z must be finished before step N can begin.",
                        "Step V must be finished before step N can begin.",
                        "Step F must be finished before step B can begin.",
                        "Step A must be finished before step Y can begin.",
                        "Step Q must be finished before step R can begin.",
                        "Step L must be finished before step O can begin.",
                        "Step H must be finished before step U can begin.",
                        "Step V must be finished before step G can begin.",
                        "Step Z must be finished before step B can begin.",
                        "Step V must be finished before step J can begin.",
                        "Step V must be finished before step O can begin.",
                        "Step T must be finished before step D can begin.",
                        "Step Y must be finished before step M can begin.",
                        "Step B must be finished before step R can begin.",
                        "Step O must be finished before step R can begin.",
                        "Step C must be finished before step V can begin.",
                        "Step W must be finished before step T can begin.",
                        "Step P must be finished before step N can begin.",
                        "Step L must be finished before step R can begin.",
                        "Step V must be finished before step U can begin.",
                        "Step C must be finished before step J can begin.",
                        "Step N must be finished before step R can begin.",
                        "Step X must be finished before step S can begin.",
                        "Step X must be finished before step A can begin.",
                        "Step G must be finished before step O can begin.",
                        "Step A must be finished before step O can begin.",
                        "Step X must be finished before step O can begin.",
                        "Step D must be finished before step Y can begin.",
                        "Step C must be finished before step G can begin.",
                        "Step K must be finished before step D can begin.",
                        "Step N must be finished before step B can begin.",
                        "Step C must be finished before step B can begin.",
                        "Step W must be finished before step F can begin.",
                        "Step E must be finished before step Z can begin.",
                        "Step S must be finished before step V can begin.",
                        "Step G must be finished before step M can begin.",
                        "Step T must be finished before step B can begin.",
                        "Step W must be finished before step C can begin.",
                        "Step D must be finished before step G can begin.",
                        "Step L must be finished before step M can begin.",
                        "Step H must be finished before step D can begin.",
                        "Step G must be finished before step R can begin.",
                        "Step T must be finished before step J can begin.",
                        "Step A must be finished before step R can begin.",
                        "Step B must be finished before step O can begin.",
                        "Step J must be finished before step R can begin.",
                        "Step G must be finished before step U can begin.",
                        "Step K must be finished before step O can begin.",
                        "Step V must be finished before step L can begin.",
                        "Step M must be finished before step R can begin.",
                        "Step D must be finished before step U can begin.",
                        "Step H must be finished before step Y can begin.",
                        "Step P must be finished before step W can begin.",
                        "Step K must be finished before step I can begin.",
                        "Step J must be finished before step G can begin."
                ));

//        when(fileReaders.getInputList("src/main/resources/puzzle_input/day7_input.txt"))
//                .thenReturn(List.of(
//                        "Step C must be finished before step A can begin.",
//                        "Step C must be finished before step F can begin.",
//                        "Step A must be finished before step B can begin.",
//                        "Step A must be finished before step D can begin.",
//                        "Step B must be finished before step E can begin.",
//                        "Step D must be finished before step E can begin.",
//                        "Step F must be finished before step E can begin."
//                ));
    }

    @DisplayName("get the current AoC Day")
    @Test
    void testGetDay() {
        final int expectedResult = 7;
        final int actualResult = day07.getDay();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("First part - should return correct order of steps")
    @Test
    void test_firstPart_returnsExpectedResult() {
        //arrange
//        final String expectedResult = "Part 1 - : " + "CABDFE";
        final String expectedResult = "Part 1 - : " + "EPWCFXKISTZVJHDGNABLQYMORU";

        //act
        final String actualResult = day07.firstPart();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

//    @Test
//    void test_getInstructions(){
//        //arrange
//        final FileReaders fileReaders = mock(FileReaders.class);
//
//                when(fileReaders.getInputList("src/main/resources/puzzle_input/day7_input.txt"))
//                .thenReturn(List.of(
//                        "Step C must be finished before step A can begin.",
//                        "Step C must be finished before step F can begin.",
//                        "Step A must be finished before step B can begin.",
//                        "Step A must be finished before step D can begin.",
//                        "Step B must be finished before step E can begin.",
//                        "Step D must be finished before step E can begin.",
//                        "Step F must be finished before step E can begin."
//                ));
//
//        final List<StepInstruction> expectedResult = List.of(
//                new StepInstruction('C','A'),
//                new StepInstruction('C','F'),
//                new StepInstruction('A','B'),
//                new StepInstruction('A','D'),
//                new StepInstruction('B','E'),
//                new StepInstruction('D','E'),
//                new StepInstruction('F','E'));
//
//        //act
//
//        final List<StepInstruction> actualResult = day07.getInstructions()
//
//        //assert
//        assertThat()
//    }

//    @Test
//    void test_convertSteps(){
//        //arrange
//        final List<StepInstruction> stepInstructions = List.of(
//                new StepInstruction('C','A'),
//                new StepInstruction('C','F'),
//                new StepInstruction('A','B'),
//                new StepInstruction('A','D'),
//                new StepInstruction('B','E'),
//                new StepInstruction('D','E'),
//                new StepInstruction('F','E'));
//
//        final List<StepInformation> expectedResult = new ArrayList<>();
//
//        //act
//
//        final List<StepInformation> actualResult = day07.convertInstructionsToSteps(stepInstructions);
//        //assert
//        assertThat(actualResult).isEqualTo(expectedResult);
//    }

    @Test
    void test_convertSortedSteps(){
        //arrange
        final List<StepInstruction> stepInstructions = List.of(
                new StepInstruction('C','A'),
                new StepInstruction('C','F'),
                new StepInstruction('A','B'),
                new StepInstruction('A','D'),
                new StepInstruction('B','E'),
                new StepInstruction('D','E'),
                new StepInstruction('F','E'));

        final Map<Character,List<Character>> expectedResult = Map.of('A',List.of('C'),
                'F',List.of('C'),
                'B',List.of('A'),
                'D',List.of('A'),
                'E',List.of('B','D','F'),
                'C',List.of());


        //act

        final Map<Character,List<Character>> actualResult = day07.convertInstructionsToSortedSteps(stepInstructions);
        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

//    @Test
//    void test_convertMapToSteps(){
//        //arrange
//        final Map<Character,List<Character>> inputMap = Map.of('A',List.of('C'),
//                'F',List.of('C'),
//                'B',List.of('A'),
//                'D',List.of('A'),
//                'E',List.of('B','D','F'),
//                'C',List.of());
//
//        final List<Step> expectedResult = List.of(new Step())
//
//        //act
//
//        final Map<Character,List<Character>> actualResult = day07.convertInstructionsToSortedSteps(stepInstructions);
//        //assert
//        assertThat(actualResult).isEqualTo(expectedResult);
//    }

}
