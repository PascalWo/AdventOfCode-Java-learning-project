package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.List;

import static java.util.Objects.requireNonNull;

public record StepInstruction(char previousStep, char step) {

    @Nonnull
    public static StepInstruction of(@Nonnull final String input) {
        requireNonNull(input, "input");

        final String[] split1 = input.split(" ");
        final char previousStep = split1[1].charAt(0);
        final char step = split1[7].charAt(0);

        return new StepInstruction(previousStep, step);
    }

    @Nonnull
    public static List<StepInstruction> of(@Nonnull final List<String> inputList) {
        requireNonNull(inputList, "inputList");

        return inputList.stream().map(StepInstruction::of).toList();
    }
}
