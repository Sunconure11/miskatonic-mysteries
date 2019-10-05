package com.miskatonicmysteries.client.render.entity;

import com.miskatonicmysteries.client.model.entity.ModelGoatBlessing;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class RenderGoatLegs extends RenderPlayer {
    public RenderGoatLegs(RenderManager rendermanagerIn, ModelGoatBlessing modelbaseIn, boolean smolArms) {
        super(rendermanagerIn, smolArms);
        this.mainModel = modelbaseIn;
        this.layerRenderers.clear();
        this.addLayer(new LayerGoatArmor(this));
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerArrow(this));
        this.addLayer(new LayerDeadmau5Head(this));
        this.addLayer(new LayerCape(this));
        this.addLayer(new LayerCustomHead(this.getMainModel().bipedHead));
        this.addLayer(new LayerElytra(this));
        this.addLayer(new LayerEntityOnShoulder(renderManager));
    }

    public static class LayerGoatArmor extends LayerBipedArmor{ //possibly, this will adapt the armor to the goat feet instead of making it invisible
        public LayerGoatArmor(RenderLivingBase<?> rendererIn) {
            super(rendererIn);
        }

        @Override
        protected ModelBiped getArmorModelHook(EntityLivingBase entity, ItemStack itemStack, EntityEquipmentSlot slot, ModelBiped model) {
            return slot == EntityEquipmentSlot.LEGS || slot == EntityEquipmentSlot.FEET ?  model : super.getArmorModelHook(entity, itemStack, slot, model);
        }

        @Override
        protected void setModelSlotVisible(ModelBiped p_188359_1_, EntityEquipmentSlot slotIn) {
            super.setModelSlotVisible(p_188359_1_, slotIn);
            switch (slotIn){
                case LEGS:
                    p_188359_1_.bipedBody.showModel = false;
                    p_188359_1_.bipedRightLeg.showModel = false;
                    p_188359_1_.bipedLeftLeg.showModel = false;
                    break;
                case FEET:
                    p_188359_1_.bipedRightLeg.showModel = false;
                    p_188359_1_.bipedLeftLeg.showModel = false;
            }
        }
    }
}
