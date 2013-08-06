package coderdojo.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/*
 * Hi Lisa!!!
 */
public class BlockLamp extends Block {
	@SideOnly(Side.CLIENT)
	private Icon lampOn;
	@SideOnly(Side.CLIENT)
	private Icon lampOff;

	private boolean isActivated;

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

	/**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
	@Override
    public void onBlockAdded(World world, int x, int y, int z) {
		if (!world.isRemote) {
			if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
				world.setBlockMetadataWithNotify(x, y, z, 1, 3);
			}
		}
    }

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockID) {
		if (!world.isRemote) {
			if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
				world.setBlockMetadataWithNotify(x, y, z, 1, 3);
			}
			else {
				world.setBlockMetadataWithNotify(z, y, z, 0, 3);
			}
		}	
	}
}
