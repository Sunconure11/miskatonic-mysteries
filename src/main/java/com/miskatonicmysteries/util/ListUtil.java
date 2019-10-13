package com.miskatonicmysteries.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ListUtil {
    public static List<?> iterableToShuffledList(Iterable<?> iterable){
        List<?> list = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
        Collections.shuffle(list);
        return list;
    }
}
