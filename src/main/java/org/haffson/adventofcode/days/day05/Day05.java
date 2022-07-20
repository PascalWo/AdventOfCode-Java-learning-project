package org.haffson.adventofcode.days.day05;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.haffson.adventofcode.utils.ProblemStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Implementation for <i>Day 5: Chronal Calibration</i>.
 */
@Component
public class Day05 implements Days {

    /**
     * The puzzle status {@code HashMap}
     */
    @Nonnull
    private final Map<Integer, ProblemStatusEnum> problemStatus;
    @Nonnull
    private final FileReaders fileReaders;

    /**
     * Causes the input file to be parsed into the frequencies array ({@code frequencies}).
     *
     * @param fileReaders {@code @Autowired} fileReader //TODO: inject what you need
     */
    public Day05(@Nonnull final FileReaders fileReaders) {
        this.fileReaders = fileReaders;
        this.problemStatus = ProblemStatus.getProblemStatusMap(1, 2,
                ProblemStatusEnum.SOLVED, ProblemStatusEnum.SOLVED);
    }

    @Override
    public int getDay() {
        return 5;
    }

    @Nonnull
    @Override
    public Map<Integer, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Nonnull
    @Override
    public String firstPart() {
        final String fileName = "src/main/resources/puzzle_input/day5_input.txt";
        final String part1Result = "Part 1 - Remaining Units after fully reacting the polymer: ";
        return part1Result + calculateRemainingUnits(fileReaders.getInputList(fileName));
    }

    @Nonnull
    @Override
    public String secondPart() {
        final String fileName = "src/main/resources/puzzle_input/day5_input.txt";
        final String part2Result = "Part 2 - Shortest remaining Units after fully reacting the polymer and removing one letter: ";
        return part2Result + calculateShortestRemainingUnits(fileReaders.getInputList(fileName));
    }

    /**
     * Primary method for Day 5, Part 1.
     * Calculates the remaining Units after fully reacting the polymer scanned.
     *
     * @return Int of remaining Units
     */
    private int calculateRemainingUnits(@Nonnull final List<String> inputStringList) {
        String inputString = convertStringListToString(inputStringList);

        List<Character> characterInputList = new ArrayList<>(convertStringToCharacterList(inputString));

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
    @Nonnull
    String convertStringListToString(@Nonnull final List<String> inputStringList) {
        return inputStringList.get(0);
    }

    /**
     * Helper method for Day 5, Part 1.
     * Converts a String to a List<Character>.
     * Important: You get a mutable list.
     *
     * @return a mutable List<Character> for further functions.
     */
    @Nonnull
    List<Character> convertStringToCharacterList(@Nonnull final String inputString) {
        return inputString.chars().mapToObj(c -> (char) c).toList();
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
    @Nonnull
    List<Character> removeCharDuplicatesWithDifferentCases(@Nonnull final List<Character> polymerCharacterList) {
        boolean isRunning = true;
        while (isRunning) {
            isRunning = false;
            for (int i = 0; i < polymerCharacterList.size() - 1; i++) {
                Character actualCharacter = polymerCharacterList.get(i);
                Character nextCharacter = polymerCharacterList.get(i + 1);
                final boolean nextCharIsDuplicateInDifferentCase = ((Character.isUpperCase(actualCharacter) && Character.isLowerCase(nextCharacter))
                        || (Character.isLowerCase(actualCharacter) && Character.isUpperCase(nextCharacter)))
                        && (Character.toUpperCase(actualCharacter) == nextCharacter
                        || actualCharacter == Character.toUpperCase(nextCharacter));
                if (nextCharIsDuplicateInDifferentCase) {
                    polymerCharacterList.remove(i);
                    polymerCharacterList.remove(i);
                    isRunning = true;
                }
            }
        }
        return polymerCharacterList;
    }

    /**
     * Primary method for Day 5, Part 2.
     * Eliminates one letter from the polymer and let it fully reacting.
     * Searches for the letter which produces the shortest remaining units and returns its length.
     *
     * @return Int of remaining Units
     */
    private int calculateShortestRemainingUnits(@Nonnull final List<String> inputStringList) {
        String inputString = convertStringListToString(inputStringList);
        List<Character> characterInputList = convertStringToCharacterList(inputString);

        Map<Character, Integer> lengthByLetterMap = getPolymerLengthByRemovedChar(characterInputList);

        Map.Entry<Character, Integer> min = Collections.min(lengthByLetterMap.entrySet(),
                Map.Entry.comparingByValue());

        return min.getValue();
    }

    /**
     * Helper method for Day 5, Part 2.
     * Start's a loop to map each alphabetical letter with a polymer length.
     *
     * @return Map<Character, Integer>
     */
    @Nonnull
    Map<Character, Integer> getPolymerLengthByRemovedChar(@Nonnull final List<Character> inputCharacterList) {
        String letterString = "abcdefghijklmnopqrstuvwxyz";

        List<Character> letterList = letterString.chars().mapToObj(c -> (char) c).toList();

        Map<Character, Integer> lengthByLetter = new HashMap<>();

        letterList.forEach(letter -> lengthByLetter.put(letter,
                cleanedPolymerLengthByRemovedLetter(letter, inputCharacterList)));

        return lengthByLetter;
    }

    /**
     * Helper method for Day 5, Part 2.
     * Removes given letter from list - upper and lower case.
     * Gives reduced polymerList to duplicateCleaning function.
     *
     * @return int of duplicateCleanedList.size.
     */
    int cleanedPolymerLengthByRemovedLetter(@Nonnull final Character letter, @Nonnull final List<Character> polymerList) {
        List<Character> letterToRemove = new ArrayList<>();
        letterToRemove.add(letter);
        letterToRemove.add(Character.toUpperCase(letter));

        List<Character> copyPolymerList = new ArrayList<>(polymerList);
        copyPolymerList.removeAll(letterToRemove);

        List<Character> duplicateCleanedList = removeCharDuplicatesWithDifferentCases(copyPolymerList);

        return duplicateCleanedList.size();
    }
}
