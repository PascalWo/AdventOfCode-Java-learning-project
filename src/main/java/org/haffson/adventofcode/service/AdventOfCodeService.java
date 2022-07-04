package org.haffson.adventofcode.service;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.controller.AdventOfCodeController;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.exceptions.PuzzleNotSolvedYetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

/**
 * The <i>Advent of Code</i> Service is used by
 * {@link AdventOfCodeController}
 * to handle calls made to it.
 *
 * @author Michelle Fernandez Bieber
 */
@Component
public class AdventOfCodeService {

    /** A list containing all {@link Days} from the corresponding subpackages. */
    private final List<Days> daysSolutions;


    /**
     * {@code @Autowired} constructor adding all implemented {@link Days} to the list of Days.
     *
     * @param daysSolutions {@code @Autowired} days solutions
     */
    @Autowired
    public AdventOfCodeService(List<Days> daysSolutions) {
        this.daysSolutions = Objects.requireNonNull(daysSolutions);
    }

    /**
     * Asks for the implementation for the requested day and then checks whether the requested part
     * has been solved yet. Then it requests the retrieved implementation to calculate the solution.
     *
     * @param day the simple day of the advent calendar to be solved
     * @param part the part of the puzzle for that day
     * @return a {@code String} with the result for the puzzle, or in case it has not been implemented,
     * an {@link PuzzleNotSolvedYetException} is thrown.
     */
    public String getResultsForASpecificDayAndPuzzlePart(String day, String part) throws FileNotFoundException {
        Days thisDaysClass = findDayForDay(Integer.parseInt(day));
        if (!isProblemSolvedForPart(thisDaysClass, part)) {
            throw new PuzzleNotSolvedYetException(new Throwable());
        } else if (("1").equals(part)) {
            return thisDaysClass.firstPart();
        } else if (("2").equals(part)) {
            return thisDaysClass.secondPart();
        } else {
            return "This puzzle has not been solved yet.";
        }
    }

    /**
     * Checks whether the corresponding part to a day has already been solved.
     *
     * @param thisDaysClass the implementation for the requested day
     * @param part the part to check for it's solution status
     * @return if the part has been solved for a specific day
     */
    private boolean isProblemSolvedForPart(Days thisDaysClass, String part) {
        return thisDaysClass.getProblemStatus().containsKey(part) && thisDaysClass.getProblemStatus().get(part) == ProblemStatusEnum.SOLVED;
    }

    /**
     * Streams through all the provided implementations for the days inside the list populated by Spring and retrieves
     * a requested implementation for a day.
     *
     * @param day the day for which an implementation should be retrieved
     * @return the {@code Day} implementation for the requested day
     */
    private Days findDayForDay(int day) {
        return daysSolutions.stream()
                .filter(solution -> solution.getDay() == day)
                .findFirst()
                .orElseThrow(() -> new PuzzleNotSolvedYetException(new Throwable()));
    }

    /**
     * Getter for {@code daysSolutions}
     *
     * @return a List of all implemented days
     */
    public List<Days> getDaysSolutions() {
        return daysSolutions;
    }
}
