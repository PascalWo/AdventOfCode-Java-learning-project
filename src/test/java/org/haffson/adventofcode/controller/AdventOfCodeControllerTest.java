package org.haffson.adventofcode.controller;

import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.service.AdventOfCodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdventOfCodeController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
class AdventOfCodeControllerTest {

    @MockBean
    AdventOfCodeService adventOfCodeService;

    private final String baseUrl = "/api/adventOfCode";

    private final MediaType contentType = new MediaType("application", "hal+json", Charset.forName("UTF-8"));

    private final Integer day1 = 1;
    private final Integer part1 = 1;
    private final Integer part2 = 2;
    private final String resultDay1Part1 = "Product 1: " + 326211;
    private final String resultDay1Part2 = "Product 2: " + 326211;

    @Autowired
    private MockMvc mvc;


    @BeforeEach
    void setup() {
        final Days day01Stub = Mockito.mock(Days.class);
        final Days day02Stub = Mockito.mock(Days.class);
        Mockito.when(day01Stub.getDay()).thenReturn(1);
        Mockito.when(day02Stub.getDay()).thenReturn(2);

        final List<Days> daysImplementedList = new LinkedList<>();
        daysImplementedList.add(day01Stub);
        daysImplementedList.add(day02Stub);

        Mockito.when(adventOfCodeService.getResultsForASpecificDayAndPuzzlePart(day1, part1))
                .thenReturn(resultDay1Part1);
        Mockito.when(adventOfCodeService.getDaysSolutions())
                .thenReturn(daysImplementedList);
    }


    @Test
    void testGetResultForASpecificDayAndPuzzlePart() throws Exception {
        mvc.perform(get(baseUrl + "/" + "?day=" + day1 + "&part=" + part1)
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("day", is(1)))
                .andExpect(jsonPath("part", is(1)))
                .andExpect(jsonPath("answer", is("Product 1: " + 326211)))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost:8080" + baseUrl + "/" + "?day=" + day1 + "&part=" + part1)))
                .andDo(document("getResultForASpecificDayAndPuzzlePart",
                        preprocessResponse(prettyPrint()),
                        responseFields(getResultForASpecificDayAndPuzzlePart("")))
                );
    }

    @Test
    void testGetResultForASpecificDayAndPuzzlePart2() throws Exception {
        mvc.perform(get(baseUrl + "/" + "?day=" + day1 + "&part=" + part2)
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("day", is(1)))
                .andExpect(jsonPath("part", is(2)))
//                .andExpect(jsonPath("answer", is("Product 1: " + 326211)))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost:8080" + baseUrl + "/" + "?day=" + day1 + "&part=" + part2)))
                .andDo(document("getResultForASpecificDayAndPuzzlePart",
                        preprocessResponse(prettyPrint()),
                        responseFields(getResultForASpecificDayAndPuzzlePart("")))
                );

    }

    @Test
    void testDaysImplemented() throws Exception {

        final List<Integer> daysImplementedIntegerList = new LinkedList<>(Arrays.asList(1, 2));

        mvc.perform(get(baseUrl + "/daysimplemented")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.integerList", is(daysImplementedIntegerList)))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost:8080" + baseUrl + "/daysimplemented")))
                .andDo(document(
                        "daysImplemented",
                        preprocessResponse(prettyPrint()),
                        responseFields(daysImplemented(""))
                        )
                );
    }

    private ArrayList<FieldDescriptor> getResultForASpecificDayAndPuzzlePart(final String path) {
        final String pathString;
        if (path.isEmpty()) {
            pathString = "";
        } else pathString = path;

        final ArrayList<FieldDescriptor> fieldDescriptorList = new ArrayList<>();
        fieldDescriptorList.add(fieldWithPath(pathString + "day")
                .description("Specific day of the puzzle of the AdventOfCode calendar"));
        fieldDescriptorList.add(fieldWithPath(pathString + "part")
                .description("Specific day's part of the puzzle of the AdventOfCode calendar"));
        fieldDescriptorList.add(fieldWithPath(pathString + "answer")
                .description("Result of the Puzzle for a specific day and part of the AdventOfCode calendar"));
        fieldDescriptorList.add(fieldWithPath(pathString + "_links.self.href")
                .description("Self link to the query for the specific solution for a day and part"));

        return fieldDescriptorList;
    }

    private ArrayList<FieldDescriptor> daysImplemented(final String path) {
        final String pathString;
        if (path.isEmpty()) {
            pathString = "";
        } else pathString = path;

        final ArrayList<FieldDescriptor> fieldDescriptorList = new ArrayList<>();
        fieldDescriptorList.add(fieldWithPath(pathString + "_embedded.integerList")
                .description("List of all implemented days"));
        fieldDescriptorList.add(fieldWithPath(pathString + "_links.self.href")
                .description("Self link to the query for the specific solution for a day and part"));

        return fieldDescriptorList;
    }
}