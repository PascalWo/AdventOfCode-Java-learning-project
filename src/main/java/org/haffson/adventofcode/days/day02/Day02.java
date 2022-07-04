package org.haffson.adventofcode.days.day02;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class Day02 implements Days {

    /**
     * The puzzle status {@code HashMap}
     */
    private final HashMap<String, ProblemStatusEnum> problemStatus;
    private final FileReaders fileReaders;

    /**
     * Causes the input file to be parsed into the frequencies array ({@code frequencies}).
     *
     * @param fileReaders {@code @Autowired} fileReader //TODO: inject what you need
     */
    @Autowired
    Day02(FileReaders fileReaders) {
        this.fileReaders = fileReaders;
        this.problemStatus = new HashMap<>();
        this.problemStatus.put("1", ProblemStatusEnum.SOLVED);
        this.problemStatus.put("2", ProblemStatusEnum.UNSOLVED);
    }

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public HashMap<String, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Override
    public String firstPart() {
        String fileName = "src/main/resources/puzzle_input/day2_input.txt";

        return "Part 1 - checksum: " + calculateCheckSum(fileReaders.getInputList(fileName));
    }

    @Override
    public String secondPart() {
        return null;
    }

    /**
     * Primary method for Day 2, Part 1.
     * Calculates the final frequency as the sum of all frequencies.
     *
     * @return the final frequency
     */

    private int calculateCheckSum(List<String> myStringList) {
        int exactlyTwoSameLetters = 0;
        int exactlyThreeSameLetters = 0;

        for (String wordFromList : myStringList
        ) {
            HashMap<Character, Integer> wordToLetterMap = new HashMap<>();
            for (Character wordLetter : wordFromList.toCharArray()
            ) {
                if (wordToLetterMap.containsKey(wordLetter)) {
                    int letterValue = wordToLetterMap.get(wordLetter);
                    wordToLetterMap.put(wordLetter, letterValue + 1);
                } else {
                    wordToLetterMap.put(wordLetter, 1);
                }
            }
            boolean hasExactlyTwoLetters = false;
            boolean hasExactlyThreeLetters = false;

            for (Character wordLetter: wordToLetterMap.keySet()
                 ) {
                if (wordToLetterMap.get(wordLetter) == 2){
                    hasExactlyTwoLetters = true;
                } else if (wordToLetterMap.get(wordLetter) == 3){
                    hasExactlyThreeLetters = true;
                }
                if (hasExactlyTwoLetters && hasExactlyThreeLetters){
                    break;
                }
            }
            if(hasExactlyTwoLetters) {
                exactlyTwoSameLetters++;
            }
            if(hasExactlyThreeLetters) {
                exactlyThreeSameLetters++;
            }
        }
        return exactlyTwoSameLetters * exactlyThreeSameLetters;
    }
}
