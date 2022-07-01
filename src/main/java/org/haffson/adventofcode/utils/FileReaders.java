package org.haffson.adventofcode.utils;

import org.haffson.adventofcode.days.day01.MyFileNotFoundException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * File readers to load and parse input files for the puzzles.
 */
@Component
public class FileReaders {

    public int[] getInputArray(String fileName) {

        try(Scanner scanner = new Scanner(new File(String.valueOf(fileName)))) {
            int[] inputIntArray = new int[1028];
            int i = 0;
            while (scanner.hasNextInt()) {
                inputIntArray[i++] = scanner.nextInt();
            }
            return inputIntArray;
        } catch (FileNotFoundException e) {
            throw new MyFileNotFoundException("FileInput not found", e);
        }
    }
}
