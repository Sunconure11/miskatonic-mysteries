package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.misc.spells.Spell;
import com.miskatonicmysteries.common.misc.spells.SpellHeal;
import com.miskatonicmysteries.common.misc.spells.SpellFeast;
import com.miskatonicmysteries.common.misc.spells.SpellYellowSign;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ModSpells {
    public static final Spell HEAL = new SpellHeal();
    public static final Spell FEAST = new SpellFeast();
    public static final Spell YELLOW_SIGN = new SpellYellowSign();
}
