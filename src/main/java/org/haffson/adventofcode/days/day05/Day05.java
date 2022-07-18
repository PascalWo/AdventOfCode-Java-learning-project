package org.haffson.adventofcode.days.day05;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.haffson.adventofcode.utils.ProblemStatus;
import org.springframework.stereotype.Component;

import java.util.*;
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
////        List<Character> characterList = convertStringToCharacterList(inputString);
////        String resultString = removeUtil(inputString);
//
//
////        String resultString = removeDuplicates(inputString, ' ');
//
////        String[] results = inputString.split("(?<=\\G.{" + 1000 + "})");
////
////        List<String> splitStringList = List.of(results);
//
//        List<String> splitStringList = splitStringToStringList(inputString);
//
//
////        List<String> cleanedStringList = new ArrayList<>();
////        for (String s : splitStringList) {
////            cleanedStringList.add(removeUtil(s));
////        }
//
//        List<String> cleanedStringList = cleanStringListUp(splitStringList);
//
////        String cleanedResultString = cleanedStringList.stream()
////                .map(String::valueOf)
////                .collect(Collectors.joining());
//
//        String cleanedResultString = collectStringListToString(cleanedStringList);
//
//        System.out.println(cleanedResultString.length());
//
//        //------------------------------------------------------------------------------
//
////        String[] results2 = cleanedResultString.split("(?<=\\G.{" + 1000 + "})");
//
//        List<String> splitStringList2 = splitStringToStringList(cleanedResultString);
//
////        List<String> cleanedStringList2 = new ArrayList<>();
////        for (String s : splitStringList2) {
////            cleanedStringList2.add(removeUtil(s));
////        }
//        List<String> cleanedStringList2 = cleanStringListUp(splitStringList2);
//
////        String cleanedResultString2 = cleanedStringList2.stream()
////                .map(String::valueOf)
////                .collect(Collectors.joining());
//
//        String cleanedResultString2 = collectStringListToString(cleanedStringList2);
//
//        System.out.println(cleanedResultString2.length());
//
//
//        //-------------------------------------------------------------------------------
//        List<String> splitStringList3 = splitStringToStringList(cleanedResultString2);
//        List<String> cleanedStringList3 = cleanStringListUp(splitStringList3);
//        String cleanedResultString3 = collectStringListToString(cleanedStringList3);
//
//        System.out.println(cleanedResultString3.length());
//
//
//        //-------------------------------------------------------------------------------
//        List<String> splitStringList4 = splitStringToStringList(cleanedResultString3);
//        List<String> cleanedStringList4 = cleanStringListUp(splitStringList4);
//        String cleanedResultString4 = collectStringListToString(cleanedStringList4);
//
//        System.out.println(cleanedResultString4.length());
//
//
//        //-------------------------------------------------------------------------------
//
//        List<String> splitStringList5 = splitStringToStringList(cleanedResultString4);
//        List<String> cleanedStringList5 = cleanStringListUp(splitStringList5);
//        String cleanedResultString5 = collectStringListToString(cleanedStringList5);
//
//        System.out.println(cleanedResultString5.length());


        //-------------------------------------------------------------------------------
        String myNewString = inputString;
        for (int i = 0; i < 1000; i++) {
//            String myNewString = cleanedResultString5;
            List<String> splitStringListX = splitStringToStringList(myNewString);
            List<String> cleanedStringListX = cleanStringListUp(splitStringListX);
            String cleanedResultStringX = collectStringListToString(cleanedStringListX);
            myNewString = cleanedResultStringX;

            System.out.println(cleanedResultStringX.length());

        }
//        List<String> splitStringList5 = splitStringToStringList(cleanedResultString4);
//        List<String> cleanedStringList5 = cleanStringListUp(splitStringList5);
//        String cleanedResultString5 = collectStringListToString(cleanedStringList5);
//
//        System.out.println(cleanedResultString5.length());


        //-------------------------------------------------------------------------------

//        String resultString = removeUtil(cleanedResultString);

            //Endergebnis hiermit!!
//        String resultString = removeUtil(cleanedResultString4);


//        String resultString = removeUtil(splitStringList.get(0));

            //richtiges return!!
//        return resultString.length();

        return myNewString.length();

//        List<Character> filteredList = characterList.stream()
//                .filter(c -> Character.toLowerCase(c))



//        for (int i = 0, j = 1; i < characterList.size()-1 && j < characterList.size(); i++, j++) {
//            if (Character.toLowerCase(characterList.get(i)) == Character.toLowerCase(characterList.get(j))) {
//                if (characterList.get(i).equals(characterList.get(j))) {
//                    continue;
//                }
//                copyList.remove(j);
//                copyList.remove(i);
//            }
//
//        }
//
//        return copyList.size();


    }
    String convertStringListToString(final List<String> inputStringList){
        return inputStringList.get(0);
    }

    List<Character> convertStringToCharacterList(final String inputString) {
        return inputString.chars().mapToObj(e -> (char) e).toList();
    }
    char last_removed;

    String removeUtil(String str) {
        if (str.length() == 0 || str.length() == 1)
            return str;

        if (Character.toLowerCase(str.charAt(0)) == Character.toLowerCase(str.charAt(1))){

            if (str.charAt(0) == str.charAt(1)) {
            } else {
                last_removed = str.charAt(0);
                int counter = 0;
                while (str.length() > 1
                        && Character.toLowerCase(str.charAt(0)) == Character.toLowerCase(str.charAt(1)) && str.charAt(0) != str.charAt(1) && counter < 1) {
                    counter++;
                    str = str.substring(1);
                }
                str = str.substring(1);
                return removeUtil(str);
            }
        }

        String rem_str
                = removeUtil(str.substring(1));

        if (rem_str.length() != 0
                && Character.toLowerCase(rem_str.charAt(0)) == Character.toLowerCase(str.charAt(0)) && rem_str.charAt(0) != str.charAt(0)) {
            last_removed = str.charAt(0);

            return rem_str.substring(1);
        }

        if (rem_str.length() == 0
                && Character.toLowerCase(last_removed) == Character.toLowerCase(str.charAt(0)) && last_removed != str.charAt(0))
            return rem_str;

        return (str.charAt(0) + rem_str);
    }

    String remove(String str)
    {
        last_removed = '\0';
        return removeUtil(str);
    }

    List<String> splitStringToStringList(final String inputString){
        String[] results = inputString.split("(?<=\\G.{" + 999 + "})");

        return List.of(results);
    }
    List<String> splitStringToStringList100(final String inputString){
        String[] results = inputString.split("(?<=\\G.{" + 1250 + "})");

        return List.of(results);
    }

    List<String> cleanStringListUp(final List<String> inputStringList){
        List<String> cleanedStringList = new ArrayList<>();
        for (String s : inputStringList) {
            cleanedStringList.add(removeUtil(s));
        }
        return cleanedStringList;
    }

    String collectStringListToString(final List<String> inputStringList){
        return inputStringList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    String simpleSolution(String polymer){

        int i = 0;
        while (i<polymer.length()-1) {
            if (i>=0 && Math.abs(polymer.charAt(i)-polymer.charAt(i+1)) == 32) {
                polymer = polymer.substring(0, i) + polymer.substring(i+2);
                i--;
            }
            else {
                i++;
            }
        }
        System.out.println(polymer);
        System.out.println(polymer.length());
        return polymer;
    }
}
