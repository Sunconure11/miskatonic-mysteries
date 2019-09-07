package moriyashiine.acme.common.potion;

import moriyashiine.acme.ACME;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModPotion extends Potion{
    protected ModPotion(String name, boolean isNegative, int color) {
        super(isNegative, color);
        setRegistryName(new ResourceLocation(ACME.MODID, name));
        setPotionName(getRegistryName().toString().replace(":", "."));
    }

    @Override
    public void affectEntity(Entity source, Entity indirectSource, EntityLivingBase living, int amplifier, double health) {

    }

    public boolean onImpact(World world, BlockPos pos, int amplifier) {
        return false;
    }
}
