package com.miskatonicmysteries.client.model.entity;

import com.miskatonicmysteries.common.entity.cultist.AbstractCultist;
import com.miskatonicmysteries.common.entity.cultist.EntityShubCultist;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;

/**
 * ModelCultistShub - cybercat5555
 * Created using Tabula 7.0.1
 */
public class ModelCultistShub extends ModelCultist {
    public ModelRenderer leftMaskPlate;
    public ModelRenderer rightMaskPlate;
    public ModelRenderer leftEar;
    public ModelRenderer leftHorn00;
    public ModelRenderer strapL;
    public ModelRenderer leftHorn01a;
    public ModelRenderer leftHorn01b;
    public ModelRenderer leftHorn02;
    public ModelRenderer leftHorn03;
    public ModelRenderer strapB;
    public ModelRenderer rightEar;
    public ModelRenderer rightHorn00;
    public ModelRenderer strapR;
    public ModelRenderer rightHorn01a;
    public ModelRenderer rightHorn01b;
    public ModelRenderer rightHorn02;
    public ModelRenderer rightHorn03;
    public ModelRenderer rGoatLeg01;
    public ModelRenderer rGoatLeg02;
    public ModelRenderer rGoatLeg03;
    public ModelRenderer rGoatHoof;
    public ModelRenderer rHoofClaw01a;
    public ModelRenderer rHoofClaw02a;
    public ModelRenderer rHoofClaw01b;
    public ModelRenderer rHoofClaw02b;

    public ModelRenderer lGoatLeg01;
    public ModelRenderer lGoatLeg02;
    public ModelRenderer lGoatLeg03;
    public ModelRenderer lGoatHoof;
    public ModelRenderer lHoofClaw01a;
    public ModelRenderer lHoofClaw02a;
    public ModelRenderer lHoofClaw01b;
    public ModelRenderer lHoofClaw02b;

    public ModelCultistShub() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.rHoofClaw02a = new ModelRenderer(this, 114, 14);
        this.rHoofClaw02a.mirror = true;
        this.rHoofClaw02a.setRotationPoint(0.6F, 0.0F, -1.3F);
        this.rHoofClaw02a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rHoofClaw02a, 0.3490658503988659F, -0.06981317007977318F, 0.0F);
        this.hoodPipe02 = new ModelRenderer(this, 109, 33);
        this.hoodPipe02.setRotationPoint(0.0F, -0.3F, 1.0F);
        this.hoodPipe02.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 4, 0.0F);
        this.setRotateAngle(hoodPipe02, -0.45378560551852565F, 0.0F, 0.0F);
        this.leftMaskPlate = new ModelRenderer(this, 68, 0);
        this.leftMaskPlate.setRotationPoint(0.0F, -5.1F, -4.9F);
        this.leftMaskPlate.addBox(-0.1F, -2.5F, -0.5F, 4, 7, 1, 0.0F);
        this.setRotateAngle(leftMaskPlate, -0.12217304763960307F, -0.13962634015954636F, 0.0F);
        this.rightEar = new ModelRenderer(this, 80, 0);
        this.rightEar.mirror = true;
        this.rightEar.setRotationPoint(-3.8F, -1.0F, 0.2F);
        this.rightEar.addBox(-2.6F, -1.0F, -0.5F, 3, 2, 1, 0.0F);
        this.setRotateAngle(rightEar, 0.3141592653589793F, 0.0F, 0.20943951023931953F);
        this.lGoatLeg01 = new ModelRenderer(this, 98, 0);
        this.lGoatLeg01.setRotationPoint(0.1F, 0.0F, 0.0F);
        this.lGoatLeg01.addBox(-2.0F, -1.5F, -2.4F, 4, 7, 4, 0.0F);
        this.setRotateAngle(lGoatLeg01, -0.5235987755982988F, -0.03490658503988659F, -0.03490658503988659F);
        lGoatLeg01.isHidden = true;
        this.leftHorn01b = new ModelRenderer(this, 78, 9);
        this.leftHorn01b.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftHorn01b.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.leftHorn03 = new ModelRenderer(this, 78, 9);
        this.leftHorn03.setRotationPoint(0.0F, -1.8F, 0.0F);
        this.leftHorn03.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(leftHorn03, 0.0F, 0.0F, 0.3141592653589793F);
        this.rightHorn00 = new ModelRenderer(this, 70, 9);
        this.rightHorn00.mirror = true;
        this.rightHorn00.setRotationPoint(-1.7F, -2.0F, 0.6F);
        this.rightHorn00.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(rightHorn00, 0.0F, 0.0F, -0.3839724354387525F);
        this.rGoatLeg01 = new ModelRenderer(this, 98, 0);
        this.rGoatLeg01.mirror = true;
        this.rGoatLeg01.setRotationPoint(-0.1F, 0.0F, 0.0F);
        this.rGoatLeg01.addBox(-2.0F, -1.5F, -2.4F, 4, 7, 4, 0.0F);
        this.setRotateAngle(rGoatLeg01, -0.5235987755982988F, 0.03490658503988659F, 0.03490658503988659F);
        this.rGoatLeg01.isHidden = true;
        this.nose = new ModelRenderer(this, 24, 0);
        this.nose.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.nose.addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F);
        this.rGoatLeg02 = new ModelRenderer(this, 115, 0);
        this.rGoatLeg02.mirror = true;
        this.rGoatLeg02.setRotationPoint(0.0F, 3.8F, -0.8F);
        this.rGoatLeg02.addBox(-1.61F, -0.4F, -2.0F, 3, 5, 3, 0.0F);
        this.setRotateAngle(rGoatLeg02, 1.1693705988362009F, 0.0F, -0.03490658503988659F);
        this.rightHorn01a = new ModelRenderer(this, 78, 9);
        this.rightHorn01a.mirror = true;
        this.rightHorn01a.setRotationPoint(0.3F, -1.1F, -0.4F);
        this.rightHorn01a.addBox(-0.8F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(rightHorn01a, 0.0F, 0.0F, -0.20943951023931953F);
        this.lGoatLeg03 = new ModelRenderer(this, 102, 12);
        this.lGoatLeg03.setRotationPoint(0.0F, 4.4F, -0.2F);
        this.lGoatLeg03.addBox(-1.4F, -0.4F, -1.0F, 3, 6, 2, 0.0F);
        this.setRotateAngle(lGoatLeg03, -0.6283185307179586F, 0.0F, 0.0F);
        this.rHoofClaw01a = new ModelRenderer(this, 114, 14);
        this.rHoofClaw01a.mirror = true;
        this.rHoofClaw01a.setRotationPoint(-0.9F, 0.0F, -1.3F);
        this.rHoofClaw01a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rHoofClaw01a, 0.3490658503988659F, 0.10471975511965977F, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 22);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.lGoatHoof = new ModelRenderer(this, 114, 9);
        this.lGoatHoof.setRotationPoint(0.1F, 5.0F, 0.0F);
        this.lGoatHoof.addBox(-1.4F, 0.0F, -2.6F, 3, 1, 3, 0.0F);
        this.hoodRSide02 = new ModelRenderer(this, 79, 51);
        this.hoodRSide02.mirror = true;
        this.hoodRSide02.setRotationPoint(-5.15F, 1.3F, 0.3F);
        this.hoodRSide02.addBox(-0.6F, -0.9F, -5.25F, 6, 2, 10, 0.0F);
        this.setRotateAngle(hoodRSide02, 0.0F, 0.0F, 0.3141592653589793F);
        this.bipedBody = new ModelRenderer(this, 16, 20);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.rHoofClaw02b = new ModelRenderer(this, 121, 14);
        this.rHoofClaw02b.mirror = true;
        this.rHoofClaw02b.setRotationPoint(0.0F, 0.3F, -0.5F);
        this.rHoofClaw02b.addBox(-0.5F, -0.5F, -2.2F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rHoofClaw02b, -0.22689280275926282F, -0.22689280275926282F, -0.7853981633974483F);
        this.leftArmFolded = new ModelRenderer(this, 44, 22);
        this.leftArmFolded.mirror = true;
        this.leftArmFolded.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftArmFolded.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.strapL = new ModelRenderer(this, 78, 8);
        this.strapL.setRotationPoint(3.3F, 0.0F, 0.0F);
        this.strapL.addBox(0.0F, -0.5F, 0.0F, 1, 1, 9, 0.0F);
        this.setRotateAngle(strapL, 0.12217304763960307F, 0.13962634015954636F, 0.0F);
        this.middleArmFolded = new ModelRenderer(this, 40, 38);
        this.middleArmFolded.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.middleArmFolded.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(middleArmFolded, -0.7499679795819634F, 0.0F, 0.0F);
        this.leftHorn00 = new ModelRenderer(this, 70, 9);
        this.leftHorn00.setRotationPoint(1.7F, -2.0F, 0.6F);
        this.leftHorn00.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(leftHorn00, 0.0F, 0.0F, 0.3839724354387525F);
        this.hoodFringeR03 = new ModelRenderer(this, 88, 39);
        this.hoodFringeR03.mirror = true;
        this.hoodFringeR03.setRotationPoint(-0.6F, -8.8F, 0.1F);
        this.hoodFringeR03.addBox(-3.2F, -0.6F, -5.08F, 4, 1, 10, 0.0F);
        this.setRotateAngle(hoodFringeR03, 0.0F, 0.0F, -0.2617993877991494F);
        this.rightArmFolded = new ModelRenderer(this, 44, 22);
        this.rightArmFolded.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.rightArmFolded.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(rightArmFolded, -0.7499679795819634F, 0.0F, 0.0F);
        this.hoodPipe01 = new ModelRenderer(this, 106, 22);
        this.hoodPipe01.setRotationPoint(0.0F, -6.6F, 4.0F);
        this.hoodPipe01.addBox(-3.0F, -2.5F, 0.0F, 6, 6, 2, 0.0F);
        this.setRotateAngle(hoodPipe01, -0.5235987755982988F, 0.0F, 0.0F);
        this.lHoofClaw02b = new ModelRenderer(this, 121, 14);
        this.lHoofClaw02b.setRotationPoint(0.0F, 0.3F, -0.5F);
        this.lHoofClaw02b.addBox(-0.5F, -0.5F, -2.2F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lHoofClaw02b, -0.22689280275926282F, 0.22689280275926282F, 0.7853981633974483F);
        this.leftHorn02 = new ModelRenderer(this, 78, 9);
        this.leftHorn02.setRotationPoint(-0.2F, -2.6F, 0.0F);
        this.leftHorn02.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(leftHorn02, 0.0F, 0.0F, 0.3141592653589793F);
        this.strapB = new ModelRenderer(this, 79, 5);
        this.strapB.setRotationPoint(0.0F, 0.0F, 8.5F);
        this.strapB.addBox(-6.7F, -0.5F, -0.5F, 7, 1, 1, 0.0F);
        this.rightHorn02 = new ModelRenderer(this, 78, 9);
        this.rightHorn02.mirror = true;
        this.rightHorn02.setRotationPoint(-0.2F, -2.6F, 0.0F);
        this.rightHorn02.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rightHorn02, 0.0F, 0.0F, -0.3141592653589793F);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.rightMaskPlate = new ModelRenderer(this, 68, 0);
        this.rightMaskPlate.mirror = true;
        this.rightMaskPlate.setRotationPoint(0.0F, -5.0F, -4.9F);
        this.rightMaskPlate.addBox(-3.9F, -2.5F, -0.5F, 4, 7, 1, 0.0F);
        this.setRotateAngle(rightMaskPlate, -0.12217304763960307F, 0.13962634015954636F, 0.0F);
        this.rightHorn03 = new ModelRenderer(this, 78, 9);
        this.rightHorn03.mirror = true;
        this.rightHorn03.setRotationPoint(0.0F, -1.8F, 0.0F);
        this.rightHorn03.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rightHorn03, 0.0F, 0.0F, -0.3141592653589793F);
        this.hood = new ModelRenderer(this, 63, 18);
        this.hood.setRotationPoint(0.0F, -1.6F, 0.0F);
        this.hood.addBox(-4.5F, -8.7F, -4.7F, 9, 11, 10, 0.0F);
        this.hoodFringeL01 = new ModelRenderer(this, 64, 40);
        this.hoodFringeL01.setRotationPoint(2.3F, -7.8F, 0.1F);
        this.hoodFringeL01.addBox(0.2F, -0.8F, -5.09F, 1, 10, 10, 0.0F);
        this.setRotateAngle(hoodFringeL01, 0.0F, 0.0F, -0.22689280275926282F);
        this.lHoofClaw01a = new ModelRenderer(this, 114, 14);
        this.lHoofClaw01a.setRotationPoint(0.9F, 0.0F, -1.3F);
        this.lHoofClaw01a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lHoofClaw01a, 0.3490658503988659F, -0.10471975511965977F, 0.0F);
        this.strapR = new ModelRenderer(this, 78, 8);
        this.strapR.setRotationPoint(-3.3F, 0.0F, 0.0F);
        this.strapR.addBox(-1.0F, -0.4F, 0.0F, 1, 1, 9, 0.0F);
        this.setRotateAngle(strapR, 0.12217304763960307F, -0.13962634015954636F, 0.0F);
        this.leftHorn01a = new ModelRenderer(this, 78, 9);
        this.leftHorn01a.setRotationPoint(0.0F, -1.1F, -0.4F);
        this.leftHorn01a.addBox(-0.8F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(leftHorn01a, 0.0F, 0.0F, 0.20943951023931953F);
        this.rHoofClaw01b = new ModelRenderer(this, 121, 14);
        this.rHoofClaw01b.mirror = true;
        this.rHoofClaw01b.setRotationPoint(0.0F, 0.3F, -0.5F);
        this.rHoofClaw01b.addBox(-0.5F, -0.5F, -2.2F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rHoofClaw01b, -0.22689280275926282F, -0.22689280275926282F, -0.7853981633974483F);
        this.hoodLSide02 = new ModelRenderer(this, 79, 51);
        this.hoodLSide02.setRotationPoint(5.15F, 1.4F, 0.3F);
        this.hoodLSide02.addBox(-5.5F, -0.9F, -5.25F, 6, 2, 10, 0.0F);
        this.setRotateAngle(hoodLSide02, 0.0F, 0.0F, -0.3141592653589793F);
        this.lGoatLeg02 = new ModelRenderer(this, 115, 0);
        this.lGoatLeg02.setRotationPoint(0.0F, 3.8F, -0.8F);
        this.lGoatLeg02.addBox(-1.39F, -0.4F, -2.0F, 3, 5, 3, 0.0F);
        this.setRotateAngle(lGoatLeg02, 1.1693705988362009F, 0.0F, 0.03490658503988659F);
        this.hoodFringeL02 = new ModelRenderer(this, 88, 39);
        this.hoodFringeL02.setRotationPoint(3.3F, -8.1F, 0.1F);
        this.hoodFringeL02.addBox(-3.6F, -0.6F, -5.08F, 4, 1, 10, 0.0F);
        this.setRotateAngle(hoodFringeL02, 0.0F, 0.0F, 0.2617993877991494F);
        this.hoodFringeR01 = new ModelRenderer(this, 64, 40);
        this.hoodFringeR01.mirror = true;
        this.hoodFringeR01.setRotationPoint(-1.7F, -7.8F, 0.1F);
        this.hoodFringeR01.addBox(-1.9F, -0.6F, -5.09F, 1, 10, 10, 0.0F);
        this.setRotateAngle(hoodFringeR01, 0.0F, 0.0F, 0.22689280275926282F);
        this.leftEar = new ModelRenderer(this, 80, 0);
        this.leftEar.setRotationPoint(3.8F, -1.0F, 0.2F);
        this.leftEar.addBox(-0.4F, -1.0F, -0.5F, 3, 2, 1, 0.0F);
        this.setRotateAngle(leftEar, 0.3141592653589793F, 0.0F, -0.20943951023931953F);
        this.lHoofClaw01b = new ModelRenderer(this, 121, 14);
        this.lHoofClaw01b.setRotationPoint(0.0F, 0.3F, -0.5F);
        this.lHoofClaw01b.addBox(-0.5F, -0.5F, -2.2F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lHoofClaw01b, -0.22689280275926282F, 0.22689280275926282F, 0.7853981633974483F);
        this.bipedRightArm = new ModelRenderer(this, 40, 46);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.rGoatHoof = new ModelRenderer(this, 114, 9);
        this.rGoatHoof.mirror = true;
        this.rGoatHoof.setRotationPoint(-0.1F, 5.0F, 0.0F);
        this.rGoatHoof.addBox(-1.6F, 0.0F, -2.6F, 3, 1, 3, 0.0F);
        this.rGoatLeg03 = new ModelRenderer(this, 102, 12);
        this.rGoatLeg03.mirror = true;
        this.rGoatLeg03.setRotationPoint(0.0F, 4.4F, -0.2F);
        this.rGoatLeg03.addBox(-1.6F, -0.4F, -1.0F, 3, 6, 2, 0.0F);
        this.setRotateAngle(rGoatLeg03, -0.6283185307179586F, 0.0F, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 40, 46);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.robe = new ModelRenderer(this, 0, 38);
        this.robe.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.robe.addBox(-4.0F, 0.0F, -3.0F, 8, 20, 6, 0.5F);
        this.rightHorn01b = new ModelRenderer(this, 78, 9);
        this.rightHorn01b.mirror = true;
        this.rightHorn01b.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightHorn01b.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 22);
        this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.lHoofClaw02a = new ModelRenderer(this, 114, 14);
        this.lHoofClaw02a.setRotationPoint(-0.6F, 0.0F, -1.3F);
        this.lHoofClaw02a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lHoofClaw02a, 0.3490658503988659F, 0.06981317007977318F, 0.0F);
        this.rGoatHoof.addChild(this.rHoofClaw02a);
        this.hoodPipe01.addChild(this.hoodPipe02);
        this.bipedHead.addChild(this.leftMaskPlate);
        this.rightMaskPlate.addChild(this.rightEar);
        this.bipedLeftLeg.addChild(this.lGoatLeg01);
        this.leftHorn01a.addChild(this.leftHorn01b);
        this.leftHorn02.addChild(this.leftHorn03);
        this.rightMaskPlate.addChild(this.rightHorn00);
        this.bipedRightLeg.addChild(this.rGoatLeg01);
        this.bipedHead.addChild(this.nose);
        this.rGoatLeg01.addChild(this.rGoatLeg02);
        this.rightHorn00.addChild(this.rightHorn01a);
        this.lGoatLeg02.addChild(this.lGoatLeg03);
        this.rGoatHoof.addChild(this.rHoofClaw01a);
        this.lGoatLeg03.addChild(this.lGoatHoof);
        this.hood.addChild(this.hoodRSide02);
        this.rHoofClaw02a.addChild(this.rHoofClaw02b);
        this.rightArmFolded.addChild(this.leftArmFolded);
        this.leftMaskPlate.addChild(this.strapL);
        this.leftMaskPlate.addChild(this.leftHorn00);
        this.hood.addChild(this.hoodFringeR03);
        this.hood.addChild(this.hoodPipe01);
        this.lHoofClaw02a.addChild(this.lHoofClaw02b);
        this.leftHorn01a.addChild(this.leftHorn02);
        this.strapL.addChild(this.strapB);
        this.rightHorn01a.addChild(this.rightHorn02);
        this.bipedHead.addChild(this.rightMaskPlate);
        this.rightHorn02.addChild(this.rightHorn03);
        this.bipedHead.addChild(this.hood);
        this.hood.addChild(this.hoodFringeL01);
        this.lGoatHoof.addChild(this.lHoofClaw01a);
        this.rightMaskPlate.addChild(this.strapR);
        this.leftHorn00.addChild(this.leftHorn01a);
        this.rHoofClaw01a.addChild(this.rHoofClaw01b);
        this.hood.addChild(this.hoodLSide02);
        this.lGoatLeg01.addChild(this.lGoatLeg02);
        this.hood.addChild(this.hoodFringeL02);
        this.hood.addChild(this.hoodFringeR01);
        this.leftMaskPlate.addChild(this.leftEar);
        this.lHoofClaw01a.addChild(this.lHoofClaw01b);
        this.rGoatLeg03.addChild(this.rGoatHoof);
        this.rGoatLeg02.addChild(this.rGoatLeg03);
        this.rightHorn01a.addChild(this.rightHorn01b);
        this.lGoatHoof.addChild(this.lHoofClaw02a);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        if (entity instanceof EntityShubCultist) {
            if (((EntityShubCultist) entity).isGoat()){
                lGoatLeg01.isHidden = false;
                rGoatLeg01.isHidden = false;
            }
        }
        this.bipedBody.render(f5);
        if (entity instanceof AbstractCultist && ((AbstractCultist) entity).armsFolded()) {
            this.middleArmFolded.render(f5);
            this.rightArmFolded.render(f5);
        }else{
            this.bipedRightArm.render(f5);
            this.bipedLeftArm.render(f5);
        }
        this.bipedLeftLeg.render(f5);
        this.bipedRightLeg.render(f5);

        this.bipedHead.render(f5);
        this.robe.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
