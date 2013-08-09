package coderdojo.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLamp extends Block {
	@SideOnly(Side.CLIENT)
	private Icon lampOn;
	@SideOnly(Side.CLIENT)
	private Icon lampOff;
	
	/**
	 *	Object constructor for the block.
	 *	@param id 					Block id
	 */
	public BlockLamp(int id) {
		super(id, Material.redstoneLight);

		setUnlocalizedName(BlockInfo.LAMP_KEY);
		setCreativeTab(CreativeTabs.tabRedstone);
	}

	/**
	 *	Adds the texture into the game from the assets folder.
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		lampOn = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.LAMP_ICON_ON);
		lampOff = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.LAMP_ICON_OFF);
	}

	/**
	 *	Sets the texture for the block based on its Metadata.
	 * 	@param side 				Side of the block to texture.
	 * 	@param meta 				Metadata of the block.
	 */
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
	 *	Called when this block is placed in the world.
 	 * @param World 				World in which the block is activated.
	 * @param x, y, z 				Co-ordinates of the block.
	 */
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (!world.isRemote) {
			if (world.isBlockIndirectlyGettingPowered(x,y,z)) {
				world.setBlockMetadataWithNotify(x, y, z, 1, 3);
				world.scheduleBlockUpdate(x, y, z, BlockInfo.LAMP_ID, 4);
			}
		}
	}


	/**
	 *	Called when a block touching this block changes
  	 * @param World 				World in which the block is activated.
	 * @param x, y, z 				Co-ordinates of the block.
	 * @param id 					Block id of the neighboring block
	 */
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

	/**
	 *	Sets the light value of the block.
 	 * @param World 				World in which the block is activated.
	 * @param x, y, z 				Co-ordinates of the block.
	 */
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
	 * @param World 				World in which the block is activated.
	 * @param x, y, z 				Co-ordinates of the block.
	 * @param player 				Player who right clicked the block.
	 */
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (!world.isRemote && !world.isBlockIndirectlyGettingPowered(x, y, z))
		{
			world.setBlock(z, y, z, BlockInfo.LAMP_ID, 1, 2);
		}
	}

	/**
	 * Called upon block activation (right click on the block.)
	 * @param World 				World in which the block is activated.
	 * @param x, y, z 				Co-ordinates of the block.
	 * @param player 				Player who right clicked the block.
	 * @param side 					Side of the block clicked.
	 * @param hitX, hitY, hitZ 		Where on the block was clicked.
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemDye) {
				System.out.println("Item: " + ItemDye.dyeColorNames[player.getHeldItem().getItemDamage()] + " dye.");
			}
		}
		return true;
	}
}
