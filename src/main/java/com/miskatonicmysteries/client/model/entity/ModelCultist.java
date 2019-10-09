package com.miskatonicmysteries.client.model.entity;

import com.miskatonicmysteries.common.entity.cultist.AbstractCultist;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;

public class ModelCultist extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer rightArm;
    public ModelRenderer rightLeg;
    public ModelRenderer leftLeg;
    public ModelRenderer leftArm;
    public ModelRenderer body;
    public ModelRenderer robe;
    public ModelRenderer rightArmFolded;
    public ModelRenderer middleArmFolded;
    public ModelRenderer nose;
    public ModelRenderer hood;
    public ModelRenderer hoodFringeL01;
    public ModelRenderer hoodFringeR01;
    public ModelRenderer hoodFringeL02;
    public ModelRenderer hoodFringeR03;
    public ModelRenderer hoodPipe01;
    public ModelRenderer hoodLSide02;
    public ModelRenderer hoodRSide02;
    public ModelRenderer hoodPipe02;
    public ModelRenderer leftArmFolded;

    public ModelCultist() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.hoodPipe02 = new ModelRenderer(this, 109, 33);
        this.hoodPipe02.setRotationPoint(0.0F, -0.3F, 1.0F);
        this.hoodPipe02.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 4, 0.0F);
        this.setRotateAngle(hoodPipe02, -0.45378560551852565F, 0.0F, 0.0F);
        this.nose = new ModelRenderer(this, 24, 0);
        this.nose.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.nose.addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F);
        this.leftLeg = new ModelRenderer(this, 0, 22);
        this.leftLeg.mirror = true;
        this.leftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.hoodRSide02 = new ModelRenderer(this, 79, 51);
        this.hoodRSide02.mirror = true;
        this.hoodRSide02.setRotationPoint(-5.15F, 1.3F, 0.3F);
        this.hoodRSide02.addBox(-0.6F, -0.9F, -5.25F, 6, 2, 10, 0.0F);
        this.setRotateAngle(hoodRSide02, 0.0F, 0.0F, 0.3141592653589793F);
        this.body = new ModelRenderer(this, 16, 20);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.leftArmFolded = new ModelRenderer(this, 44, 22);
        this.leftArmFolded.mirror = true;
        this.leftArmFolded.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftArmFolded.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.middleArmFolded = new ModelRenderer(this, 40, 38);
        this.middleArmFolded.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.middleArmFolded.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(middleArmFolded, -0.7499679795819634F, 0.0F, 0.0F);
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
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.hood = new ModelRenderer(this, 63, 18);
        this.hood.setRotationPoint(0.0F, -1.6F, 0.0F);
        this.hood.addBox(-4.5F, -8.7F, -4.7F, 9, 11, 10, 0.0F);
        this.hoodFringeL01 = new ModelRenderer(this, 64, 40);
        this.hoodFringeL01.setRotationPoint(2.3F, -7.8F, 0.1F);
        this.hoodFringeL01.addBox(0.2F, -0.8F, -5.09F, 1, 10, 10, 0.0F);
        this.setRotateAngle(hoodFringeL01, 0.0F, 0.0F, -0.22689280275926282F);
        this.hoodLSide02 = new ModelRenderer(this, 79, 51);
        this.hoodLSide02.setRotationPoint(5.15F, 1.4F, 0.3F);
        this.hoodLSide02.addBox(-5.5F, -0.9F, -5.25F, 6, 2, 10, 0.0F);
        this.setRotateAngle(hoodLSide02, 0.0F, 0.0F, -0.3141592653589793F);
        this.hoodFringeL02 = new ModelRenderer(this, 88, 39);
        this.hoodFringeL02.setRotationPoint(3.3F, -8.1F, 0.1F);
        this.hoodFringeL02.addBox(-3.6F, -0.6F, -5.08F, 4, 1, 10, 0.0F);
        this.setRotateAngle(hoodFringeL02, 0.0F, 0.0F, 0.2617993877991494F);
        this.hoodFringeR01 = new ModelRenderer(this, 64, 40);
        this.hoodFringeR01.mirror = true;
        this.hoodFringeR01.setRotationPoint(-1.7F, -7.8F, 0.1F);
        this.hoodFringeR01.addBox(-1.9F, -0.6F, -5.09F, 1, 10, 10, 0.0F);
        this.setRotateAngle(hoodFringeR01, 0.0F, 0.0F, 0.22689280275926282F);
        this.rightArm = new ModelRenderer(this, 40, 46);
        this.rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.leftArm = new ModelRenderer(this, 40, 46);
        this.leftArm.mirror = true;
        this.leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.robe = new ModelRenderer(this, 0, 38);
        this.robe.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.robe.addBox(-4.0F, 0.0F, -3.0F, 8, 20, 6, 0.5F);
        this.rightLeg = new ModelRenderer(this, 0, 22);
        this.rightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.hoodPipe01.addChild(this.hoodPipe02);
        this.head.addChild(this.nose);
        this.hood.addChild(this.hoodRSide02);
        this.rightArmFolded.addChild(this.leftArmFolded);
        this.hood.addChild(this.hoodFringeR03);
        this.hood.addChild(this.hoodPipe01);
        this.head.addChild(this.hood);
        this.hood.addChild(this.hoodFringeL01);
        this.hood.addChild(this.hoodLSide02);
        this.hood.addChild(this.hoodFringeL02);
        this.hood.addChild(this.hoodFringeR01);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.leftLeg.render(f5);
        this.body.render(f5);

        if (entity instanceof AbstractCultist && ((AbstractCultist) entity).armsFolded()) {
            this.middleArmFolded.render(f5);
            this.rightArmFolded.render(f5);
        }else{
            this.rightArm.render(f5);
            this.leftArm.render(f5);
        }
        this.head.render(f5);
        this.robe.render(f5);
        this.rightLeg.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.setRotateAngle(hoodPipe02, -0.45378560551852565F, 0.0F, 0.0F);
        this.setRotateAngle(hoodRSide02, 0.0F, 0.0F, 0.3141592653589793F);
        this.setRotateAngle(middleArmFolded, -0.7499679795819634F, 0.0F, 0.0F);
        this.setRotateAngle(hoodFringeR03, 0.0F, 0.0F, -0.2617993877991494F);
        this.setRotateAngle(rightArmFolded, -0.7499679795819634F, 0.0F, 0.0F);
        this.setRotateAngle(hoodPipe01, -0.5235987755982988F, 0.0F, 0.0F);
        this.setRotateAngle(hoodFringeL01, 0.0F, 0.0F, -0.22689280275926282F);
        this.setRotateAngle(hoodLSide02, 0.0F, 0.0F, -0.3141592653589793F);
        this.setRotateAngle(hoodFringeL02, 0.0F, 0.0F, 0.2617993877991494F);
        this.setRotateAngle(hoodFringeR01, 0.0F, 0.0F, 0.22689280275926282F);
        //that will be adapted etc. you know that deal
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
