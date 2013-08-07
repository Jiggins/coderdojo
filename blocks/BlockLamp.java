package coderdojo.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/*
 * Hi Lisa!
 */
public class BlockLamp extends Block {
	@SideOnly(Side.CLIENT)
	private Icon lampOn;
	@SideOnly(Side.CLIENT)
	private Icon lampOff;
	
	public BlockLamp(int id) {
		super(id, Material.redstoneLight);

		setUnlocalizedName(BlockInfo.LAMP_KEY);
		setCreativeTab(CreativeTabs.tabRedstone);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		lampOn = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.LAMP_ICON_ON);
		lampOff = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.LAMP_ICON_OFF);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		switch(meta) {
			case 0: return lampOff;
			case 1: return lampOn;
		}
		return null;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (!world.isRemote) {
			if (world.isBlockIndirectlyGettingPowered(x,y,z)) {
				world.setBlockMetadataWithNotify(x, y, z, 1, 3);
				world.scheduleBlockUpdate(x, y, z, BlockInfo.LAMP_ID, 4);
			}
		}
	}


	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int id) {
		if (!world.isRemote) {
			if (world.isBlockIndirectlyGettingPowered(x,y,z)) {
				world.setBlockMetadataWithNotify(x, y, z, 1, 3);
				world.scheduleBlockUpdate(x, y, z, BlockInfo.LAMP_ID, 4);
			}
			else {
				world.setBlockMetadataWithNotify(x, y, z, 0, 3);
				world.scheduleBlockUpdate(x, y, z, BlockInfo.LAMP_ID, 4);
			}
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		
		switch(meta) {
			case 0: return 0;
			case 1: return 15;
		}
		return meta;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (!world.isRemote && !world.isBlockIndirectlyGettingPowered(x, y, z))
		{
			world.setBlock(z, y, z, BlockInfo.LAMP_ID, 1, 2);
		}
	}
}
