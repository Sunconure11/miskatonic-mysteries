package moriyashiine.acme.common.potion;

import com.miskatonicmysteries.MiskatonicMysteries;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModPotion extends Potion{
    protected ModPotion(String name, boolean isNegative, int color) {
        super(isNegative, color);
        setRegistryName(new ResourceLocation(MiskatonicMysteries.MODID, name));
        setPotionName(getRegistryName().toString().replace(":", "."));
    }
    public boolean hasEffect(EntityLivingBase entity) {
        return hasEffect(entity, this);
    } //look at Potion and search for Nausea to imitate effects somehow

    public boolean hasEffect(EntityLivingBase entity, Potion potion) {
        return entity.getActivePotionEffect(potion) != null;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        render(x + 3, y + 3, alpha);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        render(x + 6, y + 7, 1);
    }

    @SideOnly(Side.CLIENT)
    private void render(int x, int y, float alpha) {
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MiskatonicMysteries.MODID, "textures/gui/effect/" + getName().replace(MiskatonicMysteries.MODID + ".", "") +".png"));
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buf = tessellator.getBuffer();
        buf.begin(7, DefaultVertexFormats.POSITION_TEX);
        GlStateManager.color(1, 1, 1, alpha);

        buf.pos(x, y + 18, 0).tex(0, 1).endVertex();
        buf.pos(x + 18, y + 18, 0).tex(1, 1).endVertex();
        buf.pos(x + 18, y, 0).tex(1, 0).endVertex();
        buf.pos(x, y, 0).tex(0, 0).endVertex();

        tessellator.draw();
    }
}
