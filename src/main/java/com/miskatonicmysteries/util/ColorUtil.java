package com.miskatonicmysteries.util;

import net.minecraft.util.math.MathHelper;

import java.awt.*;

public class ColorUtil {
    public static Color blend(Color c0, Color c1, double weightOne, double weightTwo) {
        double r = MathHelper.clamp(weightOne * c0.getRed() + weightTwo * c1.getRed(), 0, 255);
        double g = MathHelper.clamp(weightOne * c0.getGreen() + weightTwo * c1.getGreen(), 0, 255);
        double b = MathHelper.clamp(weightOne * c0.getBlue() + weightTwo * c1.getBlue(), 0, 255);
        double a = MathHelper.clamp(Math.max(c0.getAlpha(), c1.getAlpha()), 0, 255);

        return new Color((int) r, (int) g, (int) b, (int) a);
    }
}
