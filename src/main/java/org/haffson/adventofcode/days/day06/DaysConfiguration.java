package org.haffson.adventofcode.days.day06;

import org.haffson.adventofcode.utils.FileReaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;

@Configuration
public class DaysConfiguration {

    @Bean
    public Day06 createDay06(@Nonnull final FileReaders fileReaders) {
        return new Day06(fileReaders, 10000);
    }
}