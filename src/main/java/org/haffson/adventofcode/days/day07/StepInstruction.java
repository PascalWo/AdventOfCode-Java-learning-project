package org.haffson.adventofcode.days.day07;

import javax.annotation.Nonnull;
import java.util.List;

public record StepInstruction(char step, char finishedBefore) {

    @Nonnull
    public static StepInstruction of(@Nonnull final String input) {
        final String[] split1 = input.split(" ");
        final char step = split1[1].charAt(0);
        final char finishedBefore = split1[7].charAt(0);

        return new StepInstruction(step, finishedBefore);
    }

    @Nonnull
    public static List<StepInstruction> of(@Nonnull final List<String> inputList) {
        return inputList.stream().map(StepInstruction::of).toList();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final StepInstruction that = (StepInstruction) o;
        return step == that.step && finishedBefore == that.finishedBefore;
    }

    @Override
    public String toString() {
        return "StepInstruction{" +
                "step=" + step +
                ", finishedBefore=" + finishedBefore +
                '}';
    }
}
