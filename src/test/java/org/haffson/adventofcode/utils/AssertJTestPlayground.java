package org.haffson.adventofcode.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AssertJTestPlayground {

    @Test
    void a_few_simple_assertions() {
        assertThat("The Lord of the Rings").isNotNull()
                .startsWith("The")
                .contains("Lord")
                .endsWith("Rings");
    }
}
