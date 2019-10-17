package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.misc.spells.Spell;
import com.miskatonicmysteries.common.misc.spells.SpellHeal;
import com.miskatonicmysteries.common.misc.spells.SpellFeast;
import com.miskatonicmysteries.common.misc.spells.SpellYellowSign;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ModSpells {
    public static Spell heal;
    public static Spell feast;
    public static Spell yellow_sign;


    public static void init(){
        heal = new SpellHeal();
        feast = new SpellFeast();
        yellow_sign = new SpellYellowSign();
    }
}
