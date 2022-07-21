package org.haffson.adventofcode.utils;

import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * File readers to load and parse input files for the puzzles.
 */
@Component
public class FileReaders {

    @Nonnull
    public List<String> getInputList(@Nonnull final String fileName) {

        try (final Scanner scanner = new Scanner(new File(fileName))) {

            final ArrayList<String> inputArray = new ArrayList<>();
            while (scanner.hasNextLine()) {
                inputArray.add(scanner.nextLine());
            }
            return inputArray;

        } catch (final FileNotFoundException e) {
            throw new UncheckedFileNotFoundException("FileInput not found", e);
        }
    }

    @Nonnull
    public String getInputString(@Nonnull final String filename) {
        List<String> inputStringList = getInputList(filename);
        return String.join("", inputStringList);
    }
}
