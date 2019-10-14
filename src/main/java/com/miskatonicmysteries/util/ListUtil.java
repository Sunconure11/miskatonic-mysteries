package com.miskatonicmysteries.util;

import com.miskatonicmysteries.common.misc.spells.Spell;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ListUtil {
    public static List<?> iterableToShuffledList(Iterable<?> iterable){
        List<?> list = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
        Collections.shuffle(list);
        return list;
    }
}
