package coderdojo.blocks;

import net.minecraft.block.Block;
import coderdojo.items.ItemLampBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {

	public static Block lamp;

	public static void init() {
		lamp = new BlockLamp(BlockInfo.LAMP_ID);
		GameRegistry.registerBlock(lamp, ItemLampBlock.class, BlockInfo.LAMP_KEY);
	}

	public static void addNames() {
		LanguageRegistry.addName(lamp, BlockInfo.LAMP_NAME);
	}
}