package org.example.maus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DuplicateIntegersSantanderAutoSoftwareTest {

    @DisplayName("Given a set of integer When find duplicate integers Then return sorted set")
    @Test
    public void search_for_duplicates_and_produce_sorted_list() {
        var text = search_duplicates_sort_them(List.of(1, 2, 3, 4, 5, 6, 7, 8, 7, 4));
        assertThat(text, is(notNullValue()));
        assertThat(text, is("4, 7"));
    }

    @DisplayName("Validate Search for Duplicate Integers")
    @ParameterizedTest(name = "{index} ==> expected ''{0}'' from list {1}")
    @MethodSource("expectedStringNumberListProvider")
    void validate_search_for_duplicates_sort(String expectedText, List<Integer> numberList) {
        var actual = search_duplicates_sort_them(numberList);
        assertThat(actual, is(notNullValue()));
        assertThat(actual, is(expectedText));
    }

    static Stream<Arguments> expectedStringNumberListProvider() {
        return Stream.of(
                arguments("4", List.of(1, 2, 3, 4, 5, 6, 7, 8, 4)),
                arguments("4, 7", List.of(1, 2, 3, 4, 5, 6, 7, 8, 7, 4)),
                arguments("3", List.of(3,3,3,3,3,3)),
                arguments("1, 2, 3", List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 3, 2, 1)),
                arguments("2, 4, 6", List.of(9, 8, 7, 6, 5, 4, 3, 2, 1, 6, 4, 2)),
                arguments("1, 3, 6, 9", List.of(3, 2, 6, 1, 5, 9, 6, 3, 9, 4, 6, 6, 1)),
                arguments("", List.of())
                );
    }
    public String search_duplicates_sort_them(List<Integer> numberList) {
        var histograph = new HashMap<Integer, Integer>();
        numberList.stream().forEach(digit -> {
                    if (histograph.containsKey(digit)) {
                        histograph.put(digit, histograph.get(digit) + 1);
                    } else {
                        histograph.put(digit, 1);
                    }
                }
        );

        System.out.printf("histograph=%s\n", histograph);

        var duplicates = histograph.entrySet().stream().filter(tuple -> {
            return tuple.getValue() > 1;
        }).map(tuple -> tuple.getKey()).collect(Collectors.toList());
        Collections.sort(duplicates);

        System.out.printf("duplicates=%s\n", duplicates);

        var buf = new StringBuilder();
        for (int i = 0; i < duplicates.size(); i++) {
            if (i != 0) {
                buf.append(", ");
            }
            buf.append(duplicates.get(i));
        }
        System.out.printf(">>>> buf='%s'\n\n", buf.toString());
        return buf.toString();
    }
}
