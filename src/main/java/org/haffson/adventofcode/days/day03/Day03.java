package org.haffson.adventofcode.days.day03;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
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
        this.problemStatus.put("2", ProblemStatusEnum.SOLVED);
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
     * Calculates the count of overlapping rectangle coordinates.
     *
     * @return the final count of overlapping inches
     */

    private long calculateClaims(final List<String> myArrayList) {
        final List<RectangleClaim> rectangleClaimList = convertStringToRectangleList(myArrayList);
        final String[][] fabric = get2DClaimArray(rectangleClaimList);

        return Arrays.stream(fabric)
                .flatMap(Arrays::stream)
                .filter("X"::equals)
                .count();
    }

    List<RectangleClaim> convertStringToRectangleList(final List<String> stringList) {

        return stringList.stream().map(RectangleClaim::of).collect(Collectors.toList());
    }

    private int calculateNotOverlappingClaim(final List<String> inputList) {
        final List<RectangleClaim> rectangleClaimList = convertStringToRectangleList(inputList);
        final String[][] fabric = get2DClaimArray(rectangleClaimList);

        final int[] intactClaimId = {-1};
        rectangleClaimList .forEach(claim ->{
            boolean stillFree = true;
            for (int x = claim.getLeftSpace(); x < claim.getRightXCoordinate() && stillFree; x++){
                for (int y = claim.getTopSpace(); y < claim.getBottomYCoordinate(); y++) {
                    if (!Objects.equals(fabric[x][y], claim.getId())){
                        stillFree = false;
                        break;
                    }else if (x == claim.getRightXCoordinate() - 1 && y == claim.getBottomYCoordinate() - 1){
                        intactClaimId[0] = Integer.parseInt(claim.getId());
                    }
                }
            }
        });
        return intactClaimId[0];
    }

    private String[][] get2DClaimArray(final List<RectangleClaim> claimList){
        final String[][] fabric = new String[1000][1000];

        claimList.forEach(claim ->
                IntStream.range(claim.getLeftSpace(), claim.getRightXCoordinate())
                        .forEach(i
                                -> IntStream.range(claim.getTopSpace(), claim.getBottomYCoordinate())
                                .forEach(j
                                        -> fabric[i][j] = fabric[i][j] == null ? claim.getId() : "X")));
        return fabric;
    }
}
