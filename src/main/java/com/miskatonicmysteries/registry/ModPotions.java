package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.common.capability.blessing.blessings.Blessing;
import com.miskatonicmysteries.common.potion.*;

public class ModPotions {
    public static PotionTranquilized tranquilized = new PotionTranquilized();
    public static PotionMania mania = new PotionMania();
    public static PotionDefyDeath defy_death = new PotionDefyDeath();
    public static PotionHungerExotic hunger_exotic = new PotionHungerExotic();

    public static PotionLazarus lazarus = new PotionLazarus();

    public static PotionBlessedBase shub_blessed = new PotionBlessedBase("shub_blessing", 3746048, Blessing.SHUB);
    public static PotionBlessedBase hastur_blessed = new PotionBlessedBase("hastur_blessing", 13678080, Blessing.HASTUR);
}