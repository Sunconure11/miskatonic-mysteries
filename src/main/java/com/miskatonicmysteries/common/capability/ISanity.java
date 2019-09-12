package com.miskatonicmysteries.common.capability;

import java.util.Map;

public interface ISanity {
    int getSanity();

    void setSanity(int sanity);

    boolean isDirty();

    void setDirty(boolean dirty);

    int getSanityMax();

    int getTotalIncrease();

    Map<String, Integer> getExpansionMap();

    void addExpansion(String id, int value);
}
