package coderdojo.blocks;

import net.minecraft.block.Block;
import coderdojo.items.ItemLampBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {

	public static Block lamp;

	/**
	 *	Initialises all the blocks in our mod
	 */
	public static void init() {
		lamp = new BlockLamp(BlockInfo.LAMP_ID);
		GameRegistry.registerBlock(lamp, ItemLampBlock.class, BlockInfo.LAMP_KEY);
	}

	/**
	 *	Adds names to all of our blocks in the mod.
	 */
	public static void addNames() {
		LanguageRegistry.addName(lamp, BlockInfo.LAMP_NAME);

	}
}