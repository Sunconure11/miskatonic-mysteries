package com.miskatonicmysteries.registry;

import com.miskatonicmysteries.MiskatonicMysteries;
import com.miskatonicmysteries.common.misc.spells.Spell;
import com.miskatonicmysteries.common.misc.spells.SpellHeal;
import com.miskatonicmysteries.common.misc.spells.SpellFeast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ModSpells {
    public static Spell heal;
    public static Spell feast;


    public static Spell feast1;
    public static Spell feast2;
    public static Spell feast3;
    public static Spell feast4;
    public static Spell feast5;
    public static Spell feast6;
    public static Spell feast7;
    public static Spell feast8;
    public static Spell feast9;


    public static void init(){
        heal = new SpellHeal();
        feast = new SpellFeast()
        ;
        feast1 = new Spell(new ResourceLocation(MiskatonicMysteries.MODID, "1")) {
            @Override
            public void cast(EntityPlayer caster) {

            }

            @Override
            public void price(EntityPlayer caster) {

            }
        };
        feast2 = new Spell(new ResourceLocation(MiskatonicMysteries.MODID, "2")) {
            @Override
            public void cast(EntityPlayer caster) {

            }

            @Override
            public void price(EntityPlayer caster) {

            }
        };
        feast3 = new Spell(new ResourceLocation(MiskatonicMysteries.MODID, "3")) {
            @Override
            public void cast(EntityPlayer caster) {

            }

            @Override
            public void price(EntityPlayer caster) {

            }
        };
        feast4 = new Spell(new ResourceLocation(MiskatonicMysteries.MODID, "4")) {
            @Override
            public void cast(EntityPlayer caster) {

            }

            @Override
            public void price(EntityPlayer caster) {

            }
        };
        feast5 = new Spell(new ResourceLocation(MiskatonicMysteries.MODID, "5")) {
            @Override
            public void cast(EntityPlayer caster) {

            }

            @Override
            public void price(EntityPlayer caster) {

            }
        };
        feast6 = new Spell(new ResourceLocation(MiskatonicMysteries.MODID, "6")) {
            @Override
            public void cast(EntityPlayer caster) {

            }

            @Override
            public void price(EntityPlayer caster) {

            }
        };
        feast7 = new Spell(new ResourceLocation(MiskatonicMysteries.MODID, "7")) {
            @Override
            public void cast(EntityPlayer caster) {

            }

            @Override
            public void price(EntityPlayer caster) {

            }
        };

        feast8 = new Spell(new ResourceLocation(MiskatonicMysteries.MODID, "8")) {
            @Override
            public void cast(EntityPlayer caster) {

            }

            @Override
            public void price(EntityPlayer caster) {

            }
        };

        feast9 = new Spell(new ResourceLocation(MiskatonicMysteries.MODID, "9")) {
            @Override
            public void cast(EntityPlayer caster) {

            }

            @Override
            public void price(EntityPlayer caster) {

            }
        };
    }
}
