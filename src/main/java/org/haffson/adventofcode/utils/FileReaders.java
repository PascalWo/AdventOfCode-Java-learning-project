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

    public List<String> getInputList(String fileName) {

        try(Scanner scanner = new Scanner(new File(String.valueOf(fileName)))) {

            ArrayList<String> inputIntArray = new ArrayList<>();
            while (scanner.hasNextInt()) {
                inputIntArray.add(scanner.nextLine());
            }
            return inputIntArray;

        } catch (FileNotFoundException e) {
            throw new UncheckedFileNotFoundException("FileInput not found", e);
        }
    }
}
