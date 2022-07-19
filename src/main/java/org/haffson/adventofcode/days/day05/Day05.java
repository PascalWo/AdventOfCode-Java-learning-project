package org.haffson.adventofcode.days.day05;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.haffson.adventofcode.utils.ProblemStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation for <i>Day 5: Chronal Calibration</i>.
 */
@Component
public class Day05 implements Days {

    /**
     * The puzzle status {@code HashMap}
     */
    private final Map<Integer, ProblemStatusEnum> problemStatus;
    private final FileReaders fileReaders;

    /**
     * Causes the input file to be parsed into the frequencies array ({@code frequencies}).
     *
     * @param fileReaders {@code @Autowired} fileReader //TODO: inject what you need
     */
    public Day05(final FileReaders fileReaders) {
        this.fileReaders = fileReaders;
        this.problemStatus = ProblemStatus.getProblemStatusMap(1, 2,
                ProblemStatusEnum.SOLVED, ProblemStatusEnum.UNSOLVED);
    }

    @Override
    public int getDay() {
        return 5;
    }

    @Override
    public Map<Integer, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Override
    public String firstPart() {
        final String fileName = "src/main/resources/puzzle_input/day5_input.txt";
        return "Part 1 - Remaining Units after fully reacting the polymer: " + calculateRemainingUnits(fileReaders.getInputList(fileName));
    }

    @Override
    public String secondPart() {
        final String fileName = "src/main/resources/puzzle_input/day5_input.txt";
        return null;
    }

    /**
     * Primary method for Day 5, Part 1.
     * Calculates the remaining Units after fully reacting the polymer scanned.
     *
     * @return Int of remaining Units
     */
    private int calculateRemainingUnits(final List<String> inputStringList) {
        String inputString = convertStringListToString(inputStringList);

        List<Character> characterInputList = convertStringToCharacterList(inputString);

        List<Character> polymerCharacterList = removeCharDuplicatesWithDifferentCases(characterInputList);
        return polymerCharacterList.size();
    }

    /**
     * Helper method for Day 5, Part 1.
     * Converts a List<String> to a single String.
     * Needed because the FilerReader returns a List<String>.
     *
     * @return String of complete list
     */
    String convertStringListToString(final List<String> inputStringList) {
        return inputStringList.get(0);
    }

    /**
     * Helper method for Day 5, Part 1.
     * Converts a String to a List<Character>.
     * Important: You get a mutable list.
     *
     * @return a mutable List<Character> for further functions.
     */
    List<Character> convertStringToCharacterList(final String inputString) {
        return inputString.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
    }

    /**
     * Helper method for Day 5, Part 1.
     * Main method to remove duplicates.
     * Not every duplicate should be removed.
     * Just the duplicated adjacent values which have different cases (upper/ lower) will be removed.
     * Loops over the list to remove emerging values too.
     *
     * @return List<Character> without adjacent duplicates.
     */
    List<Character> removeCharDuplicatesWithDifferentCases(List<Character> polymerCharacterList) {
        boolean isRunning = true;
        while (isRunning) {
            isRunning = false;
            for (int i = 0; i < polymerCharacterList.size() - 1; i++) {
                Character actualCharacter = polymerCharacterList.get(i);
                Character nextCharacter = polymerCharacterList.get(i + 1);
                if (((Character.isUpperCase(actualCharacter) && Character.isLowerCase(nextCharacter))
                        || (Character.isLowerCase(actualCharacter) && Character.isUpperCase(nextCharacter)))
                        && (Character.toUpperCase(actualCharacter) == nextCharacter
                        || actualCharacter == Character.toUpperCase(nextCharacter))) {
                    polymerCharacterList.remove(i);
                    polymerCharacterList.remove(i);
                    isRunning = true;
                }
            }
        }
        return polymerCharacterList;
    }
}
