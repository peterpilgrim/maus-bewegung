package org.example.maus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class DuplicateIntegersSantanderAutoSoftwareTest {

    @DisplayName("Given a set of integer When find duplicate integers Then return sorted set")
    @Test
    public void search_duplicates_sort_them(){
        var data = List.of(1,2,3,4,5,6,7,8,7,4);
        var histograph = new HashMap<Integer,Integer>();
        data.stream().forEach( digit -> {
                    if ( histograph.containsKey(digit)) {
                        histograph.put( digit, histograph.get(digit)+1);
                    } else {
                        histograph.put( digit, 1);
                    }
                }
        );

        System.out.println(histograph);

        var duplicates = histograph.entrySet().stream().filter( tuple -> {
            return tuple.getValue() > 1;
        }).map( tuple -> tuple.getKey()).collect(Collectors.toList());
        Collections.sort(duplicates);

        System.out.println(duplicates);
    }
}
