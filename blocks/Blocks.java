package coderdojo.blocks;

import net.minecraft.block.Block;
import coderdojo.items.ItemLampBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {

	public static Block lampOff;
	public static Block lampOn;

	public static void init() {
		lampOff = new BlockLamp(BlockInfo.LAMP_ID);
//		lampOn = new BlockLamp(BlockInfo.LAMP_ID+1, 1);
		GameRegistry.registerBlock(lampOff, ItemLampBlock.class, BlockInfo.LAMP_KEY);
//		GameRegistry.registerBlock(lampOn, ItemLampBlock.class, BlockInfo.LAMP_KEY);
	}

	public static void addNames() {
		LanguageRegistry.addName(lampOff, BlockInfo.LAMP_NAME);
//		LanguageRegistry.addName(lampOn, BlockInfo.LAMP_NAME);
	}
}