package org.haffson.adventofcode.days.day05;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;

public record Polymer(@Nonnull String value) {
    public int length() {
        return value.length();
    }

    /**
     * Helper method for Day 5, Part 1.
     * Converts a String/ Polymer to a List<Character>.
     * Important: You get a mutable list.
     *
     * @return a mutable List<Character> for further functions.
     */
    @Nonnull
    private List<Character> convertPolymerToCharacterList() {
        return value.chars().mapToObj(c -> (char) c).toList();
    }

    /**
     * Helper method for Day 5, Part 1.
     * Converts a List<Character> to a String.
     *
     * @return String of a List<Character>.
     */
    @Nonnull
    private String convertCharacterListToString(@Nonnull final List<Character> characterList) {
        return characterList.stream().map(String::valueOf).collect(Collectors.joining());
    }

    /**
     * Helper method for Day 5, Part 1.
     * Main method to remove duplicates.
     * Not every duplicate should be removed.
     * Just the duplicated adjacent values which have different cases (upper/ lower) will be removed.
     * Loops over the list to remove emerging values too.
     *
     * @return new Polymer without adjacent duplicates.
     */
    @Nonnull
    public Polymer react() {
        List<Character> characterList = new ArrayList<>(convertPolymerToCharacterList());

        boolean isRunning = true;
        while (isRunning) {
            isRunning = false;
            for (int i = 0; i < characterList.size() - 1; i++) {
                Character actualCharacter = characterList.get(i);
                Character nextCharacter = characterList.get(i + 1);
                final boolean nextCharIsDuplicateInDifferentCase = ((Character.isUpperCase(actualCharacter) && Character.isLowerCase(nextCharacter))
                        || (Character.isLowerCase(actualCharacter) && Character.isUpperCase(nextCharacter)))
                        && Character.toUpperCase(actualCharacter) == Character.toUpperCase(nextCharacter);
                if (nextCharIsDuplicateInDifferentCase) {
                    characterList.remove(i);
                    characterList.remove(i);
                    isRunning = true;
                }
            }
        }
        return new Polymer(convertCharacterListToString(characterList));
    }

    /**
     * Helper method for Day 5, Part 2.
     * Start's a loop to map each polymer containing letter with a polymer length after reacting.
     *
     * @return Map<Character, Integer>
     */
    @Nonnull
    private Map<Character, Polymer> getPolymerLengthByRemovedChar() {
        List<Character> inputCharacterList = new ArrayList<>(convertPolymerToCharacterList());

        List<Character> letterList = inputCharacterList.stream().map(Character::toLowerCase).distinct().sorted().toList();

        Map<Character, Polymer> lengthByLetter = new HashMap<>();

        letterList.forEach(letter -> lengthByLetter.put(letter,
                reactedPolymerLengthWithRemovedLetter(letter)));

        return lengthByLetter;
    }

    /**
     * Helper method for Day 5, Part 2.
     * Removes given letter from polymer - upper and lower case.
     * Gives reduced polymer to duplicateCleaning function.
     *
     * @return int of reacted polymer length
     */
    private Polymer reactedPolymerLengthWithRemovedLetter(@Nonnull final Character letter) {
        List<Character> inputCharacterList = new ArrayList<>(convertPolymerToCharacterList());

        List<Character> letterToRemove = new ArrayList<>();
        letterToRemove.add(letter);
        letterToRemove.add(Character.toUpperCase(letter));

        inputCharacterList.removeAll(letterToRemove);

        Polymer polymerWithRemovedLetter = new Polymer(convertCharacterListToString(inputCharacterList));

        return polymerWithRemovedLetter.react();
    }

    /**
     * Helper method for Day 5, Part 2.
     * After creating a Map<Character, Integer> of letters and reacted polymer length
     * this function returns the Map.Entry with the lowest length.
     *
     * @return Map.Entry<Character, Integer> shortest reacted polymer length by letter
     */
    @Nonnull
    private ShortestPolymer getShortestRemainingUnitEntry() {

        var entry = Collections.min(getPolymerLengthByRemovedChar().entrySet(),
                Map.Entry.comparingByValue(Comparator.comparingInt(Polymer::length)));
        return new ShortestPolymer(entry.getKey(), entry.getValue());
    }

    /**
     * Helper method for Day 5, Part 2.
     * Transforms the Map.Entry<Character,Integer> with shortest reacted polymer length by letter.
     *
     * @return int value of shortest reacted polymer length by letter
     */
    public int getShortestRemainingPolymerLength() {
        return getShortestRemainingUnitEntry().length();
    }

    public record ShortestPolymer(char removedLetter, Polymer polymer) {
        public int length() {
            return polymer.length();
        }
    }

}
