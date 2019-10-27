package com.miskatonicmysteries.client.model.entity.dark_young;

import com.miskatonicmysteries.common.entity.EntityDarkYoung;
import com.miskatonicmysteries.util.ModelAnimUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * dark_young_1 - cybecat5555
 * Created using Tabula 7.0.1
 */
public class ModelDarkYoung extends ModelBase {
    public ModelRenderer chest;
    public ModelRenderer body;

    public ModelRenderer lLegF;
    public ModelRenderer lForeleg02;
    public ModelRenderer lForeleg03;
    public ModelRenderer lForeHoof;

    public ModelRenderer rLegF; //rightForeLeg1
    public ModelRenderer rForeleg02;
    public ModelRenderer rForeleg03;
    public ModelRenderer rForeHoof;

    public ModelRenderer lLegH;
    public ModelRenderer lHindLeg02;
    public ModelRenderer lHindLeg03;
    public ModelRenderer lHindHoof;

    public ModelRenderer rLegH;
    public ModelRenderer rHindLeg02;
    public ModelRenderer rHindLeg03;
    public ModelRenderer rHindHoof;

    public ModelRenderer lhHoofClaw01a;
    public ModelRenderer lrHoofClaw02a;
    public ModelRenderer lhHoofClaw01b;
    public ModelRenderer lrHoofClaw02b;

    public ModelRenderer rhHoofClaw01a;
    public ModelRenderer rhHoofClaw02a;
    public ModelRenderer rhHoofClaw01b;
    public ModelRenderer lhHoofClaw02b;

    public ModelRenderer lfHoofClaw01a;
    public ModelRenderer lfHoofClaw02a;
    public ModelRenderer lfHoofClaw01b;
    public ModelRenderer lfHoofClaw02b;
    public ModelRenderer rfHoofClaw01a;
    public ModelRenderer rfHoofClaw02a;
    public ModelRenderer rfHoofClaw01b;
    public ModelRenderer lfHoofClaw02b_1;

    public ModelRenderer lHorn01;
    public ModelRenderer rHorn01;

    public ModelRenderer lJaw;
    public ModelRenderer rJaw;

    public ModelRenderer[] tentacle01 = new ModelRenderer[6];
    public ModelRenderer[] tentacle02 = new ModelRenderer[6];
    public ModelRenderer[] tentacle03 = new ModelRenderer[4];
    public ModelRenderer[] tentacle04 = new ModelRenderer[4];
    public ModelRenderer[] tentacle05 = new ModelRenderer[5];
    public ModelRenderer[] tentacle06 = new ModelRenderer[5];
    public ModelRenderer[] tentacle07 = new ModelRenderer[4];
    public ModelRenderer[] tentacle08 = new ModelRenderer[4];
    public ModelRenderer[] tentacle09 = new ModelRenderer[4];
    public ModelRenderer[] tentacle10 = new ModelRenderer[4];

    public ModelRenderer[][] tentacles = {tentacle01, tentacle02, tentacle03, tentacle04, tentacle05,tentacle06, tentacle07, tentacle08, tentacle09, tentacle10};

    //[cycle] [angle]
    private static float[][] tentacle01Anims = {
            {0.1F, 0.4F, 0.3F},
            {0.2F, 0.2F, 0.1F},
            {0.15F, 0.6F, 0.25F},
            {0.12F, 0.1F, 0.17F},
            {0.5F, 0.2F, 0.19F}
    };
    private static float[][] tentacle02Anims = {
            {0.1F, 0.4F, 0.3F},
            {0.2F, 0.2F, 0.1F},
            {0.15F, 0.6F, 0.25F},
            {0.12F, 0.1F, 0.17F},
            {0.5F, 0.2F, 0.19F}
    };
    private static float[][] tentacle03Anims = {
            {0.1F, 0.4F, 0.3F},
            {0.2F, 0.2F, 0.1F},
            {0.15F, 0.6F, 0.25F},
            {0.12F, 0.1F, 0.17F},
            {0.5F, 0.2F, 0.19F}
    };
    private static float[][]tentacle04Anims = {
            {0.1F, 0.4F, 0.3F},
            {0.2F, 0.2F, 0.1F},
            {0.15F, 0.6F, 0.25F},
            {0.12F, 0.1F, 0.17F},
            {0.5F, 0.2F, 0.19F}
    };
    private static float[][] tentacle05Anims = {
            {0.1F, 0.4F, 0.3F},
            {0.2F, 0.2F, 0.1F},
            {0.15F, 0.6F, 0.25F},
            {0.12F, 0.1F, 0.17F},
            {0.5F, 0.2F, 0.19F}
    };
    private static float[][] tentacle06Anims = {
            {0.1F, 0.4F, 0.3F},
            {0.2F, 0.2F, 0.1F},
            {0.15F, 0.6F, 0.25F},
            {0.12F, 0.1F, 0.17F},
            {0.5F, 0.2F, 0.19F}
    };
    private static float[][] tentacle07Anims = {
            {0.1F, 0.4F, 0.3F},
            {0.2F, 0.2F, 0.1F},
            {0.15F, 0.6F, 0.25F},
            {0.12F, 0.1F, 0.17F},
            {0.5F, 0.2F, 0.19F}
    };
    private static float[][] tentacle08Anims = {
            {0.1F, 0.4F, 0.3F},
            {0.2F, 0.2F, 0.1F},
            {0.15F, 0.6F, 0.25F},
            {0.12F, 0.1F, 0.17F},
            {0.5F, 0.2F, 0.19F}
    };
    private static float[][] tentacle09Anims = {
            {0.1F, 0.4F, 0.3F},
            {0.2F, 0.2F, 0.1F},
            {0.15F, 0.6F, 0.25F},
            {0.12F, 0.1F, 0.17F},
            {0.5F, 0.2F, 0.19F}
    };
    private static float[][] tentacle10Anims = {
            {0.1F, 0.4F, 0.3F},
            {0.2F, 0.2F, 0.1F},
            {0.15F, 0.6F, 0.25F},
            {0.12F, 0.1F, 0.17F},
            {0.5F, 0.2F, 0.19F}
    };

    public ModelRenderer fur01;
    public ModelRenderer fur02;
    public ModelRenderer fur03;
    public ModelRenderer rear;
    public ModelRenderer fur04;
    public ModelRenderer tail;
    public ModelRenderer tailFur;
    public ModelRenderer lJawB;
    public ModelRenderer lJawC;
    public ModelRenderer rJawB;
    public ModelRenderer rJawC;
    public ModelRenderer lHorn02;
    public ModelRenderer lHorn03;
    public ModelRenderer rHorn02;
    public ModelRenderer rHorn03;


    public ModelDarkYoung() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.tentacle01[0] = new ModelRenderer(this, 36, 36);
        this.tentacle01[0].setRotationPoint(2.5F, -3.3F, 3.2F);
        this.tentacle01[0].addBox(-2.5F, -6.0F, -2.5F, 5, 6, 5, 0.0F);
        this.setRotateAngle(tentacle01[0], -0.20943951023931953F, 0.0F, 0.5235987755982988F);
        this.lfHoofClaw02b_1 = new ModelRenderer(this, 65, 5);
        this.lfHoofClaw02b_1.setRotationPoint(0.0F, 0.4F, -0.5F);
        this.lfHoofClaw02b_1.addBox(-0.5F, -0.5F, -2.8F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lfHoofClaw02b_1, -0.13962634015954636F, 0.13962634015954636F, 0.7853981633974483F);
        this.fur02 = new ModelRenderer(this, 105, 34);
        this.fur02.setRotationPoint(0.0F, 3.1F, -4.2F);
        this.fur02.addBox(-3.5F, 0.0F, -0.7F, 7, 8, 2, 0.0F);
        this.setRotateAngle(fur02, 0.08726646259971647F, 0.0F, 0.0F);
        this.rForeHoof = new ModelRenderer(this, 32, 0);
        this.rForeHoof.mirror = true;
        this.rForeHoof.setRotationPoint(-0.0F, 10.3F, 0.3F);
        this.rForeHoof.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 3, 0.0F);
        this.lrHoofClaw02b = new ModelRenderer(this, 65, 5);
        this.lrHoofClaw02b.setRotationPoint(0.0F, 0.4F, -0.5F);
        this.lrHoofClaw02b.addBox(-0.5F, -0.5F, -2.8F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lrHoofClaw02b, -0.13962634015954636F, 0.13962634015954636F, 0.7853981633974483F);
        this.tentacle02[5] = new ModelRenderer(this, 99, 41);
        this.tentacle02[5].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle02[5].addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(tentacle02[5], 0.0F, 0.0F, -0.22689280275926282F);
        this.tentacle03[3] = new ModelRenderer(this, 99, 41);
        this.tentacle03[3].setRotationPoint(0.0F, -4.0F, 0.0F);
        this.tentacle03[3].addBox(-0.5F, -3.9F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(tentacle03[3], 0.0F, 0.0F, -0.2617993877991494F);
        this.rHindLeg03 = new ModelRenderer(this, 52, 25);
        this.rHindLeg03.mirror = true;
        this.rHindLeg03.setRotationPoint(0.5F, 6.4F, 0.5F);
        this.rHindLeg03.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(rHindLeg03, -0.5009094953223726F, 0.0F, -0.2617993877991494F);
        this.lHindHoof = new ModelRenderer(this, 32, 0);
        this.lHindHoof.setRotationPoint(-0.0F, 9.4F, 0.3F);
        this.lHindHoof.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 3, 0.0F);
        this.setRotateAngle(lHindHoof, 0.0F, 0.0F, 0.05235987755982988F);
        this.tentacle04[2] = new ModelRenderer(this, 89, 40);
        this.tentacle04[2].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle04[2].addBox(-1.0F, -4.5F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(tentacle04[2], 0.0F, 0.0F, -0.4363323129985824F);
        this.lJawC = new ModelRenderer(this, 111, 9);
        this.lJawC.setRotationPoint(-1.9F, -1.9F, -1.4F);
        this.lJawC.addBox(-1.5F, -3.4F, -1.0F, 3, 4, 2, 0.0F);
        this.setRotateAngle(lJawC, -0.7853981633974483F, 0.0F, 0.0F);
        this.rForeleg03 = new ModelRenderer(this, 52, 25);
        this.rForeleg03.mirror = true;
        this.rForeleg03.setRotationPoint(0.4F, 4.2F, 0.0F);
        this.rForeleg03.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(rForeleg03, -0.10471975511965977F, 0.0F, -0.13962634015954636F);
        this.tail = new ModelRenderer(this, 64, 15);
        this.tail.setRotationPoint(0.0F, -4.1F, 6.3F);
        this.tail.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 4, 0.0F);
        this.setRotateAngle(tail, 0.6981317007977318F, 0.0F, 0.0F);
        this.tentacle05[0] = new ModelRenderer(this, 59, 38);
        this.tentacle05[0].setRotationPoint(-1.0F, -4.0F, 5.7F);
        this.tentacle05[0].addBox(-2.0F, -8.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(tentacle05[0], -0.5759586531581287F, 0.0F, -0.2617993877991494F);
        this.chest = new ModelRenderer(this, 0, 0);
        this.chest.setRotationPoint(0.0F, 2.0F, -6.0F);
        this.chest.addBox(-4.5F, -5.5F, -5.0F, 9, 11, 9, 0.0F);
        this.setRotateAngle(chest, -0.08726646259971647F, 0.0F, 0.0F);
        this.lForeHoof = new ModelRenderer(this, 32, 0);
        this.lForeHoof.setRotationPoint(-0.0F, 10.3F, 0.3F);
        this.lForeHoof.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 3, 0.0F);
        this.rForeleg02 = new ModelRenderer(this, 48, 14);
        this.rForeleg02.setRotationPoint(-0.8F, 5.6F, 0.6F);
        this.rForeleg02.addBox(-0.9F, -0.3F, -2.0F, 3, 6, 4, 0.0F);
        this.setRotateAngle(rForeleg02, 0.0F, 0.0F, -0.2617993877991494F);
        this.rJaw = new ModelRenderer(this, 100, 17);
        this.rJaw.setRotationPoint(-2.5F, -3.1F, -3.6F);
        this.rJaw.addBox(0.0F, -2.5F, -2.5F, 4, 5, 3, 0.0F);
        this.setRotateAngle(rJaw, -0.5759586531581287F, 0.10471975511965977F, -0.08726646259971647F);
        this.rhHoofClaw02a = new ModelRenderer(this, 65, 0);
        this.rhHoofClaw02a.mirror = true;
        this.rhHoofClaw02a.setRotationPoint(0.7F, 0.7F, -2.1F);
        this.rhHoofClaw02a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rhHoofClaw02a, 0.4363323129985824F, -0.08726646259971647F, 0.0F);
        this.rfHoofClaw01b = new ModelRenderer(this, 65, 5);
        this.rfHoofClaw01b.setRotationPoint(0.0F, 0.4F, -0.5F);
        this.rfHoofClaw01b.addBox(-0.5F, -0.5F, -2.8F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rfHoofClaw01b, -0.13962634015954636F, 0.13962634015954636F, 0.7853981633974483F);
        this.lfHoofClaw02a = new ModelRenderer(this, 65, 0);
        this.lfHoofClaw02a.setRotationPoint(-0.7F, 0.7F, -2.1F);
        this.lfHoofClaw02a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lfHoofClaw02a, 0.4363323129985824F, 0.08726646259971647F, 0.0F);
        this.fur04 = new ModelRenderer(this, 31, 48);
        this.fur04.setRotationPoint(0.0F, 4.0F, 4.0F);
        this.fur04.addBox(-3.0F, 0.0F, -0.7F, 6, 5, 10, 0.0F);
        this.tentacle06[2] = new ModelRenderer(this, 76, 37);
        this.tentacle06[2].setRotationPoint(0.0F, -6.0F, 0.0F);
        this.tentacle06[2].addBox(-1.5F, -7.0F, -1.5F, 3, 7, 3, 0.0F);
        this.setRotateAngle(tentacle06[2], -0.13962634015954636F, 0.0F, 0.22689280275926282F);
        this.lrHoofClaw02a = new ModelRenderer(this, 65, 0);
        this.lrHoofClaw02a.setRotationPoint(-0.7F, 0.7F, -2.1F);
        this.lrHoofClaw02a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lrHoofClaw02a, 0.4363323129985824F, 0.08726646259971647F, 0.0F);
        this.tentacle04[1] = new ModelRenderer(this, 76, 37);
        this.tentacle04[1].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle04[1].addBox(-1.5F, -5.3F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(tentacle04[1], 0.22689280275926282F, 0.0F, -0.17453292519943295F);
        this.rJawB = new ModelRenderer(this, 100, 26);
        this.rJawB.setRotationPoint(1.9F, 1.8F, -1.4F);
        this.rJawB.addBox(-1.5F, 0.0F, -1.0F, 3, 4, 2, 0.0F);
        this.setRotateAngle(rJawB, 0.7853981633974483F, 0.0F, 0.0F);
        this.rfHoofClaw01a = new ModelRenderer(this, 65, 0);
        this.rfHoofClaw01a.mirror = true;
        this.rfHoofClaw01a.setRotationPoint(-0.8F, 0.7F, -2.1F);
        this.rfHoofClaw01a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rfHoofClaw01a, 0.4363323129985824F, 0.08726646259971647F, 0.0F);
        this.rLegH = new ModelRenderer(this, 78, 0);
        this.rLegH.mirror = true;
        this.rLegH.setRotationPoint(-2.5F, -1.3F, 3.3F);
        this.rLegH.addBox(-3.0F, -1.9F, -2.2F, 4, 11, 6, 0.0F);
        this.setRotateAngle(rLegH, -0.22689280275926282F, 0.0F, 0.4363323129985824F);
        this.tentacle08[2] = new ModelRenderer(this, 89, 40);
        this.tentacle08[2].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle08[2].addBox(-1.0F, -5.7F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(tentacle08[2], 0.20943951023931953F, 0.0F, 0.0F);
        this.tentacle10[2] = new ModelRenderer(this, 89, 40);
        this.tentacle10[2].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle10[2].addBox(-1.0F, -5.7F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(tentacle10[2], 0.20943951023931953F, 0.0F, -0.3141592653589793F);
        this.tentacle01[5] = new ModelRenderer(this, 99, 41);
        this.tentacle01[5].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle01[5].addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(tentacle01[5], 0.12217304763960307F, 0.0F, 0.22689280275926282F);
        this.tentacle09[0] = new ModelRenderer(this, 76, 37);
        this.tentacle09[0].setRotationPoint(1.0F, -4.0F, 0.2F);
        this.tentacle09[0].addBox(-1.5F, -8.0F, -1.5F, 3, 9, 3, 0.0F);
        this.setRotateAngle(tentacle09[0], -0.3141592653589793F, 0.0F, 0.40142572795869574F);
        this.tentacle01[2] = new ModelRenderer(this, 76, 37);
        this.tentacle01[2].setRotationPoint(0.0F, -8.0F, 0.0F);
        this.tentacle01[2].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle01[2], 0.20943951023931953F, 0.0F, -0.22689280275926282F);
        this.tentacle02[3] = new ModelRenderer(this, 76, 37);
        this.tentacle02[3].mirror = true;
        this.tentacle02[3].setRotationPoint(0.0F, -7.5F, 0.0F);
        this.tentacle02[3].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle02[3], 0.0F, 0.0F, 0.22689280275926282F);
        this.tentacle07[3] = new ModelRenderer(this, 99, 41);
        this.tentacle07[3].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle07[3].addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(tentacle07[3], 0.12217304763960307F, 0.0F, 0.22689280275926282F);
        this.tentacle02[4] = new ModelRenderer(this, 89, 40);
        this.tentacle02[4].mirror = true;
        this.tentacle02[4].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle02[4].addBox(-1.0F, -5.7F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(tentacle02[4], 0.0F, 0.0F, -0.22689280275926282F);
        this.lLegH = new ModelRenderer(this, 78, 0);
        this.lLegH.setRotationPoint(2.5F, -1.3F, 3.3F);
        this.lLegH.addBox(-1.0F, -1.9F, -2.2F, 4, 11, 6, 0.0F);
        this.setRotateAngle(lLegH, -0.22689280275926282F, 0.0F, -0.4363323129985824F);
        this.lfHoofClaw01b = new ModelRenderer(this, 65, 5);
        this.lfHoofClaw01b.setRotationPoint(0.0F, 0.4F, -0.5F);
        this.lfHoofClaw01b.addBox(-0.5F, -0.5F, -2.8F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lfHoofClaw01b, -0.13962634015954636F, 0.13962634015954636F, 0.7853981633974483F);
        this.tentacle06[4] = new ModelRenderer(this, 99, 41);
        this.tentacle06[4].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle06[4].addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(tentacle06[4], 0.12217304763960307F, 0.0F, 0.22689280275926282F);
        this.tentacle04[3] = new ModelRenderer(this, 99, 41);
        this.tentacle04[3].setRotationPoint(0.0F, -4.0F, 0.0F);
        this.tentacle04[3].addBox(-0.5F, -3.9F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(tentacle04[3], 0.0F, 0.0F, 0.17453292519943295F);
        this.rhHoofClaw01b = new ModelRenderer(this, 65, 5);
        this.rhHoofClaw01b.mirror = true;
        this.rhHoofClaw01b.setRotationPoint(0.0F, 0.4F, -0.5F);
        this.rhHoofClaw01b.addBox(-0.5F, -0.5F, -2.8F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rhHoofClaw01b, -0.13962634015954636F, 0.13962634015954636F, 0.7853981633974483F);
        this.tentacle09[3] = new ModelRenderer(this, 99, 41);
        this.tentacle09[3].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle09[3].addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(tentacle09[3], 0.12217304763960307F, 0.0F, 0.22689280275926282F);
        this.fur01 = new ModelRenderer(this, 28, 22);
        this.fur01.setRotationPoint(0.0F, 1.8F, -4.5F);
        this.fur01.addBox(-3.1F, 0.0F, -0.7F, 6, 7, 2, 0.0F);
        this.setRotateAngle(fur01, -0.08726646259971647F, 0.0F, 0.0F);
        this.lForeleg03 = new ModelRenderer(this, 52, 25);
        this.lForeleg03.setRotationPoint(-0.4F, 4.2F, 0.0F);
        this.lForeleg03.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(lForeleg03, -0.10471975511965977F, 0.0F, 0.13962634015954636F);
        this.lHindLeg03 = new ModelRenderer(this, 52, 25);
        this.lHindLeg03.setRotationPoint(-0.5F, 6.4F, 0.5F);
        this.lHindLeg03.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(lHindLeg03, -0.5009094953223726F, 0.0F, 0.2617993877991494F);
        this.lfHoofClaw02b = new ModelRenderer(this, 65, 5);
        this.lfHoofClaw02b.setRotationPoint(0.0F, 0.4F, -0.5F);
        this.lfHoofClaw02b.addBox(-0.5F, -0.5F, -2.8F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lfHoofClaw02b, -0.13962634015954636F, 0.13962634015954636F, 0.7853981633974483F);
        this.tentacle02[0] = new ModelRenderer(this, 36, 36);
        this.tentacle02[0].setRotationPoint(-1.3F, -3.3F, 6.0F);
        this.tentacle02[0].addBox(-2.5F, -6.0F, -2.5F, 5, 6, 5, 0.0F);
        this.setRotateAngle(tentacle02[0], -0.20943951023931953F, 0.0F, -0.4363323129985824F);
        this.lfHoofClaw01a = new ModelRenderer(this, 65, 0);
        this.lfHoofClaw01a.setRotationPoint(0.8F, 0.7F, -2.1F);
        this.lfHoofClaw01a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lfHoofClaw01a, 0.4363323129985824F, -0.08726646259971647F, 0.0F);
        this.lhHoofClaw01a = new ModelRenderer(this, 65, 0);
        this.lhHoofClaw01a.setRotationPoint(0.8F, 0.7F, -2.1F);
        this.lhHoofClaw01a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lhHoofClaw01a, 0.4363323129985824F, -0.08726646259971647F, 0.0F);
        this.lhHoofClaw01b = new ModelRenderer(this, 65, 5);
        this.lhHoofClaw01b.setRotationPoint(0.0F, 0.4F, -0.5F);
        this.lhHoofClaw01b.addBox(-0.5F, -0.5F, -2.8F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lhHoofClaw01b, -0.13962634015954636F, 0.13962634015954636F, 0.7853981633974483F);
        this.body = new ModelRenderer(this, 0, 23);
        this.body.setRotationPoint(0.0F, 0.8F, 3.7F);
        this.body.addBox(-4.0F, -5.5F, 0.0F, 8, 10, 10, 0.0F);
        this.setRotateAngle(body, 0.08726646259971647F, 0.0F, 0.0F);
        this.tentacle06[0] = new ModelRenderer(this, 59, 38);
        this.tentacle06[0].setRotationPoint(2.3F, -4.0F, 4.3F);
        this.tentacle06[0].addBox(-2.0F, -8.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(tentacle06[0], -0.5759586531581287F, 0.0F, 0.12217304763960307F);
        this.tentacle07[0] = new ModelRenderer(this, 76, 37);
        this.tentacle07[0].setRotationPoint(3.8F, -4.9F, 9.1F);
        this.tentacle07[0].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle07[0], -0.5759586531581287F, 0.0F, 0.6283185307179586F);
        this.tentacle05[4] = new ModelRenderer(this, 99, 41);
        this.tentacle05[4].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle05[4].addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(tentacle05[4], 0.12217304763960307F, 0.0F, -0.2792526803190927F);
        this.rJawC = new ModelRenderer(this, 111, 26);
        this.rJawC.setRotationPoint(1.9F, -1.9F, -1.4F);
        this.rJawC.addBox(-1.5F, -3.4F, -1.0F, 3, 4, 2, 0.0F);
        this.setRotateAngle(rJawC, -0.7853981633974483F, 0.0F, 0.0F);
        this.tentacle10[0] = new ModelRenderer(this, 76, 37);
        this.tentacle10[0].setRotationPoint(-1.0F, -4.0F, -0.2F);
        this.tentacle10[0].addBox(-1.5F, -8.0F, -1.5F, 3, 9, 3, 0.0F);
        this.setRotateAngle(tentacle10[0], -0.3141592653589793F, 0.0F, -0.40142572795869574F);
        this.tentacle05[2] = new ModelRenderer(this, 76, 37);
        this.tentacle05[2].setRotationPoint(0.0F, -7.5F, 0.0F);
        this.tentacle05[2].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle05[2], -0.13962634015954636F, 0.0F, -0.4363323129985824F);
        this.fur03 = new ModelRenderer(this, 78, 48);
        this.fur03.setRotationPoint(0.0F, 5.6F, -2.1F);
        this.fur03.addBox(-3.5F, 0.0F, -0.7F, 7, 6, 10, 0.0F);
        this.setRotateAngle(fur03, 0.08726646259971647F, 0.0F, 0.0F);
        this.tentacle01[1] = new ModelRenderer(this, 59, 38);
        this.tentacle01[1].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle01[1].addBox(-2.0F, -8.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(tentacle01[1], -0.08726646259971647F, 0.0F, -0.22689280275926282F);
        this.tentacle08[1] = new ModelRenderer(this, 76, 37);
        this.tentacle08[1].setRotationPoint(0.0F, -6.0F, 0.0F);
        this.tentacle08[1].addBox(-1.5F, -7.0F, -1.5F, 3, 7, 3, 0.0F);
        this.setRotateAngle(tentacle08[1], -0.13962634015954636F, 0.0F, -0.12217304763960307F);
        this.lHorn02 = new ModelRenderer(this, 72, 50);
        this.lHorn02.setRotationPoint(0.0F, -2.7F, 0.0F);
        this.lHorn02.addBox(-1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(lHorn02, -0.13962634015954636F, 0.0F, -0.3141592653589793F);
        this.tentacle01[3] = new ModelRenderer(this, 76, 37);
        this.tentacle01[3].setRotationPoint(0.0F, -7.5F, 0.0F);
        this.tentacle01[3].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle01[3], -0.13962634015954636F, 0.0F, 0.22689280275926282F);
        this.tailFur = new ModelRenderer(this, 64, 25);
        this.tailFur.setRotationPoint(0.0F, 1.7F, -2.7F);
        this.tailFur.addBox(-1.5F, -1.0F, 0.3F, 3, 4, 7, 0.0F);
        this.lLegF = new ModelRenderer(this, 45, 0);
        this.lLegF.setRotationPoint(3.9F, 0.8F, -1.5F);
        this.lLegF.addBox(-1.0F, -2.4F, -2.5F, 4, 8, 6, 0.0F);
        this.setRotateAngle(lLegF, 0.19198621771937624F, 0.0F, -0.40142572795869574F);
        this.rHindLeg02 = new ModelRenderer(this, 80, 19);
        this.rHindLeg02.mirror = true;
        this.rHindLeg02.setRotationPoint(-1.2F, 8.1F, -0.9F);
        this.rHindLeg02.addBox(-1.5F, 0.0F, -1.5F, 4, 7, 4, 0.0F);
        this.setRotateAngle(rHindLeg02, 0.9075712110370513F, 0.0F, -0.17453292519943295F);
        this.tentacle08[3] = new ModelRenderer(this, 99, 41);
        this.tentacle08[3].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle08[3].addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(tentacle08[3], 0.12217304763960307F, 0.0F, 0.0F);
        this.tentacle02[1] = new ModelRenderer(this, 59, 38);
        this.tentacle02[1].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle02[1].addBox(-2.0F, -8.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(tentacle02[1], -0.17453292519943295F, 0.0F, 0.13962634015954636F);
        this.tentacle07[1] = new ModelRenderer(this, 76, 37);
        this.tentacle07[1].setRotationPoint(0.0F, -6.0F, 0.0F);
        this.tentacle07[1].addBox(-1.5F, -7.0F, -1.5F, 3, 7, 3, 0.0F);
        this.setRotateAngle(tentacle07[1], -0.13962634015954636F, 0.0F, -0.12217304763960307F);
        this.tentacle10[1] = new ModelRenderer(this, 76, 37);
        this.tentacle10[1].setRotationPoint(0.0F, -8.3F, 0.0F);
        this.tentacle10[1].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle10[1], -0.13962634015954636F, 0.0F, 0.4363323129985824F);
        this.lHorn01 = new ModelRenderer(this, 59, 50);
        this.lHorn01.setRotationPoint(3.5F, -5.1F, -2.8F);
        this.lHorn01.addBox(-1.5F, -3.0F, -1.5F, 3, 5, 3, 0.0F);
        this.setRotateAngle(lHorn01, -0.13962634015954636F, 0.0F, 0.3490658503988659F);
        this.tentacle02[2] = new ModelRenderer(this, 76, 37);
        this.tentacle02[2].mirror = true;
        this.tentacle02[2].setRotationPoint(0.0F, -8.0F, 0.0F);
        this.tentacle02[2].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle02[2], 0.13962634015954636F, 0.0F, -0.17453292519943295F);
        this.tentacle09[2] = new ModelRenderer(this, 89, 40);
        this.tentacle09[2].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle09[2].addBox(-1.0F, -5.7F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(tentacle09[2], 0.20943951023931953F, 0.0F, 0.3141592653589793F);
        this.lJawB = new ModelRenderer(this, 100, 9);
        this.lJawB.setRotationPoint(-1.9F, 1.8F, -1.4F);
        this.lJawB.addBox(-1.5F, 0.0F, -1.0F, 3, 4, 2, 0.0F);
        this.setRotateAngle(lJawB, 0.7853981633974483F, 0.0F, 0.0F);
        this.lForeleg02 = new ModelRenderer(this, 48, 14);
        this.lForeleg02.setRotationPoint(0.8F, 5.6F, 0.6F);
        this.lForeleg02.addBox(-1.9F, -0.3F, -2.0F, 3, 6, 4, 0.0F);
        this.setRotateAngle(lForeleg02, 0.0F, 0.0F, 0.2617993877991494F);
        this.rHorn01 = new ModelRenderer(this, 59, 50);
        this.rHorn01.mirror = true;
        this.rHorn01.setRotationPoint(-3.5F, -5.1F, -2.8F);
        this.rHorn01.addBox(-1.5F, -3.0F, -1.5F, 3, 5, 3, 0.0F);
        this.setRotateAngle(rHorn01, -0.13962634015954636F, 0.0F, -0.3490658503988659F);
        this.tentacle04[0] = new ModelRenderer(this, 76, 37);
        this.tentacle04[0].setRotationPoint(-1.9F, 0.0F, -1.8F);
        this.tentacle04[0].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle04[0], 0.0F, 0.0F, -0.2792526803190927F);
        this.tentacle08[0] = new ModelRenderer(this, 76, 37);
        this.tentacle08[0].setRotationPoint(-1.9F, -4.9F, 9.1F);
        this.tentacle08[0].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle08[0], -0.5759586531581287F, 0.0F, -0.45378560551852565F);
        this.tentacle06[3] = new ModelRenderer(this, 89, 40);
        this.tentacle06[3].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle06[3].addBox(-1.0F, -5.7F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(tentacle06[3], 0.20943951023931953F, 0.0F, 0.12217304763960307F);
        this.tentacle03[0] = new ModelRenderer(this, 76, 37);
        this.tentacle03[0].setRotationPoint(0.8F, -3.0F, -1.5F);
        this.tentacle03[0].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle03[0], 0.3490658503988659F, 0.0F, 0.08726646259971647F);
        this.rhHoofClaw01a = new ModelRenderer(this, 65, 0);
        this.rhHoofClaw01a.mirror = true;
        this.rhHoofClaw01a.setRotationPoint(-0.8F, 0.7F, -2.1F);
        this.rhHoofClaw01a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rhHoofClaw01a, 0.4363323129985824F, 0.08726646259971647F, 0.0F);
        this.tentacle05[3] = new ModelRenderer(this, 89, 40);
        this.tentacle05[3].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle05[3].addBox(-1.0F, -5.7F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(tentacle05[3], 0.20943951023931953F, 0.0F, 0.22689280275926282F);
        this.rHorn03 = new ModelRenderer(this, 81, 50);
        this.rHorn03.mirror = true;
        this.rHorn03.setRotationPoint(0.0F, -4.7F, 0.0F);
        this.rHorn03.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(rHorn03, -0.13962634015954636F, 0.0F, -0.3141592653589793F);
        this.tentacle06[1] = new ModelRenderer(this, 76, 37);
        this.tentacle06[1].setRotationPoint(0.0F, -8.0F, 0.0F);
        this.tentacle06[1].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle06[1], 0.20943951023931953F, 0.0F, -0.22689280275926282F);
        this.rHindHoof = new ModelRenderer(this, 32, 0);
        this.rHindHoof.mirror = true;
        this.rHindHoof.setRotationPoint(-0.0F, 9.4F, 0.3F);
        this.rHindHoof.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 3, 0.0F);
        this.setRotateAngle(rHindHoof, 0.0F, 0.0F, -0.05235987755982988F);
        this.rfHoofClaw02a = new ModelRenderer(this, 65, 0);
        this.rfHoofClaw02a.mirror = true;
        this.rfHoofClaw02a.setRotationPoint(0.7F, 0.7F, -2.1F);
        this.rfHoofClaw02a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rfHoofClaw02a, 0.4363323129985824F, -0.08726646259971647F, 0.0F);
        this.rHorn02 = new ModelRenderer(this, 72, 50);
        this.rHorn02.mirror = true;
        this.rHorn02.setRotationPoint(0.0F, -2.7F, 0.0F);
        this.rHorn02.addBox(-1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(rHorn02, -0.13962634015954636F, 0.0F, 0.3141592653589793F);
        this.tentacle05[1] = new ModelRenderer(this, 76, 37);
        this.tentacle05[1].setRotationPoint(0.0F, -8.0F, 0.0F);
        this.tentacle05[1].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle05[1], 0.20943951023931953F, 0.0F, 0.3490658503988659F);
        this.tentacle03[2] = new ModelRenderer(this, 89, 40);
        this.tentacle03[2].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle03[2].addBox(-1.0F, -4.5F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(tentacle03[2], 0.0F, 0.0F, 0.22689280275926282F);
        this.tentacle03[1] = new ModelRenderer(this, 76, 37);
        this.tentacle03[1].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle03[1].addBox(-1.5F, -5.3F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(tentacle03[1], -0.2617993877991494F, 0.0F, 0.22689280275926282F);
        this.rear = new ModelRenderer(this, 0, 45);
        this.rear.setRotationPoint(0.0F, -1.0F, 9.0F);
        this.rear.addBox(-3.5F, -5.0F, 0.0F, 7, 9, 7, 0.0F);
        this.setRotateAngle(rear, -0.17453292519943295F, 0.0F, 0.0F);
        this.lHindLeg02 = new ModelRenderer(this, 80, 19);
        this.lHindLeg02.setRotationPoint(1.2F, 8.1F, -0.9F);
        this.lHindLeg02.addBox(-2.5F, 0.0F, -1.5F, 4, 7, 4, 0.0F);
        this.setRotateAngle(lHindLeg02, 0.9075712110370513F, 0.0F, 0.17453292519943295F);
        this.lHorn03 = new ModelRenderer(this, 81, 50);
        this.lHorn03.setRotationPoint(0.0F, -4.7F, 0.0F);
        this.lHorn03.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(lHorn03, -0.13962634015954636F, 0.0F, 0.3141592653589793F);
        this.tentacle09[1] = new ModelRenderer(this, 76, 37);
        this.tentacle09[1].setRotationPoint(0.0F, -8.3F, 0.0F);
        this.tentacle09[1].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle09[1], -0.13962634015954636F, 0.0F, -0.4363323129985824F);
        this.lhHoofClaw02b = new ModelRenderer(this, 65, 5);
        this.lhHoofClaw02b.setRotationPoint(0.0F, 0.4F, -0.5F);
        this.lhHoofClaw02b.addBox(-0.5F, -0.5F, -2.8F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lhHoofClaw02b, -0.13962634015954636F, 0.13962634015954636F, 0.7853981633974483F);
        this.lJaw = new ModelRenderer(this, 100, 0);
        this.lJaw.setRotationPoint(3.0F, -3.1F, -3.6F);
        this.lJaw.addBox(-4.0F, -2.5F, -2.5F, 4, 5, 3, 0.0F);
        this.setRotateAngle(lJaw, -0.5759586531581287F, -0.10471975511965977F, 0.08726646259971647F);
        this.tentacle10[3] = new ModelRenderer(this, 99, 41);
        this.tentacle10[3].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle10[3].addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(tentacle10[3], 0.12217304763960307F, 0.0F, -0.22689280275926282F);
        this.tentacle01[4] = new ModelRenderer(this, 89, 40);
        this.tentacle01[4].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle01[4].addBox(-1.0F, -5.7F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(tentacle01[4], 0.20943951023931953F, 0.0F, 0.22689280275926282F);
        this.tentacle07[2] = new ModelRenderer(this, 89, 40);
        this.tentacle07[2].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle07[2].addBox(-1.0F, -5.7F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(tentacle07[2], 0.20943951023931953F, 0.0F, 0.12217304763960307F);
        this.rLegF = new ModelRenderer(this, 45, 0);
        this.rLegF.mirror = true;
        this.rLegF.setRotationPoint(-3.9F, 0.8F, -1.5F);
        this.rLegF.addBox(-2.5F, -2.4F, -2.5F, 4, 8, 6, 0.0F);
        this.setRotateAngle(rLegF, 0.19198621771937624F, 0.0F, 0.40142572795869574F);
        this.chest.addChild(this.tentacle01[0]);
        this.rfHoofClaw02a.addChild(this.lfHoofClaw02b_1);
        this.chest.addChild(this.fur02);
        this.rForeleg03.addChild(this.rForeHoof);
        this.lrHoofClaw02a.addChild(this.lrHoofClaw02b);
        this.tentacle02[4].addChild(this.tentacle02[5]);
        this.tentacle03[2].addChild(this.tentacle03[3]);
        this.rHindLeg02.addChild(this.rHindLeg03);
        this.lHindLeg03.addChild(this.lHindHoof);
        this.tentacle04[1].addChild(this.tentacle04[2]);
        this.lJaw.addChild(this.lJawC);
        this.rForeleg02.addChild(this.rForeleg03);
        this.rear.addChild(this.tail);
        this.body.addChild(this.tentacle05[0]);
        this.lForeleg03.addChild(this.lForeHoof);
        this.rLegF.addChild(this.rForeleg02);
        this.chest.addChild(this.rJaw);
        this.rHindHoof.addChild(this.rhHoofClaw02a);
        this.rfHoofClaw01a.addChild(this.rfHoofClaw01b);
        this.lForeHoof.addChild(this.lfHoofClaw02a);
        this.body.addChild(this.fur04);
        this.tentacle06[1].addChild(this.tentacle06[2]);
        this.lHindHoof.addChild(this.lrHoofClaw02a);
        this.tentacle04[0].addChild(this.tentacle04[1]);
        this.rJaw.addChild(this.rJawB);
        this.rForeHoof.addChild(this.rfHoofClaw01a);
        this.rear.addChild(this.rLegH);
        this.tentacle08[1].addChild(this.tentacle08[2]);
        this.tentacle10[1].addChild(this.tentacle10[2]);
        this.tentacle01[4].addChild(this.tentacle01[5]);
        this.chest.addChild(this.tentacle09[0]);
        this.tentacle01[1].addChild(this.tentacle01[2]);
        this.tentacle02[2].addChild(this.tentacle02[3]);
        this.tentacle07[2].addChild(this.tentacle07[3]);
        this.tentacle02[3].addChild(this.tentacle02[4]);
        this.rear.addChild(this.lLegH);
        this.lfHoofClaw01a.addChild(this.lfHoofClaw01b);
        this.tentacle06[3].addChild(this.tentacle06[4]);
        this.tentacle04[2].addChild(this.tentacle04[3]);
        this.rhHoofClaw01a.addChild(this.rhHoofClaw01b);
        this.tentacle09[2].addChild(this.tentacle09[3]);
        this.chest.addChild(this.fur01);
        this.lForeleg02.addChild(this.lForeleg03);
        this.lHindLeg02.addChild(this.lHindLeg03);
        this.lfHoofClaw02a.addChild(this.lfHoofClaw02b);
        this.chest.addChild(this.tentacle02[0]);
        this.lForeHoof.addChild(this.lfHoofClaw01a);
        this.lHindHoof.addChild(this.lhHoofClaw01a);
        this.lhHoofClaw01a.addChild(this.lhHoofClaw01b);
        this.chest.addChild(this.body);
        this.body.addChild(this.tentacle06[0]);
        this.body.addChild(this.tentacle07[0]);
        this.tentacle05[3].addChild(this.tentacle05[4]);
        this.rJaw.addChild(this.rJawC);
        this.chest.addChild(this.tentacle10[0]);
        this.tentacle05[1].addChild(this.tentacle05[2]);
        this.chest.addChild(this.fur03);
        this.tentacle01[0].addChild(this.tentacle01[1]);
        this.tentacle08[0].addChild(this.tentacle08[1]);
        this.lHorn01.addChild(this.lHorn02);
        this.tentacle01[2].addChild(this.tentacle01[3]);
        this.tail.addChild(this.tailFur);
        this.chest.addChild(this.lLegF);
        this.rLegH.addChild(this.rHindLeg02);
        this.tentacle08[2].addChild(this.tentacle08[3]);
        this.tentacle02[0].addChild(this.tentacle02[1]);
        this.tentacle07[0].addChild(this.tentacle07[1]);
        this.tentacle10[0].addChild(this.tentacle10[1]);
        this.chest.addChild(this.lHorn01);
        this.tentacle02[1].addChild(this.tentacle02[2]);
        this.tentacle09[1].addChild(this.tentacle09[2]);
        this.lJaw.addChild(this.lJawB);
        this.lLegF.addChild(this.lForeleg02);
        this.chest.addChild(this.rHorn01);
        this.tentacle02[0].addChild(this.tentacle04[0]);
        this.body.addChild(this.tentacle08[0]);
        this.tentacle06[2].addChild(this.tentacle06[3]);
        this.tentacle01[0].addChild(this.tentacle03[0]);
        this.rHindHoof.addChild(this.rhHoofClaw01a);
        this.tentacle05[2].addChild(this.tentacle05[3]);
        this.rHorn02.addChild(this.rHorn03);
        this.tentacle06[0].addChild(this.tentacle06[1]);
        this.rHindLeg03.addChild(this.rHindHoof);
        this.rForeHoof.addChild(this.rfHoofClaw02a);
        this.rHorn01.addChild(this.rHorn02);
        this.tentacle05[0].addChild(this.tentacle05[1]);
        this.tentacle03[1].addChild(this.tentacle03[2]);
        this.tentacle03[0].addChild(this.tentacle03[1]);
        this.body.addChild(this.rear);
        this.lLegH.addChild(this.lHindLeg02);
        this.lHorn02.addChild(this.lHorn03);
        this.tentacle09[0].addChild(this.tentacle09[1]);
        this.rhHoofClaw02a.addChild(this.lhHoofClaw02b);
        this.chest.addChild(this.lJaw);
        this.tentacle10[2].addChild(this.tentacle10[3]);
        this.tentacle01[3].addChild(this.tentacle01[4]);
        this.tentacle07[1].addChild(this.tentacle07[2]);
        this.chest.addChild(this.rLegF);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        for(ModelRenderer[] t: tentacles){
            for(ModelRenderer s: t){
                setRotateAngle(s, 0, 0, 0);
            }
        }

        this.setRotateAngle(tentacle01[0], -0.20943951023931953F, 0.0F, 0.5235987755982988F);
        this.setRotateAngle(lfHoofClaw02b_1, -0.13962634015954636F, 0.13962634015954636F, 0.7853981633974483F);
        this.setRotateAngle(fur02, 0.08726646259971647F, 0.0F, 0.0F);
        this.setRotateAngle(lrHoofClaw02b, -0.13962634015954636F, 0.13962634015954636F, 0.7853981633974483F);
        this.setRotateAngle(tentacle02[5], 0.0F, 0.0F, -0.22689280275926282F);
        this.setRotateAngle(tentacle03[3], 0.0F, 0.0F, -0.2617993877991494F);
        this.setRotateAngle(rHindLeg03, -0.5009094953223726F, 0.0F, -0.2617993877991494F);
        this.setRotateAngle(lHindHoof, 0.0F, 0.0F, 0.05235987755982988F);
        this.setRotateAngle(tentacle03[2], 0.0F, 0.0F, -0.4363323129985824F);
        this.setRotateAngle(lJawC, -0.7853981633974483F, 0.0F, 0.0F);
        this.setRotateAngle(rForeleg03, -0.10471975511965977F, 0.0F, -0.13962634015954636F);
        this.setRotateAngle(tail, 0.6981317007977318F, 0.0F, 0.0F);
        this.setRotateAngle(tentacle05[0], -0.5759586531581287F, 0.0F, -0.2617993877991494F);
        this.setRotateAngle(chest, -0.08726646259971647F, 0.0F, 0.0F);
        this.setRotateAngle(rForeleg02, 0.0F, 0.0F, -0.2617993877991494F);
        this.setRotateAngle(rJaw, -0.5759586531581287F, 0.10471975511965977F, -0.08726646259971647F);
        this.setRotateAngle(rhHoofClaw02a, 0.4363323129985824F, -0.08726646259971647F, 0.0F);
        this.setRotateAngle(lfHoofClaw02a, 0.4363323129985824F, 0.08726646259971647F, 0.0F);
        this.setRotateAngle(tentacle06[2], -0.13962634015954636F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(lrHoofClaw02a, 0.4363323129985824F, 0.08726646259971647F, 0.0F);
        this.setRotateAngle(tentacle04[1], 0.22689280275926282F, 0.0F, -0.17453292519943295F);
        this.setRotateAngle(rJawB, 0.7853981633974483F, 0.0F, 0.0F);
        this.setRotateAngle(rfHoofClaw01a, 0.4363323129985824F, 0.08726646259971647F, 0.0F);
        this.setRotateAngle(rLegH, -0.22689280275926282F, 0.0F, 0.4363323129985824F);
        this.setRotateAngle(tentacle08[2], 0.20943951023931953F, 0.0F, 0.0F);
        this.setRotateAngle(tentacle10[2], 0.20943951023931953F, 0.0F, -0.3141592653589793F);
        this.setRotateAngle(tentacle01[5], 0.12217304763960307F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(tentacle09[0], -0.3141592653589793F, 0.0F, 0.40142572795869574F);
        this.setRotateAngle(tentacle01[2], 0.20943951023931953F, 0.0F, -0.22689280275926282F);
        this.setRotateAngle(tentacle02[3], 0.0F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(tentacle07[3], 0.12217304763960307F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(tentacle02[4], 0.0F, 0.0F, -0.22689280275926282F);
        this.setRotateAngle(lLegH, -0.22689280275926282F, 0.0F, -0.4363323129985824F);
        this.setRotateAngle(lfHoofClaw01b, -0.13962634015954636F, 0.13962634015954636F, 0.7853981633974483F);
        this.setRotateAngle(tentacle06[4], 0.12217304763960307F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(tentacle04[3], 0.0F, 0.0F, 0.17453292519943295F);
        this.setRotateAngle(rhHoofClaw01b, -0.13962634015954636F, 0.13962634015954636F, 0.7853981633974483F);
        this.setRotateAngle(tentacle09[3], 0.12217304763960307F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(fur01, -0.08726646259971647F, 0.0F, 0.0F);
        this.setRotateAngle(lForeleg03, -0.10471975511965977F, 0.0F, 0.13962634015954636F);
        this.setRotateAngle(lHindLeg03, -0.5009094953223726F, 0.0F, 0.2617993877991494F);
        this.setRotateAngle(lfHoofClaw02b, -0.13962634015954636F, 0.13962634015954636F, 0.7853981633974483F);
        this.setRotateAngle(tentacle02[0], -0.20943951023931953F, 0.0F, -0.4363323129985824F);
        this.setRotateAngle(lfHoofClaw01a, 0.4363323129985824F, -0.08726646259971647F, 0.0F);
        this.setRotateAngle(lhHoofClaw01a, 0.4363323129985824F, -0.08726646259971647F, 0.0F);
        this.setRotateAngle(lhHoofClaw01b, -0.13962634015954636F, 0.13962634015954636F, 0.7853981633974483F);
        this.setRotateAngle(body, 0.08726646259971647F, 0.0F, 0.0F);
        this.setRotateAngle(tentacle06[0], -0.5759586531581287F, 0.0F, 0.12217304763960307F);
        this.setRotateAngle(tentacle07[0], -0.5759586531581287F, 0.0F, 0.6283185307179586F);
        this.setRotateAngle(tentacle05[4], 0.12217304763960307F, 0.0F, -0.2792526803190927F);
        this.setRotateAngle(rJawC, -0.7853981633974483F, 0.0F, 0.0F);
        this.setRotateAngle(tentacle05[2], -0.13962634015954636F, 0.0F, -0.4363323129985824F);
        this.setRotateAngle(fur03, 0.08726646259971647F, 0.0F, 0.0F);
        this.setRotateAngle(tentacle01[1], -0.08726646259971647F, 0.0F, -0.22689280275926282F);
        this.setRotateAngle(tentacle08[1], -0.13962634015954636F, 0.0F, -0.12217304763960307F);
        this.setRotateAngle(lHorn02, -0.13962634015954636F, 0.0F, -0.3141592653589793F);
        this.setRotateAngle(tentacle01[3], -0.13962634015954636F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(lLegF, 0.19198621771937624F, 0.0F, -0.40142572795869574F);
        this.setRotateAngle(rHindLeg02, 0.9075712110370513F, 0.0F, -0.17453292519943295F);
        this.setRotateAngle(tentacle08[3], 0.12217304763960307F, 0.0F, 0.0F);
        this.setRotateAngle(tentacle02[1], -0.17453292519943295F, 0.0F, 0.13962634015954636F);
        this.setRotateAngle(tentacle07[1], -0.13962634015954636F, 0.0F, -0.12217304763960307F);
        this.setRotateAngle(tentacle10[1], -0.13962634015954636F, 0.0F, 0.4363323129985824F);
        this.setRotateAngle(lHorn01, -0.13962634015954636F, 0.0F, 0.3490658503988659F);
        this.setRotateAngle(tentacle02[2], 0.13962634015954636F, 0.0F, -0.17453292519943295F);
        this.setRotateAngle(tentacle09[2], 0.20943951023931953F, 0.0F, 0.3141592653589793F);
        this.setRotateAngle(lJawB, 0.7853981633974483F, 0.0F, 0.0F);
        this.setRotateAngle(lForeleg02, 0.0F, 0.0F, 0.2617993877991494F);
        this.setRotateAngle(rHorn01, -0.13962634015954636F, 0.0F, -0.3490658503988659F);
        this.setRotateAngle(tentacle04[0], 0.0F, 0.0F, -0.2792526803190927F);
        this.setRotateAngle(tentacle08[0], -0.5759586531581287F, 0.0F, -0.45378560551852565F);
        this.setRotateAngle(tentacle06[3], 0.20943951023931953F, 0.0F, 0.12217304763960307F);
        this.setRotateAngle(tentacle03[0], 0.3490658503988659F, 0.0F, 0.08726646259971647F);
        this.setRotateAngle(rhHoofClaw01a, 0.4363323129985824F, 0.08726646259971647F, 0.0F);
        this.setRotateAngle(tentacle05[3], 0.20943951023931953F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(rHorn03, -0.13962634015954636F, 0.0F, -0.3141592653589793F);
        this.setRotateAngle(tentacle06[1], 0.20943951023931953F, 0.0F, -0.22689280275926282F);
        this.setRotateAngle(rHindHoof, 0.0F, 0.0F, -0.05235987755982988F);
        this.setRotateAngle(rfHoofClaw02a, 0.4363323129985824F, -0.08726646259971647F, 0.0F);
        this.setRotateAngle(rHorn02, -0.13962634015954636F, 0.0F, 0.3141592653589793F);
        this.setRotateAngle(tentacle05[1], 0.20943951023931953F, 0.0F, 0.3490658503988659F);
        this.setRotateAngle(tentacle03[2], 0.0F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(tentacle03[1], -0.2617993877991494F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(rear, -0.17453292519943295F, 0.0F, 0.0F);
        this.setRotateAngle(lHindLeg02, 0.9075712110370513F, 0.0F, 0.17453292519943295F);
        this.setRotateAngle(lHorn03, -0.13962634015954636F, 0.0F, 0.3141592653589793F);
        this.setRotateAngle(tentacle09[1], -0.13962634015954636F, 0.0F, -0.4363323129985824F);
        this.setRotateAngle(lhHoofClaw02b, -0.13962634015954636F, 0.13962634015954636F, 0.7853981633974483F);
        this.setRotateAngle(lJaw, -0.5759586531581287F, -0.10471975511965977F, 0.08726646259971647F);
        this.setRotateAngle(tentacle10[3], 0.12217304763960307F, 0.0F, -0.22689280275926282F);
        this.setRotateAngle(tentacle01[4], 0.20943951023931953F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(tentacle07[2], 0.20943951023931953F, 0.0F, 0.12217304763960307F);
        this.setRotateAngle(rLegF, 0.19198621771937624F, 0.0F, 0.40142572795869574F);

        if (entityIn instanceof EntityDarkYoung) {
            doWalkingAnims(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, (EntityDarkYoung) entityIn);
            doMawAnims(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, (EntityDarkYoung) entityIn);
            doTentacleAnims(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, (EntityDarkYoung) entityIn);
            doHurtAnims(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, (EntityDarkYoung) entityIn);
        }

    }

    private void doWalkingAnims(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, EntityDarkYoung entityIn){
        this.rLegF.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 0.7 * limbSwingAmount;
        this.lLegF.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.7 * limbSwingAmount;
        this.rLegH.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.7 * limbSwingAmount;
        this.lLegH.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 0.7 * limbSwingAmount;
    }

    private void doMawAnims(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, EntityDarkYoung entityIn){
        float progress = swingProgress;
        this.setRotateAngle(lJaw, -0.5759586531581287F, -0.6981317007977318F, 0.3490658503988659F, progress);
        this.setRotateAngle(lJawB, 0.7853981633974483F, 0.0F, 0.0F, progress);
        this.setRotateAngle(lJawC, -0.7853981633974483F, 0.0F, 0.0F, progress);

        this.setRotateAngle(rJaw, -0.5759586531581287F, 0.6981317007977318F, -0.3490658503988659F, progress);
        this.setRotateAngle(rJawB, 0.7853981633974483F, 0.0F, 0.0F, progress);
        this.setRotateAngle(rJawC, -0.7853981633974483F, 0.0F, 0.0F, progress);
    }


    private void doTentacleAnims(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, EntityDarkYoung entityIn){
        float progress = calculateTentacleProgress(40, ageInTicks);
        int cycle = (int) ((ageInTicks % 200) / 40F);

        int prevCycle = cycle <= 0 ? 4 : cycle - 1;

        addRotateAngles(tentacle01[1], tentacle01Anims[prevCycle][0], tentacle01Anims[prevCycle][1], tentacle01Anims[prevCycle][2], 1 - progress);
        addRotateAngles(tentacle02[1], tentacle02Anims[prevCycle][0], tentacle02Anims[prevCycle][1], tentacle02Anims[prevCycle][2], 1 - progress);
        addRotateAngles(tentacle03[1], tentacle03Anims[prevCycle][0], tentacle03Anims[prevCycle][1], tentacle03Anims[prevCycle][2], 1 - progress);
        addRotateAngles(tentacle04[1], tentacle04Anims[prevCycle][0], tentacle04Anims[prevCycle][1], tentacle04Anims[prevCycle][2], 1 - progress);
        addRotateAngles(tentacle05[1], tentacle05Anims[prevCycle][0], tentacle05Anims[prevCycle][1], tentacle05Anims[prevCycle][2], 1 - progress);
        addRotateAngles(tentacle06[1], tentacle06Anims[prevCycle][0], tentacle06Anims[prevCycle][1], tentacle06Anims[prevCycle][2], 1 - progress);
        addRotateAngles(tentacle07[1], tentacle07Anims[prevCycle][0], tentacle07Anims[prevCycle][1], tentacle07Anims[prevCycle][2], 1 - progress);
        addRotateAngles(tentacle08[1], tentacle08Anims[prevCycle][0], tentacle08Anims[prevCycle][1], tentacle08Anims[prevCycle][2], 1 - progress);
        addRotateAngles(tentacle09[1], tentacle09Anims[prevCycle][0], tentacle09Anims[prevCycle][1], tentacle09Anims[prevCycle][2], 1 - progress);
        addRotateAngles(tentacle10[1], tentacle10Anims[prevCycle][0], tentacle10Anims[prevCycle][1], tentacle10Anims[prevCycle][2], 1 - progress);

        addRotateAngles(tentacle01[1], tentacle01Anims[cycle][0], tentacle01Anims[cycle][1], tentacle01Anims[cycle][2], progress);
        addRotateAngles(tentacle02[1], tentacle02Anims[cycle][0], tentacle02Anims[cycle][1], tentacle02Anims[cycle][2], progress);
        addRotateAngles(tentacle03[1], tentacle03Anims[cycle][0], tentacle03Anims[cycle][1], tentacle03Anims[cycle][2], progress);
        addRotateAngles(tentacle04[1], tentacle04Anims[cycle][0], tentacle04Anims[cycle][1], tentacle04Anims[cycle][2], progress);
        addRotateAngles(tentacle05[1], tentacle05Anims[cycle][0], tentacle05Anims[cycle][1], tentacle05Anims[cycle][2], progress);
        addRotateAngles(tentacle06[1], tentacle06Anims[cycle][0], tentacle06Anims[cycle][1], tentacle06Anims[cycle][2], progress);
        addRotateAngles(tentacle07[1], tentacle07Anims[cycle][0], tentacle07Anims[cycle][1], tentacle07Anims[cycle][2], progress);
        addRotateAngles(tentacle08[1], tentacle08Anims[cycle][0], tentacle08Anims[cycle][1], tentacle08Anims[cycle][2], progress);
        addRotateAngles(tentacle09[1], tentacle09Anims[cycle][0], tentacle09Anims[cycle][1], tentacle09Anims[cycle][2], progress);
        addRotateAngles(tentacle10[1], tentacle10Anims[cycle][0], tentacle10Anims[cycle][1], tentacle10Anims[cycle][2], progress);

        ModelAnimUtil.waveTentacle(tentacle01, 0.8F, calculateTentacleProgress(100, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle02, 0.8F, calculateTentacleProgress(100, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle03, 0.8F, calculateTentacleProgress(100, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle04, 0.8F, calculateTentacleProgress(100, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle05, 0.8F, calculateTentacleProgress(100, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle06, 0.8F, calculateTentacleProgress(100, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle07, 0.8F, calculateTentacleProgress(100, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle08, 0.8F, calculateTentacleProgress(100, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle09, 0.8F, calculateTentacleProgress(100, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle10, 0.8F, calculateTentacleProgress(100, ageInTicks), true);
    }

    private void doHurtAnims(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, EntityDarkYoung entityIn){
        float hurtProgress = 0;
        if (entityIn.maxHurtTime > 0 && entityIn.hurtTime > 0){
            hurtProgress = (entityIn.maxHurtTime - entityIn.hurtTime + Minecraft.getMinecraft().getRenderPartialTicks()) / (float)entityIn.maxHurtTime;
        }
        ModelAnimUtil.waveTentacle(tentacle01, 1.5F, hurtProgress, true);
        ModelAnimUtil.waveTentacle(tentacle02, 1.5F, hurtProgress, true);
        ModelAnimUtil.waveTentacle(tentacle03, 1.5F, hurtProgress, true);
        ModelAnimUtil.waveTentacle(tentacle04, 1.5F, hurtProgress, true);
        ModelAnimUtil.waveTentacle(tentacle05, 1.5F, hurtProgress, true);
        ModelAnimUtil.waveTentacle(tentacle06, 1.5F, hurtProgress, true);
        ModelAnimUtil.waveTentacle(tentacle07, 1.5F, hurtProgress, true);
        ModelAnimUtil.waveTentacle(tentacle08, 1.5F, hurtProgress, true);
        ModelAnimUtil.waveTentacle(tentacle09, 1.5F, hurtProgress, true);
        ModelAnimUtil.waveTentacle(tentacle10, 1.5F, hurtProgress, true);
    }

    private float calculateTentacleProgress(int tickSequence, float ageInTicks){
        return (ageInTicks % tickSequence) / (float) tickSequence;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.chest.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotateAngle(ModelRenderer model, float x, float y, float z, float progression) {
        model.rotateAngleX = model.rotateAngleX * (1 - progression) + x * progression;
        model.rotateAngleY = model.rotateAngleY * (1 - progression) + y * progression;
        model.rotateAngleZ = model.rotateAngleZ * (1 - progression) + z * progression;
    }

    public void addRotateAngles(ModelRenderer model, float x, float y, float z, float progression) {
        model.rotateAngleX += x * progression;
        model.rotateAngleY += y * progression;
        model.rotateAngleZ += z * progression;
    }
}
