package coderdojo.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Class to create the CoderDojo Block.
 * Class extends Block from the Minecraft source code.
 * This allows us to use all the methods from the Block class. 
 */
public class BlockCoderDojo extends Block {
	//Used later
	private Icon coderDojoIconSides;
	private Icon coderDojoIconTop;

	/**
	 * Constructor for the Block
	 * @param id: -	The ID number for each of the block
	 * 				ID numbers for the blocks in the game can be found here:
	 * 				http://www.minecraftwiki.net/wiki/Data_values#Block_IDs
	 */
	public BlockCoderDojo(int id) {
		/**
		 * Super creates the CoderDojo block using the Block class
		 * @param id		- as before
		 * @param Material	- The material of the block
		 * 					- Determines the sound the block makes when walked on
		 */
		super(id, Material.rock);
		
		/*
		 * Sets the creative tab the block appears in the game's creative menu.
		 * The block will not show up at all if this is not here.
		 */
		setCreativeTab(CreativeTabs.tabBlock);
		
		//Sets the number of hits required to break the block
		setHardness(3);
		
		/*
		 * Sets the amount of light emitted by a block from 0.0f to 1.0f (converts internally to 0-15). 
		 */
		setLightValue(1.0f);
	}

	/**
	 *	Adds the texture into the game from the assets folder.
	 *	Assets folder should look like:
	 *		\assets
	 *			\coderdojo
	 *				\textures
	 *					\blocks
	 *						| coderDojoIconTop.png
	 *						| coderDojoIconSides.png
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		coderDojoIconSides = register.registerIcon("coderdojo:coderDojoIconSides");
		coderDojoIconTop = register.registerIcon("coderdojo:coderDojoIconTop");
	}

	/**
     * Sets the icon for each side of the block.
     * Icons are registered in the registerIcons(IconRegister) method above
     * and declared private in the class.
     * 
     * @param side		| The side of the block ranging from 0-5
     * 					| If you wanted to place a different texture on the top of the block you would use
     * 					| if (side == 1) return topIcon else return coderDojoIcon;
     * 					| 0: Bottom
     * 					| 1: Top
     * 					| 2: North
     * 					| 3: South
     * 					| 4: West
     * 					| 5: East
     * 
     * @param metadata	| Will be explained week 3.
     * 					| No need to worry about it now.
     */
	@Override
	public Icon getIcon(int side, int meta) {
		//Top of the block
		if (side == 0) {
			return coderDojoIconTop;
		}
		//Bottom of the block
		if (side == 1) {
			return coderDojoIconTop;
		}
		//everything else
		return coderDojoIconSides;
	}
}