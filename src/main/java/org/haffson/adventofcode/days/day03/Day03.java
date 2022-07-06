package org.haffson.adventofcode.days.day03;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.IntStream;


/**
 * Implementation for <i>Day 1: Chronal Calibration</i>.
 */
@Component
public class Day03 implements Days {

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
    Day03(final FileReaders fileReaders) {
        this.fileReaders = fileReaders;
        this.problemStatus = new HashMap<>();
        this.problemStatus.put("1", ProblemStatusEnum.SOLVED);
        this.problemStatus.put("2", ProblemStatusEnum.UNSOLVED);
    }

    @Override
    public int getDay() {
        return 3;
    }

    @Override
    public HashMap<String, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Override
    public String firstPart() {
        final String fileName = "src/main/resources/puzzle_input/day3_input.txt";

        return "Part 1 - two or more claims: " + calculateClaims(fileReaders.getInputList(fileName));
    }

    @Override
    public String secondPart() {
        final String fileName = "src/main/resources/puzzle_input/day3_input.txt";
        return "Part 2 - not overlapping claim: " + calculateNotOverlappingClaim(fileReaders.getInputList(fileName));
    }

    /**
     * Primary method for Day 3, Part 1.
     * Calculates the final frequency as the sum of all frequencies.
     *
     * @return the final frequency
     */


    private long calculateClaims(final List<String> myArrayList) {
        final List<RectangleClaim> rectangleClaimList = convertStringToRectangleList(myArrayList);

        final String[][] fabric = new String[1000][1000];
        rectangleClaimList.forEach(claim ->
                IntStream.range(claim.getLeftSpace(), claim.getLeftSpace() + claim.getWidth())
                        .forEach(i
                                -> IntStream.range(claim.getTopSpace(), claim.getTopSpace() + claim.getHeight())
                                .forEach(j
                                        -> fabric[i][j] = fabric[i][j] == null ? "0" : "X")));
        return Arrays.stream(fabric)
                .mapToLong(claims ->
                        Arrays.stream(claims)
                                .filter("X"::equals)
                                .count())
                .sum();

    }

    List<RectangleClaim> convertStringToRectangleList(final List<String> stringList) {
        final List<RectangleClaim> rectangleClaimList = new ArrayList<>();

        for (final String str : stringList
        ) {
            final String[] split1 = str.split("@");
            final String[] splitId = split1[0].split("#")[1].split(" ");
            final String[] split2 = split1[1].split(" ");
            final String[] spaces = split2[1].split(",");
            final String[] topSpace = spaces[1].split(":");
            final String[] recSize = split2[2].split("x");

            final int currentId = Integer.parseInt(splitId[0]);
            final int leftSpace = Integer.parseInt(spaces[0]);
            final int topSPace = Integer.parseInt(topSpace[0]);
            final int width = Integer.parseInt(recSize[0]);
            final int height = Integer.parseInt(recSize[1]);

            rectangleClaimList.add(new RectangleClaim(currentId, leftSpace, topSPace, width, height));
        }
        return rectangleClaimList;
    }

    private int calculateNotOverlappingClaim(final List<String> inputList) {
        final List<RectangleClaim> rectangleClaimList = convertStringToRectangleList(inputList);
        final Integer[] overlapping = new Integer[rectangleClaimList.size()];

        final String[][] fabric = new String[1000][1000];
        rectangleClaimList.forEach(claim ->{
            overlapping[claim.getId()-1]=0;
            IntStream.range(claim.getLeftSpace(), claim.getLeftSpace() + claim.getWidth())
                    .forEach(i
                            -> IntStream.range(claim.getTopSpace(), claim.getTopSpace() + claim.getHeight())
                            .forEach(j
                                    ->  {
                                if (fabric[i][j] == null) {
                                    fabric [i][j] = String.valueOf(claim.getId());
                                } else {
                                    overlapping[claim.getId()-1]++;
                                    overlapping[Integer.parseInt(fabric[i][j])-1]++;
                                }
                            }));});

        return Arrays.asList(overlapping).indexOf(0);
    }


//    private int calculateNotOverlappingClaim(final List<String> inputList) {
//        final List<RectangleClaim> rectangleClaimList = convertStringToRectangleList(inputList);
//        final Integer[] overlapping = new Integer[rectangleClaimList.size()];
//
//        final String[][] fabric = new String[1000][1000];
//        rectangleClaimList.forEach(claim ->{
//
//            IntStream.range(claim.getLeftSpace(), claim.getLeftSpace() + claim.getWidth())
//                    .forEach(i
//                            -> IntStream.range(claim.getTopSpace(), claim.getTopSpace() + claim.getHeight())
//                            .forEach(j
//                                    ->  {
//                                if (fabric[i][j] == null) {
//                                    fabric [i][j] = String.valueOf(rectangleClaimList.get(0).getId());
//                                } else {
//                                    overlapping[rectangleClaimList.get(0).getId()-1]++;
//                                    overlapping[Integer.parseInt(fabric[i][j])-1]++;
//                                }
//                            }))});
//
//        return Arrays.asList(overlapping).indexOf(0);
//    }
}
