package org.haffson.adventofcode.days.day02;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        this.problemStatus.put("2", ProblemStatusEnum.SOLVED);
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
        String fileName = "src/main/resources/puzzle_input/day2_input.txt";
        return "Part 2 - common letters: " + calculateCommonLetters(fileReaders.getInputList(fileName));
    }

    /**
     * Primary method for Day 2, Part 1.
     * Calculates the final frequency as the sum of all frequencies.
     *
     * @return the final frequency
     */

    private int calculateCheckSum(List<String> myStringList) {
        int[] countSameLetterArray = new int[2];

        myStringList.forEach(word -> {
            Map<Integer, Integer> letterMap = new HashMap<>();
            word.chars().forEach(wordCharacter -> {
                Integer numberOfLetter = letterMap.get(wordCharacter);
                letterMap.put(wordCharacter, numberOfLetter != null ? numberOfLetter + 1 : 1);
            });
            countSameLetterArray[0] += letterMap.entrySet().stream().anyMatch(e -> e.getValue() == 2) ? 1 : 0;
            countSameLetterArray[1] += letterMap.entrySet().stream().anyMatch(e -> e.getValue() == 3) ? 1 : 0;
        });
        return countSameLetterArray[0] * countSameLetterArray[1];
    }

    private String calculateCommonLetters(List<String> myStringList) {
        for (int i = 0; i < myStringList.size(); i++) {
            String word1 = myStringList.get(i);
            for (int j = i + 1; j < myStringList.size(); j++) {
                String word2 = myStringList.get(j);
                int count = 0;
                int position = 0;
                String stringBuilder = compareWordsForEqualLetters(word1, word2, count, position);
                if (stringBuilder != null) return stringBuilder;
            }
        }
        return null;
    }

    private String compareWordsForEqualLetters(String word1, String word2, int count, int position) {
        for (int k = 0; k < word1.length(); k++) {
            if (word1.charAt(k) != word2.charAt(k)) {
                count++;
                if (count > 1) break;
                position = k;
            }
        }
        if (count == 1) {
            StringBuilder stringBuilder = new StringBuilder(word1);
            stringBuilder.deleteCharAt(position);
            return stringBuilder.toString();
        }
        return null;
    }
}