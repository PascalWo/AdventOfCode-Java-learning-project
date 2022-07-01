package org.haffson.adventofcode.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * File readers to load and parse input files for the puzzles.
 */
@Component
public class FileReaders {

    public List<Integer> getInputArray(String fileName) {

        try(Scanner scanner = new Scanner(new File(String.valueOf(fileName)))) {

            ArrayList<Integer> inputIntArray = new ArrayList<>();
            while (scanner.hasNextInt()) {
                inputIntArray.add(scanner.nextInt());
            }
            return inputIntArray;

        } catch (FileNotFoundException e) {
            throw new UncheckedFileNotFoundException("FileInput not found", e);
        }
    }
}
