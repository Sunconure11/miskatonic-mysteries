package com.miskatonicmysteries.client.render.util;

import com.miskatonicmysteries.client.render.models.ModelTestBlessing;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;

public class RenderGoatLegs extends RenderPlayer {
    public RenderGoatLegs(RenderManager rendermanagerIn, ModelTestBlessing modelbaseIn, boolean smolArms) {
        super(rendermanagerIn, smolArms);
        this.mainModel = modelbaseIn;
    }
}
