package coderdojo.config;

import java.io.File;

import net.minecraftforge.common.Configuration;
import coderdojo.blocks.BlockInfo;

public class ConfigHandler {
	public static void init(File file) {
		Configuration config = new Configuration(file);
		
		config.load();
		
		BlockInfo.LAMP_ID = config.getBlock(BlockInfo.LAMP_KEY, BlockInfo.LAMP_DEFAULT_ID).getInt();

		config.save();
	}
}