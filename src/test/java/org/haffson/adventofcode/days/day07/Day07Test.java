package org.haffson.adventofcode.days.day07;

import org.haffson.adventofcode.utils.FileReaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
                        "Step C must be finished before step A can begin.",
                        "Step C must be finished before step F can begin.",
                        "Step A must be finished before step B can begin.",
                        "Step A must be finished before step D can begin.",
                        "Step B must be finished before step E can begin.",
                        "Step D must be finished before step E can begin.",
                        "Step F must be finished before step E can begin."
                ));
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
        final String expectedResult = "Part 1 - : " + "CABDFE";

        //act
        final String actualResult = day07.firstPart();

        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

}
