package com.miskatonicmysteries.client.model.tile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * shub_statue - cybecat5555
 * Created using Tabula 7.0.1
 */
public class ModelShubStatue extends ModelBase {
    public ModelRenderer plinth;
    public ModelRenderer body;
    public ModelRenderer chest;
    public ModelRenderer lTentacle00a;
    public ModelRenderer lTentacle01a;
    public ModelRenderer rTentacle00a;
    public ModelRenderer rTentacle01a;
    public ModelRenderer lTentacle02a;
    public ModelRenderer rTentacle02a;
    public ModelRenderer boobs;
    public ModelRenderer lArm01;
    public ModelRenderer rArm01;
    public ModelRenderer head;
    public ModelRenderer lTentacle03a;
    public ModelRenderer rTentacle03a;
    public ModelRenderer lArm02;
    public ModelRenderer lHoof01;
    public ModelRenderer lHoof02;
    public ModelRenderer rArm02;
    public ModelRenderer rHoof01;
    public ModelRenderer rHoof02;
    public ModelRenderer snout;
    public ModelRenderer snoutSnope;
    public ModelRenderer lHorn00;
    public ModelRenderer rHorn00;
    public ModelRenderer lEar;
    public ModelRenderer rEar;
    public ModelRenderer lHorn01a;
    public ModelRenderer lHorn01b;
    public ModelRenderer lHorn02a;
    public ModelRenderer lHorn02b;
    public ModelRenderer lHorn03;
    public ModelRenderer rHorn01a;
    public ModelRenderer rHorn01b;
    public ModelRenderer rHorn02a;
    public ModelRenderer rHorn02b;
    public ModelRenderer rHorn03;
    public ModelRenderer lTentacle03b;
    public ModelRenderer lTentacle03c;
    public ModelRenderer lTentacle03d;
    public ModelRenderer rTentacle03b;
    public ModelRenderer rTentacle03c;
    public ModelRenderer rTentacle03d;
    public ModelRenderer lTentacle00b;
    public ModelRenderer lTentacle00c;
    public ModelRenderer lTentacle00d;
    public ModelRenderer lTentacle00e;
    public ModelRenderer lTentacle00f;
    public ModelRenderer lTentacle01b;
    public ModelRenderer lTentacle01c;
    public ModelRenderer lTentacle01d;
    public ModelRenderer lTentacle01e;
    public ModelRenderer lTentacle01f;
    public ModelRenderer rTentacle00b;
    public ModelRenderer rTentacle00c;
    public ModelRenderer rTentacle00d;
    public ModelRenderer rTentacle00e;
    public ModelRenderer rTentacle00f;
    public ModelRenderer rTentacle01b;
    public ModelRenderer rTentacle01c;
    public ModelRenderer rTentacle01d;
    public ModelRenderer rTentacle01e;
    public ModelRenderer rTentacle01f;
    public ModelRenderer lTentacle02b;
    public ModelRenderer lTentacle02c;
    public ModelRenderer lTentacle02d;
    public ModelRenderer lTentacle02e;
    public ModelRenderer rTentacle02b;
    public ModelRenderer rTentacle02c;
    public ModelRenderer rTentacle02d;
    public ModelRenderer rTentacle02e;

    public ModelShubStatue() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.rTentacle02c = new ModelRenderer(this, 52, 10);
        this.rTentacle02c.mirror = true;
        this.rTentacle02c.setRotationPoint(0.0F, 0.0F, 1.6F);
        this.rTentacle02c.addBox(-1.01F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(rTentacle02c, -0.4363323129985824F, 0.0F, 0.0F);
        this.rEar = new ModelRenderer(this, 56, 24);
        this.rEar.mirror = true;
        this.rEar.setRotationPoint(-2.0F, -2.1F, 0.7F);
        this.rEar.addBox(-2.0F, -1.0F, -0.5F, 2, 2, 1, 0.0F);
        this.setRotateAngle(rEar, 0.5235987755982988F, 0.0F, -0.3141592653589793F);
        this.rTentacle00b = new ModelRenderer(this, 41, 12);
        this.rTentacle00b.mirror = true;
        this.rTentacle00b.setRotationPoint(0.0F, -0.4F, 2.5F);
        this.rTentacle00b.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(rTentacle00b, -0.40142572795869574F, 0.2617993877991494F, 0.0F);
        this.chest = new ModelRenderer(this, 0, 17);
        this.chest.setRotationPoint(0.0F, -4.3F, 0.0F);
        this.chest.addBox(-3.0F, -4.0F, -1.5F, 6, 5, 3, 0.0F);
        this.setRotateAngle(chest, -0.17453292519943295F, 0.0F, 0.0F);
        this.rTentacle01d = new ModelRenderer(this, 52, 10);
        this.rTentacle01d.mirror = true;
        this.rTentacle01d.setRotationPoint(0.0F, 0.0F, 1.6F);
        this.rTentacle01d.addBox(-1.01F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(rTentacle01d, -0.4363323129985824F, 0.0F, 0.0F);
        this.rTentacle00e = new ModelRenderer(this, 40, 17);
        this.rTentacle00e.mirror = true;
        this.rTentacle00e.setRotationPoint(0.0F, -0.1F, 3.5F);
        this.rTentacle00e.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(rTentacle00e, 0.5235987755982988F, 0.0F, 0.0F);
        this.lTentacle02b = new ModelRenderer(this, 41, 12);
        this.lTentacle02b.setRotationPoint(0.0F, 0.0F, 1.6F);
        this.lTentacle02b.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(lTentacle02b, -0.6981317007977318F, -0.3141592653589793F, 0.3141592653589793F);
        this.rTentacle02b = new ModelRenderer(this, 41, 12);
        this.rTentacle02b.mirror = true;
        this.rTentacle02b.setRotationPoint(0.0F, 0.0F, 1.6F);
        this.rTentacle02b.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(rTentacle02b, -0.6981317007977318F, 0.3141592653589793F, -0.3141592653589793F);
        this.body = new ModelRenderer(this, 19, 17);
        this.body.setRotationPoint(0.0F, 16.2F, 0.8F);
        this.body.addBox(-2.5F, -4.0F, -1.5F, 5, 4, 3, 0.0F);
        this.setRotateAngle(body, 0.4363323129985824F, 0.0F, 0.0F);
        this.rHorn01a = new ModelRenderer(this, 50, 0);
        this.rHorn01a.mirror = true;
        this.rHorn01a.setRotationPoint(0.2F, -1.5F, 0.11F);
        this.rHorn01a.addBox(-0.2F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rHorn01a, -0.10471975511965977F, 0.0F, -0.4363323129985824F);
        this.rTentacle03c = new ModelRenderer(this, 51, 17);
        this.rTentacle03c.mirror = true;
        this.rTentacle03c.setRotationPoint(0.0F, 0.0F, 3.3F);
        this.rTentacle03c.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rTentacle03c, 0.3490658503988659F, 0.0F, 0.0F);
        this.lTentacle00d = new ModelRenderer(this, 52, 10);
        this.lTentacle00d.setRotationPoint(0.0F, 0.0F, 1.6F);
        this.lTentacle00d.addBox(-0.909F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(lTentacle00d, -0.5759586531581287F, 0.0F, 0.0F);
        this.snout = new ModelRenderer(this, 45, 25);
        this.snout.setRotationPoint(0.0F, -1.0F, -1.9F);
        this.snout.addBox(-1.5F, -1.8F, -2.2F, 3, 3, 3, 0.0F);
        this.setRotateAngle(snout, 0.17453292519943295F, 0.0F, 0.0F);
        this.rTentacle02e = new ModelRenderer(this, 51, 17);
        this.rTentacle02e.mirror = true;
        this.rTentacle02e.setRotationPoint(0.0F, 0.0F, 2.8F);
        this.rTentacle02e.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rTentacle02e, 0.5235987755982988F, 0.0F, 0.0F);
        this.snoutSnope = new ModelRenderer(this, 45, 25);
        this.snoutSnope.setRotationPoint(0.0F, -2.9F, -1.9F);
        this.snoutSnope.addBox(-1.51F, -0.6F, -2.5F, 3, 1, 3, 0.0F);
        this.setRotateAngle(snoutSnope, 0.3490658503988659F, 0.0F, 0.0F);
        this.rHorn03 = new ModelRenderer(this, 55, 0);
        this.rHorn03.mirror = true;
        this.rHorn03.setRotationPoint(0.0F, -1.6F, 0.0F);
        this.rHorn03.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rHorn03, 0.0F, 0.0F, 0.2617993877991494F);
        this.lEar = new ModelRenderer(this, 56, 24);
        this.lEar.setRotationPoint(2.0F, -2.1F, 0.7F);
        this.lEar.addBox(0.0F, -1.0F, -0.5F, 2, 2, 1, 0.0F);
        this.setRotateAngle(lEar, 0.5235987755982988F, 0.0F, 0.3141592653589793F);
        this.rHorn00 = new ModelRenderer(this, 43, 0);
        this.rHorn00.mirror = true;
        this.rHorn00.setRotationPoint(-1.5F, -2.8F, 1.0F);
        this.rHorn00.addBox(-1.0F, -2.0F, -0.4F, 2, 2, 1, 0.0F);
        this.setRotateAngle(rHorn00, -0.3141592653589793F, 0.2617993877991494F, -0.8028514559173915F);
        this.lTentacle00e = new ModelRenderer(this, 40, 17);
        this.lTentacle00e.setRotationPoint(0.0F, -0.1F, 3.5F);
        this.lTentacle00e.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(lTentacle00e, 0.5235987755982988F, 0.0F, 0.0F);
        this.rArm01 = new ModelRenderer(this, 33, 0);
        this.rArm01.mirror = true;
        this.rArm01.setRotationPoint(-2.5F, -3.5F, 0.4F);
        this.rArm01.addBox(-1.8F, -0.5F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(rArm01, -0.4363323129985824F, 0.0F, -0.17453292519943295F);
        this.lHorn02a = new ModelRenderer(this, 55, 0);
        this.lHorn02a.setRotationPoint(0.1F, -1.9F, 0.11F);
        this.lHorn02a.addBox(-0.7F, -1.7F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(lHorn02a, -0.10471975511965977F, 0.0F, 0.4363323129985824F);
        this.lTentacle03b = new ModelRenderer(this, 52, 10);
        this.lTentacle03b.setRotationPoint(0.0F, 0.0F, 1.1F);
        this.lTentacle03b.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(lTentacle03b, -0.4363323129985824F, -0.5235987755982988F, 0.0F);
        this.lTentacle00a = new ModelRenderer(this, 41, 4);
        this.lTentacle00a.setRotationPoint(-1.5F, -1.5F, -1.1F);
        this.lTentacle00a.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 4, 0.0F);
        this.setRotateAngle(lTentacle00a, -0.45378560551852565F, -0.2617993877991494F, 0.08726646259971647F);
        this.lArm02 = new ModelRenderer(this, 32, 6);
        this.lArm02.setRotationPoint(1.3F, 3.3F, 0.0F);
        this.lArm02.addBox(-1.2F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(lArm02, -0.22689280275926282F, 0.0F, 0.0F);
        this.rHorn01b = new ModelRenderer(this, 50, 0);
        this.rHorn01b.mirror = true;
        this.rHorn01b.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rHorn01b.addBox(-0.8F, -2.0F, -0.49F, 1, 2, 1, 0.0F);
        this.lTentacle00f = new ModelRenderer(this, 51, 17);
        this.lTentacle00f.setRotationPoint(0.0F, 0.0F, 2.8F);
        this.lTentacle00f.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lTentacle00f, 0.5235987755982988F, 0.0F, 0.0F);
        this.lHorn02b = new ModelRenderer(this, 55, 0);
        this.lHorn02b.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lHorn02b.addBox(-0.4F, -1.7F, -0.49F, 1, 2, 1, 0.0F);
        this.lTentacle01e = new ModelRenderer(this, 40, 17);
        this.lTentacle01e.setRotationPoint(0.0F, -0.1F, 3.5F);
        this.lTentacle01e.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(lTentacle01e, 0.6981317007977318F, 0.0F, 0.0F);
        this.lHorn03 = new ModelRenderer(this, 55, 0);
        this.lHorn03.setRotationPoint(0.0F, -1.6F, 0.0F);
        this.lHorn03.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(lHorn03, 0.0F, 0.0F, -0.2617993877991494F);
        this.head = new ModelRenderer(this, 25, 24);
        this.head.setRotationPoint(0.0F, -4.4F, 0.0F);
        this.head.addBox(-2.5F, -3.6F, -2.0F, 5, 4, 4, 0.0F);
        this.rTentacle01a = new ModelRenderer(this, 41, 4);
        this.rTentacle01a.mirror = true;
        this.rTentacle01a.setRotationPoint(0.9F, -1.8F, 0.7F);
        this.rTentacle01a.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 4, 0.0F);
        this.setRotateAngle(rTentacle01a, -0.45378560551852565F, 0.13962634015954636F, -0.08726646259971647F);
        this.rTentacle01f = new ModelRenderer(this, 51, 17);
        this.rTentacle01f.mirror = true;
        this.rTentacle01f.setRotationPoint(0.0F, 0.0F, 2.8F);
        this.rTentacle01f.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rTentacle01f, 0.40142572795869574F, 0.0F, 0.0F);
        this.lHoof01 = new ModelRenderer(this, 32, 13);
        this.lHoof01.setRotationPoint(0.4F, 3.95F, -0.6F);
        this.lHoof01.addBox(-0.5F, -0.5F, -0.8F, 1, 2, 2, 0.0F);
        this.setRotateAngle(lHoof01, 0.3490658503988659F, -0.03490658503988659F, -0.13962634015954636F);
        this.rHorn02a = new ModelRenderer(this, 55, 0);
        this.rHorn02a.mirror = true;
        this.rHorn02a.setRotationPoint(0.0F, -1.9F, 0.11F);
        this.rHorn02a.addBox(-0.4F, -1.7F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rHorn02a, -0.10471975511965977F, 0.0F, -0.4363323129985824F);
        this.lTentacle01d = new ModelRenderer(this, 52, 10);
        this.lTentacle01d.setRotationPoint(0.0F, 0.0F, 1.6F);
        this.lTentacle01d.addBox(-0.99F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(lTentacle01d, -0.4363323129985824F, 0.0F, 0.0F);
        this.rTentacle03b = new ModelRenderer(this, 52, 10);
        this.rTentacle03b.mirror = true;
        this.rTentacle03b.setRotationPoint(0.0F, 0.0F, 1.1F);
        this.rTentacle03b.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(rTentacle03b, -0.4363323129985824F, 0.5235987755982988F, 0.0F);
        this.rTentacle01b = new ModelRenderer(this, 41, 12);
        this.rTentacle01b.mirror = true;
        this.rTentacle01b.setRotationPoint(0.0F, -0.4F, 2.5F);
        this.rTentacle01b.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(rTentacle01b, -0.5235987755982988F, -0.13962634015954636F, 0.0F);
        this.lTentacle03a = new ModelRenderer(this, 41, 12);
        this.lTentacle03a.setRotationPoint(-1.3F, -2.7F, 1.6F);
        this.lTentacle03a.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(lTentacle03a, -0.6108652381980153F, -0.6108652381980153F, 0.5235987755982988F);
        this.lTentacle00c = new ModelRenderer(this, 41, 12);
        this.lTentacle00c.setRotationPoint(0.0F, 0.0F, 1.6F);
        this.lTentacle00c.addBox(-1.01F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(lTentacle00c, -0.4363323129985824F, 0.0F, 0.0F);
        this.rTentacle00c = new ModelRenderer(this, 41, 12);
        this.rTentacle00c.mirror = true;
        this.rTentacle00c.setRotationPoint(0.0F, 0.0F, 1.6F);
        this.rTentacle00c.addBox(-0.99F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(rTentacle00c, -0.4363323129985824F, 0.0F, 0.0F);
        this.rHoof02 = new ModelRenderer(this, 32, 13);
        this.rHoof02.mirror = true;
        this.rHoof02.setRotationPoint(0.8F, 4.1F, -0.6F);
        this.rHoof02.addBox(-0.5F, -0.5F, -0.8F, 1, 2, 2, 0.0F);
        this.setRotateAngle(rHoof02, 0.3490658503988659F, -0.03490658503988659F, 0.13962634015954636F);
        this.lTentacle01c = new ModelRenderer(this, 41, 12);
        this.lTentacle01c.setRotationPoint(0.0F, 0.0F, 1.6F);
        this.lTentacle01c.addBox(-1.01F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(lTentacle01c, -0.5235987755982988F, 0.0F, 0.0F);
        this.lTentacle02c = new ModelRenderer(this, 52, 10);
        this.lTentacle02c.setRotationPoint(0.0F, 0.0F, 1.6F);
        this.lTentacle02c.addBox(-1.01F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(lTentacle02c, -0.4363323129985824F, 0.0F, 0.0F);
        this.rArm02 = new ModelRenderer(this, 32, 6);
        this.rArm02.mirror = true;
        this.rArm02.setRotationPoint(-1.0F, 3.3F, 0.0F);
        this.rArm02.addBox(-0.7F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(rArm02, -0.22689280275926282F, 0.0F, 0.0F);
        this.lTentacle02e = new ModelRenderer(this, 51, 17);
        this.lTentacle02e.setRotationPoint(0.0F, 0.0F, 2.8F);
        this.lTentacle02e.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lTentacle02e, 0.5235987755982988F, 0.0F, 0.0F);
        this.lTentacle01f = new ModelRenderer(this, 51, 17);
        this.lTentacle01f.setRotationPoint(0.0F, 0.0F, 2.8F);
        this.lTentacle01f.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lTentacle01f, 0.40142572795869574F, 0.0F, 0.0F);
        this.plinth = new ModelRenderer(this, 0, 0);
        this.plinth.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.plinth.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.lHorn00 = new ModelRenderer(this, 43, 0);
        this.lHorn00.setRotationPoint(1.5F, -2.8F, 1.0F);
        this.lHorn00.addBox(-1.0F, -2.0F, -0.4F, 2, 2, 1, 0.0F);
        this.setRotateAngle(lHorn00, -0.3141592653589793F, -0.2617993877991494F, 0.8028514559173915F);
        this.rTentacle00a = new ModelRenderer(this, 41, 4);
        this.rTentacle00a.mirror = true;
        this.rTentacle00a.setRotationPoint(1.5F, -1.5F, -1.1F);
        this.rTentacle00a.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 4, 0.0F);
        this.setRotateAngle(rTentacle00a, -0.45378560551852565F, 0.2617993877991494F, -0.08726646259971647F);
        this.lHoof02 = new ModelRenderer(this, 32, 13);
        this.lHoof02.setRotationPoint(-0.7F, 4.1F, -0.6F);
        this.lHoof02.addBox(-0.5F, -0.5F, -0.8F, 1, 2, 2, 0.0F);
        this.setRotateAngle(lHoof02, 0.3490658503988659F, 0.03490658503988659F, -0.13962634015954636F);
        this.rHorn02b = new ModelRenderer(this, 55, 0);
        this.rHorn02b.mirror = true;
        this.rHorn02b.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rHorn02b.addBox(-0.7F, -1.7F, -0.49F, 1, 2, 1, 0.0F);
        this.lTentacle03c = new ModelRenderer(this, 51, 17);
        this.lTentacle03c.setRotationPoint(0.0F, 0.0F, 3.3F);
        this.lTentacle03c.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lTentacle03c, 0.3490658503988659F, 0.0F, 0.0F);
        this.rTentacle02a = new ModelRenderer(this, 41, 12);
        this.rTentacle02a.mirror = true;
        this.rTentacle02a.setRotationPoint(1.3F, -5.0F, 0.6F);
        this.rTentacle02a.addBox(-1.0F, -1.0F, 0.4F, 2, 2, 2, 0.0F);
        this.setRotateAngle(rTentacle02a, -0.22689280275926282F, 0.9599310885968813F, 0.0F);
        this.rHoof01 = new ModelRenderer(this, 32, 13);
        this.rHoof01.mirror = true;
        this.rHoof01.setRotationPoint(-0.3F, 3.95F, -0.6F);
        this.rHoof01.addBox(-0.5F, -0.5F, -0.8F, 1, 2, 2, 0.0F);
        this.setRotateAngle(rHoof01, 0.3490658503988659F, 0.03490658503988659F, 0.13962634015954636F);
        this.rTentacle03d = new ModelRenderer(this, 51, 17);
        this.rTentacle03d.mirror = true;
        this.rTentacle03d.setRotationPoint(0.0F, 0.0F, 2.8F);
        this.rTentacle03d.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rTentacle03d, 0.5235987755982988F, 0.0F, 0.0F);
        this.lTentacle01b = new ModelRenderer(this, 41, 12);
        this.lTentacle01b.setRotationPoint(0.0F, -0.4F, 2.5F);
        this.lTentacle01b.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(lTentacle01b, -0.5235987755982988F, 0.13962634015954636F, 0.0F);
        this.rTentacle00f = new ModelRenderer(this, 51, 17);
        this.rTentacle00f.mirror = true;
        this.rTentacle00f.setRotationPoint(0.0F, 0.0F, 2.8F);
        this.rTentacle00f.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rTentacle00f, 0.5235987755982988F, 0.0F, 0.0F);
        this.rTentacle03a = new ModelRenderer(this, 41, 12);
        this.rTentacle03a.mirror = true;
        this.rTentacle03a.setRotationPoint(1.3F, -2.7F, 1.6F);
        this.rTentacle03a.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(rTentacle03a, -0.6108652381980153F, 0.6108652381980153F, -0.5235987755982988F);
        this.rTentacle01c = new ModelRenderer(this, 41, 12);
        this.rTentacle01c.mirror = true;
        this.rTentacle01c.setRotationPoint(0.0F, 0.0F, 1.6F);
        this.rTentacle01c.addBox(-0.99F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(rTentacle01c, -0.5235987755982988F, 0.0F, 0.0F);
        this.lTentacle01a = new ModelRenderer(this, 41, 4);
        this.lTentacle01a.setRotationPoint(-0.9F, -1.8F, 0.7F);
        this.lTentacle01a.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 4, 0.0F);
        this.setRotateAngle(lTentacle01a, -0.45378560551852565F, -0.13962634015954636F, 0.08726646259971647F);
        this.rTentacle02d = new ModelRenderer(this, 40, 17);
        this.rTentacle02d.mirror = true;
        this.rTentacle02d.setRotationPoint(0.0F, 0.0F, 3.3F);
        this.rTentacle02d.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(rTentacle02d, 0.6981317007977318F, 0.0F, 0.0F);
        this.lTentacle02d = new ModelRenderer(this, 40, 17);
        this.lTentacle02d.setRotationPoint(0.0F, 0.0F, 3.3F);
        this.lTentacle02d.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(lTentacle02d, 0.6981317007977318F, 0.0F, 0.0F);
        this.rTentacle00d = new ModelRenderer(this, 52, 10);
        this.rTentacle00d.mirror = true;
        this.rTentacle00d.setRotationPoint(0.0F, 0.0F, 1.6F);
        this.rTentacle00d.addBox(-1.01F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(rTentacle00d, -0.5759586531581287F, 0.0F, 0.0F);
        this.lHorn01a = new ModelRenderer(this, 50, 0);
        this.lHorn01a.setRotationPoint(-0.1F, -1.5F, 0.11F);
        this.lHorn01a.addBox(-0.8F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(lHorn01a, -0.10471975511965977F, 0.0F, 0.4363323129985824F);
        this.lArm01 = new ModelRenderer(this, 33, 0);
        this.lArm01.setRotationPoint(2.1F, -3.5F, 0.4F);
        this.lArm01.addBox(0.2F, -0.5F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(lArm01, -0.4363323129985824F, 0.0F, 0.17453292519943295F);
        this.lTentacle00b = new ModelRenderer(this, 41, 12);
        this.lTentacle00b.setRotationPoint(0.0F, -0.4F, 2.5F);
        this.lTentacle00b.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(lTentacle00b, -0.40142572795869574F, -0.2617993877991494F, 0.0F);
        this.lTentacle03d = new ModelRenderer(this, 51, 17);
        this.lTentacle03d.setRotationPoint(0.0F, 0.0F, 2.8F);
        this.lTentacle03d.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lTentacle03d, 0.5235987755982988F, 0.0F, 0.0F);
        this.lTentacle02a = new ModelRenderer(this, 41, 12);
        this.lTentacle02a.setRotationPoint(-1.3F, -5.0F, 0.6F);
        this.lTentacle02a.addBox(-1.0F, -1.0F, 0.4F, 2, 2, 2, 0.0F);
        this.setRotateAngle(lTentacle02a, -0.22689280275926282F, -0.9599310885968813F, 0.0F);
        this.boobs = new ModelRenderer(this, 0, 28);
        this.boobs.setRotationPoint(0.0F, -2.9F, -0.7F);
        this.boobs.addBox(-2.5F, 0.0F, -1.0F, 5, 2, 2, 0.0F);
        this.setRotateAngle(boobs, -1.0471975511965976F, 0.0F, 0.0F);
        this.rTentacle01e = new ModelRenderer(this, 40, 17);
        this.rTentacle01e.mirror = true;
        this.rTentacle01e.setRotationPoint(0.0F, -0.1F, 3.5F);
        this.rTentacle01e.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(rTentacle01e, 0.6981317007977318F, 0.0F, 0.0F);
        this.lHorn01b = new ModelRenderer(this, 50, 0);
        this.lHorn01b.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lHorn01b.addBox(-0.3F, -2.0F, -0.49F, 1, 2, 1, 0.0F);
        this.rTentacle02b.addChild(this.rTentacle02c);
        this.head.addChild(this.rEar);
        this.rTentacle00a.addChild(this.rTentacle00b);
        this.body.addChild(this.chest);
        this.rTentacle01c.addChild(this.rTentacle01d);
        this.rTentacle00d.addChild(this.rTentacle00e);
        this.lTentacle02a.addChild(this.lTentacle02b);
        this.rTentacle02a.addChild(this.rTentacle02b);
        this.rHorn00.addChild(this.rHorn01a);
        this.rTentacle03b.addChild(this.rTentacle03c);
        this.lTentacle00c.addChild(this.lTentacle00d);
        this.head.addChild(this.snout);
        this.rTentacle02d.addChild(this.rTentacle02e);
        this.head.addChild(this.snoutSnope);
        this.rHorn02a.addChild(this.rHorn03);
        this.head.addChild(this.lEar);
        this.head.addChild(this.rHorn00);
        this.lTentacle00d.addChild(this.lTentacle00e);
        this.chest.addChild(this.rArm01);
        this.lHorn01a.addChild(this.lHorn02a);
        this.lTentacle03a.addChild(this.lTentacle03b);
        this.body.addChild(this.lTentacle00a);
        this.lArm01.addChild(this.lArm02);
        this.rHorn01a.addChild(this.rHorn01b);
        this.lTentacle00e.addChild(this.lTentacle00f);
        this.lHorn02a.addChild(this.lHorn02b);
        this.lTentacle01d.addChild(this.lTentacle01e);
        this.lHorn02a.addChild(this.lHorn03);
        this.chest.addChild(this.head);
        this.body.addChild(this.rTentacle01a);
        this.rTentacle01e.addChild(this.rTentacle01f);
        this.lArm02.addChild(this.lHoof01);
        this.rHorn01a.addChild(this.rHorn02a);
        this.lTentacle01c.addChild(this.lTentacle01d);
        this.rTentacle03a.addChild(this.rTentacle03b);
        this.rTentacle01a.addChild(this.rTentacle01b);
        this.chest.addChild(this.lTentacle03a);
        this.lTentacle00b.addChild(this.lTentacle00c);
        this.rTentacle00b.addChild(this.rTentacle00c);
        this.rArm02.addChild(this.rHoof02);
        this.lTentacle01b.addChild(this.lTentacle01c);
        this.lTentacle02b.addChild(this.lTentacle02c);
        this.rArm01.addChild(this.rArm02);
        this.lTentacle02d.addChild(this.lTentacle02e);
        this.lTentacle01e.addChild(this.lTentacle01f);
        this.head.addChild(this.lHorn00);
        this.body.addChild(this.rTentacle00a);
        this.lArm02.addChild(this.lHoof02);
        this.rHorn02a.addChild(this.rHorn02b);
        this.lTentacle03b.addChild(this.lTentacle03c);
        this.body.addChild(this.rTentacle02a);
        this.rArm02.addChild(this.rHoof01);
        this.rTentacle03c.addChild(this.rTentacle03d);
        this.lTentacle01a.addChild(this.lTentacle01b);
        this.rTentacle00e.addChild(this.rTentacle00f);
        this.chest.addChild(this.rTentacle03a);
        this.rTentacle01b.addChild(this.rTentacle01c);
        this.body.addChild(this.lTentacle01a);
        this.rTentacle02c.addChild(this.rTentacle02d);
        this.lTentacle02c.addChild(this.lTentacle02d);
        this.rTentacle00c.addChild(this.rTentacle00d);
        this.lHorn00.addChild(this.lHorn01a);
        this.chest.addChild(this.lArm01);
        this.lTentacle00a.addChild(this.lTentacle00b);
        this.lTentacle03c.addChild(this.lTentacle03d);
        this.body.addChild(this.lTentacle02a);
        this.chest.addChild(this.boobs);
        this.rTentacle01d.addChild(this.rTentacle01e);
        this.lHorn01a.addChild(this.lHorn01b);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
        this.plinth.render(f5);
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
