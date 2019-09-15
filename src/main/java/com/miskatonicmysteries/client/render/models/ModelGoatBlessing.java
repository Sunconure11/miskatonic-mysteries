package com.miskatonicmysteries.client.render.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelGoatBlessing - cybecat5555
 * Created using Tabula 7.0.1
 */
public class ModelGoatBlessing extends ModelBase {
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedLeftLeg;
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

    public ModelGoatBlessing() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.lGoatLeg01 = new ModelRenderer(this, 65, 0);
        this.lGoatLeg01.setRotationPoint(0.1F, 0.0F, 0.0F);
        this.lGoatLeg01.addBox(-2.0F, -1.5F, -2.4F, 4, 7, 4, 0.0F);
        this.setRotateAngle(lGoatLeg01, -0.5235987755982988F, -0.03490658503988659F, -0.03490658503988659F);
        this.lGoatHoof = new ModelRenderer(this, 68, 33);
        this.lGoatHoof.setRotationPoint(0.1F, 5.0F, 0.0F);
        this.lGoatHoof.addBox(-1.4F, 0.0F, -2.6F, 3, 1, 3, 0.0F);
        this.rGoatLeg02 = new ModelRenderer(this, 66, 13);
        this.rGoatLeg02.mirror = true;
        this.rGoatLeg02.setRotationPoint(0.0F, 3.8F, -0.8F);
        this.rGoatLeg02.addBox(-1.61F, -0.4F, -2.0F, 3, 5, 3, 0.0F);
        this.setRotateAngle(rGoatLeg02, 1.1693705988362009F, 0.0F, -0.03490658503988659F);
        this.rGoatHoof = new ModelRenderer(this, 68, 33);
        this.rGoatHoof.mirror = true;
        this.rGoatHoof.setRotationPoint(-0.1F, 5.0F, 0.0F);
        this.rGoatHoof.addBox(-1.6F, 0.0F, -2.6F, 3, 1, 3, 0.0F);
        this.rHoofClaw01b = new ModelRenderer(this, 82, 5);
        this.rHoofClaw01b.mirror = true;
        this.rHoofClaw01b.setRotationPoint(0.0F, 0.3F, -0.5F);
        this.rHoofClaw01b.addBox(-0.5F, -0.5F, -2.2F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rHoofClaw01b, -0.22689280275926282F, -0.22689280275926282F, -0.7853981633974483F);
        this.rHoofClaw02a = new ModelRenderer(this, 81, 0);
        this.rHoofClaw02a.mirror = true;
        this.rHoofClaw02a.setRotationPoint(0.6F, 0.0F, -1.3F);
        this.rHoofClaw02a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rHoofClaw02a, 0.3490658503988659F, -0.06981317007977318F, 0.0F);
        this.lHoofClaw01b = new ModelRenderer(this, 82, 5);
        this.lHoofClaw01b.setRotationPoint(0.0F, 0.3F, -0.5F);
        this.lHoofClaw01b.addBox(-0.5F, -0.5F, -2.2F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lHoofClaw01b, -0.22689280275926282F, 0.22689280275926282F, 0.7853981633974483F);
        this.rGoatLeg01 = new ModelRenderer(this, 65, 0);
        this.rGoatLeg01.mirror = true;
        this.rGoatLeg01.setRotationPoint(-0.1F, 0.0F, 0.0F);
        this.rGoatLeg01.addBox(-2.0F, -1.5F, -2.4F, 4, 7, 4, 0.0F);
        this.setRotateAngle(rGoatLeg01, -0.5235987755982988F, 0.03490658503988659F, 0.03490658503988659F);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.lHoofClaw01a = new ModelRenderer(this, 81, 0);
        this.lHoofClaw01a.setRotationPoint(0.9F, 0.0F, -1.3F);
        this.lHoofClaw01a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lHoofClaw01a, 0.3490658503988659F, -0.10471975511965977F, 0.0F);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.rGoatLeg03 = new ModelRenderer(this, 67, 23);
        this.rGoatLeg03.mirror = true;
        this.rGoatLeg03.setRotationPoint(0.0F, 4.4F, -0.2F);
        this.rGoatLeg03.addBox(-1.6F, -0.4F, -1.0F, 3, 6, 2, 0.0F);
        this.setRotateAngle(rGoatLeg03, -0.6283185307179586F, 0.0F, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.rHoofClaw01a = new ModelRenderer(this, 81, 0);
        this.rHoofClaw01a.mirror = true;
        this.rHoofClaw01a.setRotationPoint(-0.9F, 0.0F, -1.3F);
        this.rHoofClaw01a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rHoofClaw01a, 0.3490658503988659F, 0.10471975511965977F, 0.0F);
        this.lHoofClaw02a = new ModelRenderer(this, 81, 0);
        this.lHoofClaw02a.setRotationPoint(-0.6F, 0.0F, -1.3F);
        this.lHoofClaw02a.addBox(-0.5F, -0.5F, -1.9F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lHoofClaw02a, 0.3490658503988659F, 0.06981317007977318F, 0.0F);
        this.lHoofClaw02b = new ModelRenderer(this, 82, 5);
        this.lHoofClaw02b.setRotationPoint(0.0F, 0.3F, -0.5F);
        this.lHoofClaw02b.addBox(-0.5F, -0.5F, -2.2F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lHoofClaw02b, -0.22689280275926282F, 0.22689280275926282F, 0.7853981633974483F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.rHoofClaw02b = new ModelRenderer(this, 82, 5);
        this.rHoofClaw02b.mirror = true;
        this.rHoofClaw02b.setRotationPoint(0.0F, 0.3F, -0.5F);
        this.rHoofClaw02b.addBox(-0.5F, -0.5F, -2.2F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rHoofClaw02b, -0.22689280275926282F, -0.22689280275926282F, -0.7853981633974483F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.lGoatLeg03 = new ModelRenderer(this, 67, 23);
        this.lGoatLeg03.setRotationPoint(0.0F, 4.4F, -0.2F);
        this.lGoatLeg03.addBox(-1.4F, -0.4F, -1.0F, 3, 6, 2, 0.0F);
        this.setRotateAngle(lGoatLeg03, -0.6283185307179586F, 0.0F, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 32, 48);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.lGoatLeg02 = new ModelRenderer(this, 66, 13);
        this.lGoatLeg02.setRotationPoint(0.0F, 3.8F, -0.8F);
        this.lGoatLeg02.addBox(-1.39F, -0.4F, -2.0F, 3, 5, 3, 0.0F);
        this.setRotateAngle(lGoatLeg02, 1.1693705988362009F, 0.0F, 0.03490658503988659F);
        this.bipedLeftLeg.addChild(this.lGoatLeg01);
        this.lGoatLeg03.addChild(this.lGoatHoof);
        this.rGoatLeg01.addChild(this.rGoatLeg02);
        this.rGoatLeg03.addChild(this.rGoatHoof);
        this.rHoofClaw01a.addChild(this.rHoofClaw01b);
        this.rGoatHoof.addChild(this.rHoofClaw02a);
        this.lHoofClaw01a.addChild(this.lHoofClaw01b);
        this.bipedRightLeg.addChild(this.rGoatLeg01);
        this.lGoatHoof.addChild(this.lHoofClaw01a);
        this.rGoatLeg02.addChild(this.rGoatLeg03);
        this.rGoatHoof.addChild(this.rHoofClaw01a);
        this.lGoatHoof.addChild(this.lHoofClaw02a);
        this.lHoofClaw02a.addChild(this.lHoofClaw02b);
        this.rHoofClaw02a.addChild(this.rHoofClaw02b);
        this.lGoatLeg02.addChild(this.lGoatLeg03);
        this.lGoatLeg01.addChild(this.lGoatLeg02);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.bipedBody.render(f5);
        this.bipedHead.render(f5);
        this.bipedLeftLeg.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedRightArm.render(f5);
        this.bipedLeftArm.render(f5);
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
