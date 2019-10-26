package com.miskatonicmysteries.util;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ModelAnimUtil {
    public static void rollInTentacle(ModelRenderer[] tentacle, float progress){
        for (int i = 0; i < tentacle.length; i++) {
            tentacle[i].rotateAngleX += MathHelper.cos(progress);
        }
    }
}
