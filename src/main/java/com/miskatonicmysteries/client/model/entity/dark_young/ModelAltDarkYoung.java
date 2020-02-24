package com.miskatonicmysteries.client.model.entity.dark_young;

import com.miskatonicmysteries.common.entity.EntityDarkYoung;
import com.miskatonicmysteries.util.ModelAnimUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * dark_young_2 - cybercat5555
 * Created using Tabula 7.0.1
 */
public class ModelAltDarkYoung extends ModelBase {
    public ModelRenderer body;

    public ModelRenderer legBase01;
    public ModelRenderer legBase02;

    public ModelRenderer lfLeg01;
    public ModelRenderer lfLeg02;
    public ModelRenderer lfLeg03;
    public ModelRenderer lfHoof;
    public ModelRenderer lfHoofClaw01a;
    public ModelRenderer lfHoofClaw01b;
    public ModelRenderer lfHoofClaw02a;
    public ModelRenderer lfHoofClaw02b;

    public ModelRenderer lbLeg01;
    public ModelRenderer lbLeg02;
    public ModelRenderer lbLeg03;
    public ModelRenderer lbHoof;
    public ModelRenderer lbHoofClaw01a;
    public ModelRenderer lbHoofClaw02a;
    public ModelRenderer lbHoofClaw01b;
    public ModelRenderer lbHoofClaw02b;

    public ModelRenderer rbLeg01;
    public ModelRenderer rbLeg02;
    public ModelRenderer rbLeg03;
    public ModelRenderer rbHoof;
    public ModelRenderer rbHoofClaw01a;
    public ModelRenderer rbHoofClaw01b;
    public ModelRenderer rbHoofClaw02a;
    public ModelRenderer rbHoofClaw02b;

    public ModelRenderer rfLeg01;
    public ModelRenderer rfLeg02;
    public ModelRenderer rfLeg03;
    public ModelRenderer rfHoof;
    public ModelRenderer rfHoofClaw01a;
    public ModelRenderer rfHoofClaw02a;
    public ModelRenderer rfHoofClaw01b;
    public ModelRenderer rfHoofClaw02b;

    public ModelRenderer lHorn01;
    public ModelRenderer lHorn02;
    public ModelRenderer lHorn03;

    public ModelRenderer rHorn01;
    public ModelRenderer rHorn02;
    public ModelRenderer rHorn03;

    public ModelRenderer mouth01Top;
    public ModelRenderer mouth01Lower;

    public ModelRenderer mouth02Top;
    public ModelRenderer mouth02Lower;

    public ModelRenderer mouth03Top;
    public ModelRenderer mouth03Lower;

    public ModelRenderer fur;

    public ModelRenderer tentacle01[] = new ModelRenderer[6];
    public ModelRenderer tentacle02[] = new ModelRenderer[6];
    public ModelRenderer tentacle03[] = new ModelRenderer[4];
    public ModelRenderer tentacle04[] = new ModelRenderer[4];
    public ModelRenderer tentacle05[] = new ModelRenderer[6];
    public ModelRenderer tentacle06[] = new ModelRenderer[4];
    public ModelRenderer tentacle07[] = new ModelRenderer[6];
    public ModelRenderer tentacle08[] = new ModelRenderer[4];
    public ModelRenderer tentacle09[] = new ModelRenderer[5];
    public ModelRenderer tentacle10[] = new ModelRenderer[5];
    public ModelRenderer tentacle11[] = new ModelRenderer[5];
    public ModelRenderer tentacle12[] = new ModelRenderer[5];
    public ModelRenderer tentacle13[] = new ModelRenderer[5];
    public ModelRenderer tentacle14[] = new ModelRenderer[4];
    public ModelRenderer tentacle15[] = new ModelRenderer[4];

    public ModelRenderer[][] tentacles = {tentacle01, tentacle02, tentacle03, tentacle04, tentacle05,tentacle06, tentacle07, tentacle08, tentacle09, tentacle10, tentacle11, tentacle12, tentacle13, tentacle14, tentacle15};

    public ModelAltDarkYoung() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.tentacle09[0] = new ModelRenderer(this, 59, 38);
        this.tentacle09[0].setRotationPoint(-5.4F, -9.4F, -5.2F);
        this.tentacle09[0].addBox(-2.0F, -8.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(tentacle09[0], 0.0F, 0.0F, -0.22689280275926282F);
        this.lbHoofClaw01b = new ModelRenderer(this, 29, 42);
        this.lbHoofClaw01b.setRotationPoint(0.7F, 0.0F, 0.0F);
        this.lbHoofClaw01b.addBox(-0.5F, -1.1F, -0.6F, 1, 4, 1, 0.0F);
        this.setRotateAngle(lbHoofClaw01b, 0.0F, 0.7853981633974483F, 0.3665191429188092F);
        this.tentacle14[2] = new ModelRenderer(this, 89, 40);
        this.tentacle14[2].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle14[2].addBox(-1.0F, -4.5F, -1.0F, 2, 5, 2, 0.0F);
        this.lfLeg01 = new ModelRenderer(this, 0, 31);
        this.lfLeg01.setRotationPoint(4.9F, 0.9F, 0.0F);
        this.lfLeg01.addBox(0.4F, -2.0F, -2.5F, 6, 12, 5, 0.0F);
        this.setRotateAngle(lfLeg01, 0.0F, 0.0F, -0.5235987755982988F);
        this.lbHoofClaw01a = new ModelRenderer(this, 24, 42);
        this.lbHoofClaw01a.setRotationPoint(-1.0F, 3.2F, -0.8F);
        this.lbHoofClaw01a.addBox(-0.5F, -0.5F, -0.6F, 1, 2, 1, 0.0F);
        this.tentacle13[4] = new ModelRenderer(this, 99, 41);
        this.tentacle13[4].setRotationPoint(0.0F, -7.9F, 0.0F);
        this.tentacle13[4].addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(tentacle13[4], 0.12217304763960307F, 0.0F, 0.22689280275926282F);
        this.tentacle01[0] = new ModelRenderer(this, 36, 50);
        this.tentacle01[0].setRotationPoint(0.0F, -4.6F, -6.9F);
        this.tentacle01[0].addBox(-3.0F, -6.0F, -3.0F, 6, 8, 6, 0.0F);
        this.setRotateAngle(tentacle01[0], 0.5235987755982988F, 0.0F, 0.0F);
        this.tentacle05[3] = new ModelRenderer(this, 76, 37);
        this.tentacle05[3].setRotationPoint(0.0F, -7.5F, 0.0F);
        this.tentacle05[3].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle05[3], -0.13962634015954636F, 0.0F, -0.08726646259971647F);
        this.tentacle15[0] = new ModelRenderer(this, 76, 37);
        this.tentacle15[0].setRotationPoint(5.8F, -6.3F, 5.6F);
        this.tentacle15[0].addBox(-1.5F, -6.0F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(tentacle15[0], -0.3490658503988659F, 0.0F, 0.3141592653589793F);
        this.rbLeg03 = new ModelRenderer(this, 0, 58);
        this.rbLeg03.mirror = true;
        this.rbLeg03.setRotationPoint(7.8F, 3.0F, 0.0F);
        this.rbLeg03.addBox(0.0F, -1.5F, -1.5F, 12, 3, 3, 0.0F);
        this.setRotateAngle(rbLeg03, 0.0F, 0.0F, 0.5759586531581287F);
        this.tentacle03[0] = new ModelRenderer(this, 36, 50);
        this.tentacle03[0].setRotationPoint(0.0F, -5.2F, 6.0F);
        this.tentacle03[0].addBox(-3.0F, -6.0F, -3.0F, 6, 8, 6, 0.0F);
        this.setRotateAngle(tentacle03[0], -0.3490658503988659F, 0.0F, 0.0F);
        this.tentacle03[2] = new ModelRenderer(this, 76, 37);
        this.tentacle03[2].mirror = true;
        this.tentacle03[2].setRotationPoint(0.0F, -8.0F, 0.0F);
        this.tentacle03[2].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle03[2], 0.03490658503988659F, 0.0F, -0.17453292519943295F);
        this.tentacle11[2] = new ModelRenderer(this, 76, 37);
        this.tentacle11[2].setRotationPoint(0.0F, -6.0F, 0.0F);
        this.tentacle11[2].addBox(-1.5F, -7.0F, -1.5F, 3, 7, 3, 0.0F);
        this.setRotateAngle(tentacle11[2], -0.13962634015954636F, 0.0F, -0.17453292519943295F);
        this.tentacle02[0] = new ModelRenderer(this, 76, 37);
        this.tentacle02[0].setRotationPoint(4.6F, -8.9F, -2.9F);
        this.tentacle02[0].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle02[0], 0.17453292519943295F, 0.0F, 0.08726646259971647F);
        this.rbHoofClaw02b = new ModelRenderer(this, 29, 48);
        this.rbHoofClaw02b.mirror = true;
        this.rbHoofClaw02b.setRotationPoint(-0.6F, 0.0F, 0.0F);
        this.rbHoofClaw02b.addBox(-0.5F, -1.1F, -0.6F, 1, 4, 1, 0.0F);
        this.setRotateAngle(rbHoofClaw02b, 0.0F, 0.7853981633974483F, -0.3665191429188092F);
        this.tentacle02[4] = new ModelRenderer(this, 89, 40);
        this.tentacle02[4].mirror = true;
        this.tentacle02[4].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle02[4].addBox(-1.0F, -5.7F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(tentacle02[4], -0.12217304763960307F, 0.0F, -0.22689280275926282F);
        this.lfHoofClaw02b = new ModelRenderer(this, 29, 48);
        this.lfHoofClaw02b.setRotationPoint(0.7F, 0.0F, 0.0F);
        this.lfHoofClaw02b.addBox(-0.5F, -1.1F, -0.6F, 1, 4, 1, 0.0F);
        this.setRotateAngle(lfHoofClaw02b, 0.0F, -0.7853981633974483F, 0.3665191429188092F);
        this.tentacle11[3] = new ModelRenderer(this, 89, 40);
        this.tentacle11[3].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle11[3].addBox(-1.0F, -8.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(tentacle11[3], 0.20943951023931953F, 0.0F, 0.12217304763960307F);
        this.tentacle11[4] = new ModelRenderer(this, 99, 41);
        this.tentacle11[4].setRotationPoint(0.0F, -8.0F, 0.0F);
        this.tentacle11[4].addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(tentacle11[4], 0.12217304763960307F, 0.0F, 0.22689280275926282F);
        this.mouth01Lower = new ModelRenderer(this, 58, 11);
        this.mouth01Lower.setRotationPoint(0.0F, -1.2F, -5.9F);
        this.mouth01Lower.addBox(-2.0F, -0.5F, -4.9F, 4, 2, 4, 0.0F);
        this.lfHoofClaw02a = new ModelRenderer(this, 24, 42);
        this.lfHoofClaw02a.setRotationPoint(-1.0F, 3.2F, 1.0F);
        this.lfHoofClaw02a.addBox(-0.5F, -0.5F, -0.6F, 1, 2, 1, 0.0F);
        this.tentacle02[3] = new ModelRenderer(this, 99, 41);
        this.tentacle02[3].setRotationPoint(0.0F, -4.0F, 0.0F);
        this.tentacle02[3].addBox(-0.5F, -3.9F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(tentacle02[3], 0.0F, 0.0F, -0.2617993877991494F);
        this.tentacle05[2] = new ModelRenderer(this, 76, 37);
        this.tentacle05[2].setRotationPoint(0.0F, -8.0F, 0.0F);
        this.tentacle05[2].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle05[2], 0.20943951023931953F, 0.0F, -0.13962634015954636F);
        this.tentacle12[3] = new ModelRenderer(this, 89, 40);
        this.tentacle12[3].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle12[3].addBox(-1.0F, -5.7F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(tentacle12[3], 0.20943951023931953F, 0.0F, -0.13962634015954636F);
        this.legBase02 = new ModelRenderer(this, 0, 0);
        this.legBase02.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.legBase02.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(legBase02, 0.0F, -0.7853981633974483F, 0.0F);
        this.tentacle01[2] = new ModelRenderer(this, 76, 37);
        this.tentacle01[2].setRotationPoint(0.0F, -8.0F, 0.0F);
        this.tentacle01[2].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle01[2], 0.20943951023931953F, 0.0F, -0.08726646259971647F);
        this.rbHoof = new ModelRenderer(this, 23, 33);
        this.rbHoof.mirror = true;
        this.rbHoof.setRotationPoint(11.9F, -0.3F, 0.0F);
        this.rbHoof.addBox(-0.5F, -1.0F, -1.51F, 2, 4, 3, 0.0F);
        this.setRotateAngle(rbHoof, 0.0F, 0.0F, -0.05235987755982988F);
        this.rbLeg02 = new ModelRenderer(this, 0, 48);
        this.rbLeg02.setRotationPoint(-3.5F, 4.8F, 0.0F);
        this.rbLeg02.addBox(0.0F, 0.8F, -2.0F, 9, 5, 4, 0.0F);
        this.setRotateAngle(rbLeg02, 0.0F, 0.0F, 0.5235987755982988F);
        this.tentacle07[5] = new ModelRenderer(this, 99, 41);
        this.tentacle07[5].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle07[5].addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(tentacle07[5], 0.0F, 0.0F, -0.22689280275926282F);
        this.rbLeg01 = new ModelRenderer(this, 0, 31);
        this.rbLeg01.mirror = true;
        this.rbLeg01.setRotationPoint(-4.7F, 0.9F, 0.0F);
        this.rbLeg01.addBox(-6.8F, -2.0F, -2.5F, 6, 12, 5, 0.0F);
        this.setRotateAngle(rbLeg01, 0.0F, 0.0F, 0.5235987755982988F);
        this.lbLeg02 = new ModelRenderer(this, 0, 48);
        this.lbLeg02.mirror = true;
        this.lbLeg02.setRotationPoint(3.5F, 4.8F, 0.0F);
        this.lbLeg02.addBox(-9.0F, 0.8F, -2.0F, 9, 5, 4, 0.0F);
        this.setRotateAngle(lbLeg02, 0.0F, 0.0F, -0.5235987755982988F);
        this.mouth02Top = new ModelRenderer(this, 79, 0);
        this.mouth02Top.setRotationPoint(3.9F, 2.0F, -5.3F);
        this.mouth02Top.addBox(-2.5F, -0.9F, -5.0F, 5, 3, 5, 0.0F);
        this.setRotateAngle(mouth02Top, 0.0F, 0.0F, 0.7853981633974483F);
        this.tentacle12[1] = new ModelRenderer(this, 76, 37);
        this.tentacle12[1].setRotationPoint(0.0F, -8.0F, 0.0F);
        this.tentacle12[1].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle12[1], 0.0F, 0.0F, 0.22689280275926282F);
        this.tentacle06[1] = new ModelRenderer(this, 76, 37);
        this.tentacle06[1].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle06[1].addBox(-1.5F, -5.3F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(tentacle06[1], 0.3141592653589793F, 0.0F, -0.3490658503988659F);
        this.rHorn02 = new ModelRenderer(this, 72, 18);
        this.rHorn02.mirror = true;
        this.rHorn02.setRotationPoint(0.0F, -2.7F, 0.0F);
        this.rHorn02.addBox(-1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(rHorn02, -0.13962634015954636F, 0.0F, 0.3141592653589793F);
        this.tentacle13[1] = new ModelRenderer(this, 76, 37);
        this.tentacle13[1].setRotationPoint(0.0F, -8.0F, 0.0F);
        this.tentacle13[1].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle13[1], 0.03490658503988659F, 0.0F, 0.17453292519943295F);
        this.tentacle09[2] = new ModelRenderer(this, 76, 37);
        this.tentacle09[2].setRotationPoint(0.0F, -7.5F, 0.0F);
        this.tentacle09[2].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle09[2], -0.13962634015954636F, 0.0F, -0.17453292519943295F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, -0.9F, 0.0F);
        this.body.addBox(-7.0F, -11.8F, -7.0F, 14, 17, 14, 0.0F);
        this.lfLeg02 = new ModelRenderer(this, 0, 48);
        this.lfLeg02.mirror = true;
        this.lfLeg02.setRotationPoint(3.5F, 4.8F, 0.0F);
        this.lfLeg02.addBox(-9.0F, 0.8F, -2.0F, 9, 5, 4, 0.0F);
        this.setRotateAngle(lfLeg02, 0.0F, 0.0F, -0.5235987755982988F);
        this.tentacle03[1] = new ModelRenderer(this, 59, 38);
        this.tentacle03[1].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle03[1].addBox(-2.0F, -8.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(tentacle03[1], 0.08726646259971647F, 0.0F, 0.13962634015954636F);
        this.mouth03Lower = new ModelRenderer(this, 100, 0);
        this.mouth03Lower.mirror = true;
        this.mouth03Lower.setRotationPoint(-3.6F, 2.3F, -5.4F);
        this.mouth03Lower.addBox(-2.0F, -0.9F, -5.0F, 4, 2, 5, 0.0F);
        this.setRotateAngle(mouth03Lower, 0.0F, 0.0F, -0.7853981633974483F);
        this.tentacle15[3] = new ModelRenderer(this, 99, 41);
        this.tentacle15[3].setRotationPoint(0.0F, -4.0F, 0.0F);
        this.tentacle15[3].addBox(-0.5F, -3.9F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(tentacle15[3], 0.0F, 0.0F, 0.17453292519943295F);
        this.legBase01 = new ModelRenderer(this, 0, 0);
        this.legBase01.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.legBase01.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(legBase01, 0.0F, 0.7853981633974483F, 0.0F);
        this.tentacle11[0] = new ModelRenderer(this, 59, 38);
        this.tentacle11[0].setRotationPoint(-1.1F, -11.0F, -3.7F);
        this.tentacle11[0].addBox(-2.0F, -8.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(tentacle11[0], 0.0F, 0.0F, -0.08726646259971647F);
        this.tentacle05[4] = new ModelRenderer(this, 89, 40);
        this.tentacle05[4].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle05[4].addBox(-1.0F, -5.7F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(tentacle05[4], 0.20943951023931953F, 0.0F, 0.22689280275926282F);
        this.tentacle14[3] = new ModelRenderer(this, 99, 41);
        this.tentacle14[3].setRotationPoint(0.0F, -4.0F, 0.0F);
        this.tentacle14[3].addBox(-0.5F, -3.9F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(tentacle14[3], 0.0F, 0.0F, 0.17453292519943295F);
        this.lbLeg03 = new ModelRenderer(this, 0, 58);
        this.lbLeg03.setRotationPoint(-7.8F, 3.0F, 0.0F);
        this.lbLeg03.addBox(-12.0F, -1.5F, -1.5F, 12, 3, 3, 0.0F);
        this.setRotateAngle(lbLeg03, 0.0F, 0.0F, -0.5759586531581287F);
        this.tentacle08[3] = new ModelRenderer(this, 99, 41);
        this.tentacle08[3].setRotationPoint(0.0F, -4.0F, 0.0F);
        this.tentacle08[3].addBox(-0.5F, -3.9F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(tentacle08[3], 0.0F, 0.0F, 0.17453292519943295F);
        this.tentacle02[5] = new ModelRenderer(this, 99, 41);
        this.tentacle02[5].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle02[5].addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(tentacle02[5], 0.0F, 0.0F, -0.22689280275926282F);
        this.tentacle09[4] = new ModelRenderer(this, 99, 41);
        this.tentacle09[4].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle09[4].addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(tentacle09[4], 0.12217304763960307F, 0.0F, -0.2792526803190927F);
        this.tentacle03[3] = new ModelRenderer(this, 76, 37);
        this.tentacle03[3].mirror = true;
        this.tentacle03[3].setRotationPoint(0.0F, -7.5F, 0.0F);
        this.tentacle03[3].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle03[3], 0.17453292519943295F, 0.0F, 0.22689280275926282F);
        this.tentacle15[2] = new ModelRenderer(this, 89, 40);
        this.tentacle15[2].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle15[2].addBox(-1.0F, -4.5F, -1.0F, 2, 5, 2, 0.0F);
        this.tentacle04[0] = new ModelRenderer(this, 76, 37);
        this.tentacle04[0].setRotationPoint(-3.5F, 0.0F, -1.4F);
        this.tentacle04[0].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle04[0], 0.0F, 0.0F, -0.2792526803190927F);
        this.tentacle04[1] = new ModelRenderer(this, 76, 37);
        this.tentacle04[1].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle04[1].addBox(-1.5F, -5.3F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(tentacle04[1], 0.22689280275926282F, 0.0F, 0.3141592653589793F);
        this.tentacle07[3] = new ModelRenderer(this, 76, 37);
        this.tentacle07[3].mirror = true;
        this.tentacle07[3].setRotationPoint(0.0F, -7.5F, 0.0F);
        this.tentacle07[3].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle07[3], 0.0F, 0.0F, 0.22689280275926282F);
        this.tentacle09[3] = new ModelRenderer(this, 89, 40);
        this.tentacle09[3].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle09[3].addBox(-1.0F, -5.7F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(tentacle09[3], 0.20943951023931953F, 0.0F, -0.17453292519943295F);
        this.tentacle10[1] = new ModelRenderer(this, 76, 37);
        this.tentacle10[1].setRotationPoint(0.0F, -8.0F, 0.0F);
        this.tentacle10[1].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle10[1], 0.20943951023931953F, 0.0F, -0.22689280275926282F);
        this.rfHoof = new ModelRenderer(this, 23, 33);
        this.rfHoof.mirror = true;
        this.rfHoof.setRotationPoint(11.9F, -0.3F, 0.0F);
        this.rfHoof.addBox(-0.5F, -1.0F, -1.51F, 2, 4, 3, 0.0F);
        this.setRotateAngle(rfHoof, 0.0F, 0.0F, -0.05235987755982988F);
        this.tentacle10[3] = new ModelRenderer(this, 89, 40);
        this.tentacle10[3].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle10[3].addBox(-1.0F, -5.7F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(tentacle10[3], -0.2792526803190927F, 0.0F, -0.12217304763960307F);
        this.tentacle06[2] = new ModelRenderer(this, 89, 40);
        this.tentacle06[2].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle06[2].addBox(-1.0F, -4.5F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(tentacle06[2], 0.0F, 0.0F, 0.22689280275926282F);
        this.lbLeg01 = new ModelRenderer(this, 0, 31);
        this.lbLeg01.setRotationPoint(4.9F, 0.9F, 0.0F);
        this.lbLeg01.addBox(0.4F, -2.0F, -2.5F, 6, 12, 5, 0.0F);
        this.setRotateAngle(lbLeg01, 0.0F, 0.0F, -0.5235987755982988F);
        this.tentacle01[1] = new ModelRenderer(this, 59, 38);
        this.tentacle01[1].setRotationPoint(0.0F, -5.0F, -0.8F);
        this.tentacle01[1].addBox(-2.0F, -8.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(tentacle01[1], -0.5235987755982988F, 0.0F, -0.03490658503988659F);
        this.rfLeg02 = new ModelRenderer(this, 0, 48);
        this.rfLeg02.setRotationPoint(-3.5F, 4.8F, 0.0F);
        this.rfLeg02.addBox(0.0F, 0.8F, -2.0F, 9, 5, 4, 0.0F);
        this.setRotateAngle(rfLeg02, 0.0F, 0.0F, 0.5235987755982988F);
        this.tentacle11[1] = new ModelRenderer(this, 76, 37);
        this.tentacle11[1].setRotationPoint(0.0F, -8.0F, 0.0F);
        this.tentacle11[1].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle11[1], 0.0F, 0.0F, 0.22689280275926282F);
        this.tentacle05[5] = new ModelRenderer(this, 99, 41);
        this.tentacle05[5].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle05[5].addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(tentacle05[5], 0.12217304763960307F, 0.0F, 0.22689280275926282F);
        this.rfLeg01 = new ModelRenderer(this, 0, 31);
        this.rfLeg01.mirror = true;
        this.rfLeg01.setRotationPoint(-4.7F, 0.9F, 0.0F);
        this.rfLeg01.addBox(-6.8F, -2.0F, -2.5F, 6, 12, 5, 0.0F);
        this.setRotateAngle(rfLeg01, 0.0F, 0.0F, 0.5235987755982988F);
        this.tentacle01[4] = new ModelRenderer(this, 89, 40);
        this.tentacle01[4].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle01[4].addBox(-1.0F, -5.7F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(tentacle01[4], 0.20943951023931953F, 0.0F, 0.0F);
        this.lfHoofClaw01b = new ModelRenderer(this, 29, 42);
        this.lfHoofClaw01b.setRotationPoint(0.7F, 0.0F, 0.0F);
        this.lfHoofClaw01b.addBox(-0.5F, -1.1F, -0.6F, 1, 4, 1, 0.0F);
        this.setRotateAngle(lfHoofClaw01b, 0.0F, 0.7853981633974483F, 0.3665191429188092F);
        this.rHorn03 = new ModelRenderer(this, 81, 21);
        this.rHorn03.mirror = true;
        this.rHorn03.setRotationPoint(0.0F, -4.7F, 0.0F);
        this.rHorn03.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(rHorn03, -0.13962634015954636F, 0.0F, -0.3141592653589793F);
        this.tentacle04[2] = new ModelRenderer(this, 89, 40);
        this.tentacle04[2].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle04[2].addBox(-1.0F, -4.5F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(tentacle04[2], 0.0F, 0.0F, -0.4363323129985824F);
        this.tentacle07[1] = new ModelRenderer(this, 59, 38);
        this.tentacle07[1].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle07[1].addBox(-2.0F, -8.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(tentacle07[1], -0.17453292519943295F, 0.0F, 0.20943951023931953F);
        this.tentacle08[2] = new ModelRenderer(this, 89, 40);
        this.tentacle08[2].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle08[2].addBox(-1.0F, -4.5F, -1.0F, 2, 5, 2, 0.0F);
        this.lbHoof = new ModelRenderer(this, 23, 33);
        this.lbHoof.setRotationPoint(-11.9F, -0.3F, 0.0F);
        this.lbHoof.addBox(-1.5F, -1.0F, -1.51F, 2, 4, 3, 0.0F);
        this.setRotateAngle(lbHoof, 0.0F, 0.0F, 0.05235987755982988F);
        this.tentacle02[2] = new ModelRenderer(this, 89, 40);
        this.tentacle02[2].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle02[2].addBox(-1.0F, -4.5F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(tentacle02[2], 0.0F, 0.0F, 0.22689280275926282F);
        this.tentacle12[2] = new ModelRenderer(this, 76, 37);
        this.tentacle12[2].setRotationPoint(0.0F, -6.0F, 0.0F);
        this.tentacle12[2].addBox(-1.5F, -7.0F, -1.5F, 3, 7, 3, 0.0F);
        this.setRotateAngle(tentacle12[2], -0.13962634015954636F, 0.0F, -0.17453292519943295F);
        this.rbHoofClaw01a = new ModelRenderer(this, 24, 42);
        this.rbHoofClaw01a.mirror = true;
        this.rbHoofClaw01a.setRotationPoint(1.0F, 3.2F, -0.8F);
        this.rbHoofClaw01a.addBox(-0.5F, -0.5F, -0.6F, 1, 2, 1, 0.0F);
        this.tentacle01[5] = new ModelRenderer(this, 99, 41);
        this.tentacle01[5].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle01[5].addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(tentacle01[5], 0.12217304763960307F, 0.0F, -0.15707963267948966F);
        this.tentacle02[1] = new ModelRenderer(this, 76, 37);
        this.tentacle02[1].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle02[1].addBox(-1.5F, -5.3F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(tentacle02[1], -0.13962634015954636F, 0.0F, 0.22689280275926282F);
        this.lbHoofClaw02a = new ModelRenderer(this, 24, 42);
        this.lbHoofClaw02a.setRotationPoint(-1.0F, 3.2F, 1.0F);
        this.lbHoofClaw02a.addBox(-0.5F, -0.5F, -0.6F, 1, 2, 1, 0.0F);
        this.lHorn02 = new ModelRenderer(this, 72, 18);
        this.lHorn02.setRotationPoint(0.0F, -2.7F, 0.0F);
        this.lHorn02.addBox(-1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(lHorn02, -0.13962634015954636F, 0.0F, -0.3141592653589793F);
        this.tentacle13[3] = new ModelRenderer(this, 89, 40);
        this.tentacle13[3].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle13[3].addBox(-1.0F, -8.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(tentacle13[3], 0.20943951023931953F, 0.0F, 0.12217304763960307F);
        this.tentacle04[3] = new ModelRenderer(this, 99, 41);
        this.tentacle04[3].setRotationPoint(0.0F, -4.0F, 0.0F);
        this.tentacle04[3].addBox(-0.5F, -3.9F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(tentacle04[3], 0.0F, 0.0F, 0.17453292519943295F);
        this.tentacle10[2] = new ModelRenderer(this, 76, 37);
        this.tentacle10[2].setRotationPoint(0.0F, -6.0F, 0.0F);
        this.tentacle10[2].addBox(-1.5F, -7.0F, -1.5F, 3, 7, 3, 0.0F);
        this.setRotateAngle(tentacle10[2], -0.13962634015954636F, 0.0F, 0.22689280275926282F);
        this.tentacle12[4] = new ModelRenderer(this, 99, 41);
        this.tentacle12[4].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle12[4].addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(tentacle12[4], 0.12217304763960307F, 0.0F, 0.0F);
        this.lHorn03 = new ModelRenderer(this, 81, 21);
        this.lHorn03.setRotationPoint(0.0F, -4.7F, 0.0F);
        this.lHorn03.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(lHorn03, -0.13962634015954636F, 0.0F, 0.3141592653589793F);
        this.rfHoofClaw02a = new ModelRenderer(this, 24, 42);
        this.rfHoofClaw02a.mirror = true;
        this.rfHoofClaw02a.setRotationPoint(1.0F, 3.2F, 0.9F);
        this.rfHoofClaw02a.addBox(-0.5F, -0.5F, -0.6F, 1, 2, 1, 0.0F);
        this.mouth01Top = new ModelRenderer(this, 53, 0);
        this.mouth01Top.setRotationPoint(0.0F, -4.0F, -8.1F);
        this.mouth01Top.addBox(-2.5F, -0.5F, -2.8F, 5, 4, 6, 0.0F);
        this.tentacle07[4] = new ModelRenderer(this, 89, 40);
        this.tentacle07[4].mirror = true;
        this.tentacle07[4].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle07[4].addBox(-1.0F, -5.7F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(tentacle07[4], 0.0F, 0.0F, -0.22689280275926282F);
        this.rHorn01 = new ModelRenderer(this, 59, 22);
        this.rHorn01.mirror = true;
        this.rHorn01.setRotationPoint(-3.5F, -6.9F, -7.0F);
        this.rHorn01.addBox(-1.5F, -3.0F, -1.5F, 3, 5, 3, 0.0F);
        this.setRotateAngle(rHorn01, 0.3141592653589793F, 0.0F, -0.5759586531581287F);
        this.rfHoofClaw01b = new ModelRenderer(this, 29, 42);
        this.rfHoofClaw01b.mirror = true;
        this.rfHoofClaw01b.setRotationPoint(-0.7F, 0.0F, 0.0F);
        this.rfHoofClaw01b.addBox(-0.5F, -1.1F, -0.6F, 1, 4, 1, 0.0F);
        this.setRotateAngle(rfHoofClaw01b, 0.0F, -0.7853981633974483F, -0.3665191429188092F);
        this.lfHoof = new ModelRenderer(this, 23, 33);
        this.lfHoof.setRotationPoint(-11.9F, -0.3F, 0.0F);
        this.lfHoof.addBox(-1.5F, -1.0F, -1.51F, 2, 4, 3, 0.0F);
        this.setRotateAngle(lfHoof, 0.0F, 0.0F, 0.05235987755982988F);
        this.tentacle08[1] = new ModelRenderer(this, 76, 37);
        this.tentacle08[1].setRotationPoint(0.0F, -7.0F, 0.0F);
        this.tentacle08[1].addBox(-1.5F, -5.3F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(tentacle08[1], 0.22689280275926282F, 0.0F, 0.3490658503988659F);
        this.rbHoofClaw02a = new ModelRenderer(this, 24, 42);
        this.rbHoofClaw02a.mirror = true;
        this.rbHoofClaw02a.setRotationPoint(1.0F, 3.2F, 0.9F);
        this.rbHoofClaw02a.addBox(-0.5F, -0.5F, -0.6F, 1, 2, 1, 0.0F);
        this.tentacle05[1] = new ModelRenderer(this, 59, 38);
        this.tentacle05[1].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle05[1].addBox(-2.0F, -8.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(tentacle05[1], -0.08726646259971647F, 0.0F, -0.22689280275926282F);
        this.tentacle13[0] = new ModelRenderer(this, 59, 38);
        this.tentacle13[0].setRotationPoint(1.2F, -11.0F, -3.9F);
        this.tentacle13[0].addBox(-2.0F, -8.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(tentacle13[0], 0.2617993877991494F, 0.0F, 0.20943951023931953F);
        this.tentacle07[2] = new ModelRenderer(this, 76, 37);
        this.tentacle07[2].mirror = true;
        this.tentacle07[2].setRotationPoint(0.0F, -8.0F, 0.0F);
        this.tentacle07[2].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle07[2], 0.13962634015954636F, 0.0F, -0.17453292519943295F);
        this.rbHoofClaw01b = new ModelRenderer(this, 29, 42);
        this.rbHoofClaw01b.mirror = true;
        this.rbHoofClaw01b.setRotationPoint(-0.7F, 0.0F, 0.0F);
        this.rbHoofClaw01b.addBox(-0.5F, -1.1F, -0.6F, 1, 4, 1, 0.0F);
        this.setRotateAngle(rbHoofClaw01b, 0.0F, -0.7853981633974483F, -0.3665191429188092F);
        this.tentacle01[3] = new ModelRenderer(this, 76, 37);
        this.tentacle01[3].setRotationPoint(0.0F, -7.5F, 0.0F);
        this.tentacle01[3].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle01[3], -0.13962634015954636F, 0.0F, 0.22689280275926282F);
        this.tentacle14[1] = new ModelRenderer(this, 76, 37);
        this.tentacle14[1].setRotationPoint(0.0F, -6.2F, 0.0F);
        this.tentacle14[1].addBox(-1.5F, -5.3F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(tentacle14[1], 0.22689280275926282F, 0.0F, -0.17453292519943295F);
        this.tentacle14[0] = new ModelRenderer(this, 76, 37);
        this.tentacle14[0].setRotationPoint(5.8F, -3.6F, -4.3F);
        this.tentacle14[0].addBox(-1.5F, -6.0F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(tentacle14[0], 0.0F, 0.0F, 0.3141592653589793F);
        this.lbHoofClaw02b = new ModelRenderer(this, 29, 48);
        this.lbHoofClaw02b.setRotationPoint(0.7F, 0.0F, 0.0F);
        this.lbHoofClaw02b.addBox(-0.5F, -1.1F, -0.6F, 1, 4, 1, 0.0F);
        this.setRotateAngle(lbHoofClaw02b, 0.0F, -0.7853981633974483F, 0.3665191429188092F);
        this.tentacle12[0] = new ModelRenderer(this, 59, 38);
        this.tentacle12[0].setRotationPoint(-3.2F, -11.0F, 2.8F);
        this.tentacle12[0].addBox(-2.0F, -8.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(tentacle12[0], -0.17453292519943295F, 0.0F, -0.08726646259971647F);
        this.rfHoofClaw02b = new ModelRenderer(this, 29, 48);
        this.rfHoofClaw02b.mirror = true;
        this.rfHoofClaw02b.setRotationPoint(-0.6F, 0.0F, 0.0F);
        this.rfHoofClaw02b.addBox(-0.5F, -1.1F, -0.6F, 1, 4, 1, 0.0F);
        this.setRotateAngle(rfHoofClaw02b, 0.0F, 0.7853981633974483F, -0.3665191429188092F);
        this.tentacle09[1] = new ModelRenderer(this, 76, 37);
        this.tentacle09[1].setRotationPoint(0.0F, -8.0F, 0.0F);
        this.tentacle09[1].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle09[1], 0.0F, 0.0F, 0.2617993877991494F);
        this.tentacle07[0] = new ModelRenderer(this, 36, 36);
        this.tentacle07[0].setRotationPoint(-4.9F, -3.7F, 0.0F);
        this.tentacle07[0].addBox(-2.5F, -6.0F, -2.5F, 5, 6, 5, 0.0F);
        this.setRotateAngle(tentacle07[0], 0.0F, 0.0F, -0.4363323129985824F);
        this.mouth03Top = new ModelRenderer(this, 79, 0);
        this.mouth03Top.mirror = true;
        this.mouth03Top.setRotationPoint(-4.8F, 0.9F, -5.5F);
        this.mouth03Top.addBox(-2.5F, -0.9F, -5.0F, 5, 3, 5, 0.0F);
        this.setRotateAngle(mouth03Top, 0.0F, 0.0F, -0.7853981633974483F);
        this.tentacle13[2] = new ModelRenderer(this, 76, 37);
        this.tentacle13[2].setRotationPoint(0.0F, -6.0F, 0.0F);
        this.tentacle13[2].addBox(-1.5F, -7.0F, -1.5F, 3, 7, 3, 0.0F);
        this.setRotateAngle(tentacle13[2], -0.2617993877991494F, 0.0F, -0.22689280275926282F);
        this.tentacle15[1] = new ModelRenderer(this, 76, 37);
        this.tentacle15[1].setRotationPoint(0.0F, -6.2F, 0.0F);
        this.tentacle15[1].addBox(-1.5F, -5.3F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(tentacle15[1], 0.22689280275926282F, 0.0F, -0.17453292519943295F);
        this.tentacle10[0] = new ModelRenderer(this, 59, 38);
        this.tentacle10[0].setRotationPoint(1.2F, -11.0F, 3.4F);
        this.tentacle10[0].addBox(-2.0F, -8.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(tentacle10[0], 0.0F, 0.0F, 0.12217304763960307F);
        this.tentacle06[0] = new ModelRenderer(this, 76, 37);
        this.tentacle06[0].setRotationPoint(-0.8F, -3.0F, 1.5F);
        this.tentacle06[0].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle06[0], -0.4363323129985824F, 0.0F, -0.40142572795869574F);
        this.mouth02Lower = new ModelRenderer(this, 100, 0);
        this.mouth02Lower.setRotationPoint(2.5F, 3.3F, -5.2F);
        this.mouth02Lower.addBox(-2.0F, -0.9F, -5.0F, 4, 2, 5, 0.0F);
        this.setRotateAngle(mouth02Lower, 0.0F, 0.0F, 0.7853981633974483F);
        this.lfLeg03 = new ModelRenderer(this, 0, 58);
        this.lfLeg03.setRotationPoint(-7.8F, 3.0F, 0.0F);
        this.lfLeg03.addBox(-12.0F, -1.5F, -1.5F, 12, 3, 3, 0.0F);
        this.setRotateAngle(lfLeg03, 0.0F, 0.0F, -0.5759586531581287F);
        this.rfHoofClaw01a = new ModelRenderer(this, 24, 42);
        this.rfHoofClaw01a.mirror = true;
        this.rfHoofClaw01a.setRotationPoint(1.0F, 3.2F, -0.8F);
        this.rfHoofClaw01a.addBox(-0.5F, -0.5F, -0.6F, 1, 2, 1, 0.0F);
        this.tentacle05[0] = new ModelRenderer(this, 36, 36);
        this.tentacle05[0].setRotationPoint(4.5F, -5.4F, 0.0F);
        this.tentacle05[0].addBox(-2.5F, -6.0F, -2.5F, 5, 6, 5, 0.0F);
        this.setRotateAngle(tentacle05[0], 0.0F, 0.0F, 0.5235987755982988F);
        this.tentacle10[4] = new ModelRenderer(this, 99, 41);
        this.tentacle10[4].setRotationPoint(0.0F, -5.0F, 0.0F);
        this.tentacle10[4].addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(tentacle10[4], 0.12217304763960307F, 0.0F, 0.22689280275926282F);
        this.tentacle08[0] = new ModelRenderer(this, 76, 37);
        this.tentacle08[0].setRotationPoint(-1.9F, 0.0F, -1.8F);
        this.tentacle08[0].addBox(-1.5F, -7.0F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(tentacle08[0], 0.0F, 0.0F, -0.03490658503988659F);
        this.lHorn01 = new ModelRenderer(this, 59, 22);
        this.lHorn01.setRotationPoint(3.5F, -6.9F, -7.0F);
        this.lHorn01.addBox(-1.5F, -3.0F, -1.5F, 3, 5, 3, 0.0F);
        this.setRotateAngle(lHorn01, 0.3141592653589793F, 0.0F, 0.5759586531581287F);
        this.tentacle06[3] = new ModelRenderer(this, 99, 41);
        this.tentacle06[3].setRotationPoint(0.0F, -4.0F, 0.0F);
        this.tentacle06[3].addBox(-0.5F, -3.9F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(tentacle06[3], 0.0F, 0.0F, -0.2617993877991494F);
        this.rfLeg03 = new ModelRenderer(this, 0, 58);
        this.rfLeg03.mirror = true;
        this.rfLeg03.setRotationPoint(7.8F, 3.0F, 0.0F);
        this.rfLeg03.addBox(0.0F, -1.5F, -1.5F, 12, 3, 3, 0.0F);
        this.setRotateAngle(rfLeg03, 0.0F, 0.0F, 0.5759586531581287F);
        this.fur = new ModelRenderer(this, 76, 15);
        this.fur.setRotationPoint(0.0F, 5.2F, 0.0F);
        this.fur.addBox(-6.5F, 0.0F, -6.5F, 13, 6, 13, 0.0F);
        this.lfHoofClaw01a = new ModelRenderer(this, 24, 42);
        this.lfHoofClaw01a.setRotationPoint(-1.0F, 3.2F, -0.8F);
        this.lfHoofClaw01a.addBox(-0.5F, -0.5F, -0.6F, 1, 2, 1, 0.0F);
        this.body.addChild(this.tentacle09[0]);
        this.lbHoofClaw01a.addChild(this.lbHoofClaw01b);
        this.tentacle14[1].addChild(this.tentacle14[2]);
        this.legBase01.addChild(this.lfLeg01);
        this.lbHoof.addChild(this.lbHoofClaw01a);
        this.tentacle13[3].addChild(this.tentacle13[4]);
        this.body.addChild(this.tentacle01[0]);
        this.tentacle05[2].addChild(this.tentacle05[3]);
        this.body.addChild(this.tentacle15[0]);
        this.rbLeg02.addChild(this.rbLeg03);
        this.body.addChild(this.tentacle03[0]);
        this.tentacle03[1].addChild(this.tentacle03[2]);
        this.tentacle11[1].addChild(this.tentacle11[2]);
        this.body.addChild(this.tentacle02[0]);
        this.rbHoofClaw02a.addChild(this.rbHoofClaw02b);
        this.tentacle03[3].addChild(this.tentacle02[4]);
        this.lfHoofClaw02a.addChild(this.lfHoofClaw02b);
        this.tentacle11[2].addChild(this.tentacle11[3]);
        this.tentacle11[3].addChild(this.tentacle11[4]);
        this.body.addChild(this.mouth01Lower);
        this.lfHoof.addChild(this.lfHoofClaw02a);
        this.tentacle02[2].addChild(this.tentacle02[3]);
        this.tentacle05[1].addChild(this.tentacle05[2]);
        this.tentacle12[2].addChild(this.tentacle12[3]);
        this.body.addChild(this.legBase02);
        this.tentacle01[1].addChild(this.tentacle01[2]);
        this.rbLeg03.addChild(this.rbHoof);
        this.rbLeg01.addChild(this.rbLeg02);
        this.tentacle07[4].addChild(this.tentacle07[5]);
        this.legBase01.addChild(this.rbLeg01);
        this.lbLeg01.addChild(this.lbLeg02);
        this.body.addChild(this.mouth02Top);
        this.tentacle12[0].addChild(this.tentacle12[1]);
        this.tentacle06[0].addChild(this.tentacle06[1]);
        this.rHorn01.addChild(this.rHorn02);
        this.tentacle13[0].addChild(this.tentacle13[1]);
        this.tentacle09[1].addChild(this.tentacle09[2]);
        this.lfLeg01.addChild(this.lfLeg02);
        this.tentacle03[0].addChild(this.tentacle03[1]);
        this.body.addChild(this.mouth03Lower);
        this.tentacle15[2].addChild(this.tentacle15[3]);
        this.body.addChild(this.legBase01);
        this.body.addChild(this.tentacle11[0]);
        this.tentacle05[3].addChild(this.tentacle05[4]);
        this.tentacle14[2].addChild(this.tentacle14[3]);
        this.lbLeg02.addChild(this.lbLeg03);
        this.tentacle08[2].addChild(this.tentacle08[3]);
        this.tentacle02[4].addChild(this.tentacle02[5]);
        this.tentacle09[3].addChild(this.tentacle09[4]);
        this.tentacle03[2].addChild(this.tentacle03[3]);
        this.tentacle15[1].addChild(this.tentacle15[2]);
        this.tentacle03[0].addChild(this.tentacle04[0]);
        this.tentacle04[0].addChild(this.tentacle04[1]);
        this.tentacle07[2].addChild(this.tentacle07[3]);
        this.tentacle09[2].addChild(this.tentacle09[3]);
        this.tentacle10[0].addChild(this.tentacle10[1]);
        this.rfLeg03.addChild(this.rfHoof);
        this.tentacle10[2].addChild(this.tentacle10[3]);
        this.tentacle06[1].addChild(this.tentacle06[2]);
        this.legBase02.addChild(this.lbLeg01);
        this.tentacle01[0].addChild(this.tentacle01[1]);
        this.rfLeg01.addChild(this.rfLeg02);
        this.tentacle11[0].addChild(this.tentacle11[1]);
        this.tentacle05[4].addChild(this.tentacle05[5]);
        this.legBase02.addChild(this.rfLeg01);
        this.tentacle01[3].addChild(this.tentacle01[4]);
        this.lfHoofClaw01a.addChild(this.lfHoofClaw01b);
        this.rHorn02.addChild(this.rHorn03);
        this.tentacle04[1].addChild(this.tentacle04[2]);
        this.tentacle07[0].addChild(this.tentacle07[1]);
        this.tentacle08[1].addChild(this.tentacle08[2]);
        this.lbLeg03.addChild(this.lbHoof);
        this.tentacle02[1].addChild(this.tentacle02[2]);
        this.tentacle12[1].addChild(this.tentacle12[2]);
        this.rbHoof.addChild(this.rbHoofClaw01a);
        this.tentacle01[4].addChild(this.tentacle01[5]);
        this.tentacle02[0].addChild(this.tentacle02[1]);
        this.lbHoof.addChild(this.lbHoofClaw02a);
        this.lHorn01.addChild(this.lHorn02);
        this.tentacle13[2].addChild(this.tentacle13[3]);
        this.tentacle04[2].addChild(this.tentacle04[3]);
        this.tentacle10[1].addChild(this.tentacle10[2]);
        this.tentacle12[3].addChild(this.tentacle12[4]);
        this.lHorn02.addChild(this.lHorn03);
        this.rfHoof.addChild(this.rfHoofClaw02a);
        this.body.addChild(this.mouth01Top);
        this.tentacle07[3].addChild(this.tentacle07[4]);
        this.body.addChild(this.rHorn01);
        this.rfHoofClaw01a.addChild(this.rfHoofClaw01b);
        this.lfLeg03.addChild(this.lfHoof);
        this.tentacle08[0].addChild(this.tentacle08[1]);
        this.rbHoof.addChild(this.rbHoofClaw02a);
        this.tentacle05[0].addChild(this.tentacle05[1]);
        this.body.addChild(this.tentacle13[0]);
        this.tentacle07[1].addChild(this.tentacle07[2]);
        this.rbHoofClaw01a.addChild(this.rbHoofClaw01b);
        this.tentacle01[2].addChild(this.tentacle01[3]);
        this.tentacle14[0].addChild(this.tentacle14[1]);
        this.body.addChild(this.tentacle14[0]);
        this.lbHoofClaw02a.addChild(this.lbHoofClaw02b);
        this.body.addChild(this.tentacle12[0]);
        this.rfHoofClaw02a.addChild(this.rfHoofClaw02b);
        this.tentacle09[0].addChild(this.tentacle09[1]);
        this.body.addChild(this.tentacle07[0]);
        this.body.addChild(this.mouth03Top);
        this.tentacle13[1].addChild(this.tentacle13[2]);
        this.tentacle15[0].addChild(this.tentacle15[1]);
        this.body.addChild(this.tentacle10[0]);
        this.tentacle05[0].addChild(this.tentacle06[0]);
        this.body.addChild(this.mouth02Lower);
        this.lfLeg02.addChild(this.lfLeg03);
        this.rfHoof.addChild(this.rfHoofClaw01a);
        this.body.addChild(this.tentacle05[0]);
        this.tentacle10[3].addChild(this.tentacle10[4]);
        this.tentacle07[0].addChild(this.tentacle08[0]);
        this.body.addChild(this.lHorn01);
        this.tentacle06[2].addChild(this.tentacle06[3]);
        this.rfLeg02.addChild(this.rfLeg03);
        this.body.addChild(this.fur);
        this.lfHoof.addChild(this.lfHoofClaw01a);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        for(ModelRenderer[] t: tentacles){
            for(ModelRenderer s: t){
                setRotateAngle(s, 0, 0, 0);
            }
        }

        this.setRotateAngle(legBase01, 0.0F, 0.7853981633974483F, 0.0F);
        this.setRotateAngle(legBase02, 0.0F, -0.7853981633974483F, 0.0F);


        this.setRotateAngle(lfLeg01, 0.0F, 0.0F, -0.5235987755982988F);
        this.setRotateAngle(lfLeg02, 0.0F, 0.0F, -0.5235987755982988F);
        this.setRotateAngle(lfLeg03, 0.0F, 0.0F, -0.5759586531581287F);
        this.setRotateAngle(lfHoof, 0.0F, 0.0F, 0.05235987755982988F);
        this.setRotateAngle(lfHoofClaw01b, 0.0F, 0.7853981633974483F, 0.3665191429188092F);
        this.setRotateAngle(lfHoofClaw02b, 0.0F, -0.7853981633974483F, 0.3665191429188092F);

        this.setRotateAngle(rfLeg01, 0.0F, 0.0F, 0.5235987755982988F);
        this.setRotateAngle(rfLeg02, 0.0F, 0.0F, 0.5235987755982988F);
        this.setRotateAngle(rfLeg03, 0.0F, 0.0F, 0.5759586531581287F);
        this.setRotateAngle(rfHoof, 0.0F, 0.0F, -0.05235987755982988F);
        this.setRotateAngle(rfHoofClaw01b, 0.0F, -0.7853981633974483F, -0.3665191429188092F);
        this.setRotateAngle(rfHoofClaw02b, 0.0F, 0.7853981633974483F, -0.3665191429188092F);


        this.setRotateAngle(lbLeg01, 0.0F, 0.0F, -0.5235987755982988F);
        this.setRotateAngle(lbLeg02, 0.0F, 0.0F, -0.5235987755982988F);
        this.setRotateAngle(lbLeg03, 0.0F, 0.0F, -0.5759586531581287F);
        this.setRotateAngle(lbHoof, 0.0F, 0.0F, 0.05235987755982988F);
        this.setRotateAngle(lbHoofClaw01b, 0.0F, 0.7853981633974483F, 0.3665191429188092F);
        this.setRotateAngle(lbHoofClaw02b, 0.0F, -0.7853981633974483F, 0.3665191429188092F);

        this.setRotateAngle(rbLeg01, 0.0F, 0.0F, 0.5235987755982988F);
        this.setRotateAngle(rbLeg02, 0.0F, 0.0F, 0.5235987755982988F);
        this.setRotateAngle(rbLeg03, 0.0F, 0.0F, 0.5759586531581287F);
        this.setRotateAngle(rbHoof, 0.0F, 0.0F, -0.05235987755982988F);
        this.setRotateAngle(rbHoofClaw01b, 0.0F, -0.7853981633974483F, -0.3665191429188092F);
        this.setRotateAngle(rbHoofClaw02b, 0.0F, 0.7853981633974483F, -0.3665191429188092F);

        this.setRotateAngle(mouth02Top, 0.0F, 0.0F, 0.7853981633974483F);
        this.setRotateAngle(mouth02Lower, 0.0F, 0.0F, 0.7853981633974483F);

        this.setRotateAngle(mouth03Top, 0.0F, 0.0F, -0.7853981633974483F);
        this.setRotateAngle(mouth03Lower, 0.0F, 0.0F, -0.7853981633974483F);

        this.setRotateAngle(lHorn01, 0.3141592653589793F, 0.0F, 0.5759586531581287F);
        this.setRotateAngle(lHorn02, -0.13962634015954636F, 0.0F, -0.3141592653589793F);
        this.setRotateAngle(lHorn03, -0.13962634015954636F, 0.0F, 0.3141592653589793F);

        this.setRotateAngle(rHorn01, 0.3141592653589793F, 0.0F, -0.5759586531581287F);
        this.setRotateAngle(rHorn02, -0.13962634015954636F, 0.0F, 0.3141592653589793F);
        this.setRotateAngle(rHorn03, -0.13962634015954636F, 0.0F, -0.3141592653589793F);

        this.setRotateAngle(tentacle01[0], 0.5235987755982988F, 0.0F, 0.0F);
        this.setRotateAngle(tentacle01[1], -0.5235987755982988F, 0.0F, -0.03490658503988659F);
        this.setRotateAngle(tentacle01[2], 0.20943951023931953F, 0.0F, -0.08726646259971647F);
        this.setRotateAngle(tentacle01[3], -0.13962634015954636F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(tentacle01[4], 0.20943951023931953F, 0.0F, 0.0F);
        this.setRotateAngle(tentacle01[5], 0.12217304763960307F, 0.0F, -0.15707963267948966F);

        this.setRotateAngle(tentacle02[0], 0.17453292519943295F, 0.0F, 0.08726646259971647F);
        this.setRotateAngle(tentacle02[1], -0.13962634015954636F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(tentacle02[2], 0.0F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(tentacle02[3], 0.0F, 0.0F, -0.2617993877991494F);
        this.setRotateAngle(tentacle02[4], -0.12217304763960307F, 0.0F, -0.22689280275926282F);
        this.setRotateAngle(tentacle02[5], 0.0F, 0.0F, -0.22689280275926282F);

        this.setRotateAngle(tentacle03[0], -0.3490658503988659F, 0.0F, 0.0F);
        this.setRotateAngle(tentacle03[1], 0.08726646259971647F, 0.0F, 0.13962634015954636F);
        this.setRotateAngle(tentacle03[2], 0.03490658503988659F, 0.0F, -0.17453292519943295F);
        this.setRotateAngle(tentacle03[3], 0.17453292519943295F, 0.0F, 0.22689280275926282F);

        this.setRotateAngle(tentacle04[0], 0.0F, 0.0F, -0.2792526803190927F);
        this.setRotateAngle(tentacle04[1], 0.22689280275926282F, 0.0F, 0.3141592653589793F);
        this.setRotateAngle(tentacle04[2], 0.0F, 0.0F, -0.4363323129985824F);
        this.setRotateAngle(tentacle04[3], 0.0F, 0.0F, 0.17453292519943295F);

        this.setRotateAngle(tentacle05[0], 0.0F, 0.0F, 0.5235987755982988F);
        this.setRotateAngle(tentacle05[1], -0.08726646259971647F, 0.0F, -0.22689280275926282F);
        this.setRotateAngle(tentacle05[2], 0.20943951023931953F, 0.0F, -0.13962634015954636F);
        this.setRotateAngle(tentacle05[3], -0.13962634015954636F, 0.0F, -0.08726646259971647F);
        this.setRotateAngle(tentacle05[4], 0.20943951023931953F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(tentacle05[5], 0.12217304763960307F, 0.0F, 0.22689280275926282F);

        this.setRotateAngle(tentacle06[0], -0.4363323129985824F, 0.0F, -0.40142572795869574F);
        this.setRotateAngle(tentacle06[1], 0.3141592653589793F, 0.0F, -0.3490658503988659F);
        this.setRotateAngle(tentacle06[2], 0.0F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(tentacle06[3], 0.0F, 0.0F, -0.2617993877991494F);

        this.setRotateAngle(tentacle07[0], 0.0F, 0.0F, -0.4363323129985824F);
        this.setRotateAngle(tentacle07[1], -0.17453292519943295F, 0.0F, 0.20943951023931953F);
        this.setRotateAngle(tentacle07[2], 0.13962634015954636F, 0.0F, -0.17453292519943295F);
        this.setRotateAngle(tentacle07[3], 0.0F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(tentacle07[4], 0.0F, 0.0F, -0.22689280275926282F);
        this.setRotateAngle(tentacle07[5], 0.0F, 0.0F, -0.22689280275926282F);

        this.setRotateAngle(tentacle08[0], 0.0F, 0.0F, -0.03490658503988659F);
        this.setRotateAngle(tentacle08[1], 0.22689280275926282F, 0.0F, 0.3490658503988659F);
        this.setRotateAngle(tentacle08[3], 0.0F, 0.0F, 0.17453292519943295F);

        this.setRotateAngle(tentacle09[0], 0.0F, 0.0F, -0.22689280275926282F);
        this.setRotateAngle(tentacle09[1], 0.0F, 0.0F, 0.2617993877991494F);
        this.setRotateAngle(tentacle09[2], -0.13962634015954636F, 0.0F, -0.17453292519943295F);
        this.setRotateAngle(tentacle09[3], 0.20943951023931953F, 0.0F, -0.17453292519943295F);
        this.setRotateAngle(tentacle09[4], 0.12217304763960307F, 0.0F, -0.2792526803190927F);

        this.setRotateAngle(tentacle10[0], 0.0F, 0.0F, 0.12217304763960307F);
        this.setRotateAngle(tentacle10[1], 0.20943951023931953F, 0.0F, -0.22689280275926282F);
        this.setRotateAngle(tentacle10[2], -0.13962634015954636F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(tentacle10[3], -0.2792526803190927F, 0.0F, -0.12217304763960307F);
        this.setRotateAngle(tentacle10[4], 0.12217304763960307F, 0.0F, 0.22689280275926282F);

        this.setRotateAngle(tentacle11[0], 0.0F, 0.0F, -0.08726646259971647F);
        this.setRotateAngle(tentacle11[1], 0.0F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(tentacle11[2], -0.13962634015954636F, 0.0F, -0.17453292519943295F);
        this.setRotateAngle(tentacle11[3], 0.20943951023931953F, 0.0F, 0.12217304763960307F);
        this.setRotateAngle(tentacle11[4], 0.12217304763960307F, 0.0F, 0.22689280275926282F);

        this.setRotateAngle(tentacle12[0], -0.17453292519943295F, 0.0F, -0.08726646259971647F);
        this.setRotateAngle(tentacle12[1], 0.0F, 0.0F, 0.22689280275926282F);
        this.setRotateAngle(tentacle12[2], -0.13962634015954636F, 0.0F, -0.17453292519943295F);
        this.setRotateAngle(tentacle12[3], 0.20943951023931953F, 0.0F, -0.13962634015954636F);
        this.setRotateAngle(tentacle12[4], 0.12217304763960307F, 0.0F, 0.0F);

        this.setRotateAngle(tentacle13[0], 0.2617993877991494F, 0.0F, 0.20943951023931953F);
        this.setRotateAngle(tentacle13[1], 0.03490658503988659F, 0.0F, 0.17453292519943295F);
        this.setRotateAngle(tentacle13[2], -0.2617993877991494F, 0.0F, -0.22689280275926282F);
        this.setRotateAngle(tentacle13[3], 0.20943951023931953F, 0.0F, 0.12217304763960307F);
        this.setRotateAngle(tentacle13[4], 0.12217304763960307F, 0.0F, 0.22689280275926282F);

        this.setRotateAngle(tentacle14[0], 0.0F, 0.0F, 0.3141592653589793F);
        this.setRotateAngle(tentacle14[1], 0.22689280275926282F, 0.0F, -0.17453292519943295F);
        this.setRotateAngle(tentacle14[3], 0.0F, 0.0F, 0.17453292519943295F);

        this.setRotateAngle(tentacle15[0], -0.3490658503988659F, 0.0F, 0.3141592653589793F);
        this.setRotateAngle(tentacle15[1], 0.22689280275926282F, 0.0F, -0.17453292519943295F);
        this.setRotateAngle(tentacle15[3], 0.0F, 0.0F, 0.17453292519943295F);

        if (entityIn instanceof EntityDarkYoung) {
            doWalkingAnims(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, (EntityDarkYoung) entityIn);
            doTentacleAnims(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, (EntityDarkYoung) entityIn);
            doHurtAnims((EntityDarkYoung) entityIn);
            doMawAnims(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, (EntityDarkYoung) entityIn);
        }
    }

    private void doMawAnims(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, EntityDarkYoung entityIn){
        float progress =  1 - (float) Math.abs(0.5 - entityIn.swingProgress) * 2;
        this.setRotateAngle(mouth01Top, -0.5759586531581287F, 0, 0, progress); //the one on the top
        this.setRotateAngle(mouth02Top, -0.3759586531581287F, 0, 0, progress);
        this.setRotateAngle(mouth03Top, -0.3759586531581287F, 0, 0, progress);

        this.setRotateAngle(mouth01Lower, 0.3759586531581287F, 0, 0, progress);
        this.setRotateAngle(mouth02Lower, 0.3759586531581287F, 0, 0, progress);
        this.setRotateAngle(mouth03Lower, 0.3759586531581287F, 0, 0, progress);
    }

    private void doTentacleAnims(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, EntityDarkYoung entityIn){
        ModelAnimUtil.waveTentacle(tentacle01, 1F, 0.9F, ModelAnimUtil.calculateTickSequenceProgress(60, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle02, 0.1F, 1.3F, ModelAnimUtil.calculateTickSequenceProgress(70, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle03, 0.8F, 0.1F, ModelAnimUtil.calculateTickSequenceProgress(66, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle04, 1.1F, 0.1F, ModelAnimUtil.calculateTickSequenceProgress(75, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle05, 0.2F, 0.8F, ModelAnimUtil.calculateTickSequenceProgress(62, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle06, 0.5F, 0.5F, ModelAnimUtil.calculateTickSequenceProgress(50, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle07, 0.1F, 1F, ModelAnimUtil.calculateTickSequenceProgress(65, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle08, 0.15F, 1.2F, ModelAnimUtil.calculateTickSequenceProgress(60, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle09, 0.7F, 0.4F, ModelAnimUtil.calculateTickSequenceProgress(60, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle10, 0.3F, 0.6F, ModelAnimUtil.calculateTickSequenceProgress(68, ageInTicks), true);
       //still need to set these
        ModelAnimUtil.waveTentacle(tentacle11, 0.3F, 0.6F, ModelAnimUtil.calculateTickSequenceProgress(68, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle12, 0.3F, 0.6F, ModelAnimUtil.calculateTickSequenceProgress(68, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle13, 0.3F, 0.6F, ModelAnimUtil.calculateTickSequenceProgress(68, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle14, 0.3F, 0.6F, ModelAnimUtil.calculateTickSequenceProgress(68, ageInTicks), true);
        ModelAnimUtil.waveTentacle(tentacle15, 0.3F, 0.6F, ModelAnimUtil.calculateTickSequenceProgress(68, ageInTicks), true);

    }

    private void doWalkingAnims(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, EntityDarkYoung entityIn){
        this.rfLeg01.rotateAngleZ += MathHelper.cos(limbSwing * 0.6662F) * 0.5 * limbSwingAmount;
        this.lfLeg01.rotateAngleZ += MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.5 * limbSwingAmount;
        this.rbLeg01.rotateAngleZ += MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.5 * limbSwingAmount;
        this.lbLeg01.rotateAngleZ += MathHelper.cos(limbSwing * 0.6662F) * 0.5 * limbSwingAmount;
    }

    private void doHurtAnims(EntityDarkYoung entityIn){
        float hurtProgress = 0;
        if (entityIn.maxHurtTime > 0 && entityIn.hurtTime > 0){
            hurtProgress = (entityIn.maxHurtTime - entityIn.hurtTime + Minecraft.getMinecraft().getRenderPartialTicks()) / (float)entityIn.maxHurtTime;
        }

        for (ModelRenderer[] ohGodManyTentacles : tentacles){
            ModelAnimUtil.waveTentacle(ohGodManyTentacles, 1.8F, 1, hurtProgress, true);
        }
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
