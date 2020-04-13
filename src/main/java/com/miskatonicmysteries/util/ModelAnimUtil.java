package com.miskatonicmysteries.util;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ModelAnimUtil {
    public static void rollInTentacle(ModelRenderer[] tentacle, float progress){
        for (int i = 0; i < tentacle.length; i++) {
            tentacle[i].rotateAngleX = tentacle[i].rotateAngleX + MathHelper.cos(progress);
        }
    }


    public static void waveTentacle(ModelRenderer[] tentacle, float strengthX, float strengthZ, float progress, boolean reset) {
        if (reset)
            progress = (0.5F - Math.abs(0.5F - progress)) * 2F;
        for (int i = 0; i < tentacle.length - 1; i++) { //-0.5 )) + 1
            tentacle[i + 1].rotateAngleX = tentacle[i + 1].rotateAngleX + MathHelper.sin(progress * i * strengthX) * (i % 2 == 0 ? -1 : 1);
            tentacle[i + 1].rotateAngleZ = tentacle[i + 1].rotateAngleZ + MathHelper.sin(progress * i * strengthZ) * (i % 2 == 0 ? -1 : 1);
        }
    }

    public static float calculateTickSequenceProgress(int tickSequence, float ageInTicks){
        return (ageInTicks % tickSequence) / (float) tickSequence;
    }
}
