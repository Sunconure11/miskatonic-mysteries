package com.miskatonicmysteries.common.capability.blessing.blessings;

import java.util.HashMap;
import java.util.Map;

public class Blessing {
    protected String name;
    private static Map<String, Blessing> blessings = new HashMap<>();

    public static final Blessing NONE = addBlessing(new Blessing("none"));
    public static final Blessing SHUB = addBlessing(new Blessing("shub"));
    public static final Blessing HASTUR = addBlessing(new Blessing("hastur"));


    public Blessing(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //todo? add event stuff later

    public static Blessing addBlessing(Blessing blessing){
        blessings.put(blessing.name, blessing);
        return blessing;
    }

    public static Blessing getBlessing(String name){
        return blessings.getOrDefault(name, NONE);
    }

    public static Blessing getBlessingWithNull(String name){
        return blessings.getOrDefault(name, null);
    }

    public static String[] getBlessings(){
        return blessings.keySet().toArray(new String[blessings.keySet().size()]);
    }
}
