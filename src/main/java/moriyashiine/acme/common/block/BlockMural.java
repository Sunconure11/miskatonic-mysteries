package moriyashiine.acme.common.block;

import moriyashiine.acme.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMural extends Block {
	public Item item;
	
	public BlockMural(Material mat) {
		super(mat);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ) {
		if (player.getHeldItem(hand).getItem() instanceof ItemWritableBook) {
			if (!world.isRemote) Util.giveAndConsumeItem(player, hand, new ItemStack(item));
			return true;
		}
		return super.onBlockActivated(world, pos, state, player, hand, face, hitX, hitY, hitZ);
	}
}