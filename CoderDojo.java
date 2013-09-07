package coderdojo;

import coderdojo.blocks.Blocks;
import coderdojo.config.ConfigHandler;
import coderdojo.network.PacketHandler;
import coderdojo.proxies.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

//test
@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION)
@NetworkMod(channels = {ModInformation.CHANNEL}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class CoderDojo {
	
	@Instance("CoderDojo")
	public static CoderDojo instance;

	@SidedProxy(clientSide = "coderdojo.proxies.ClientProxy", serverSide = "coderdojo.proxies.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		Blocks.init();
		Blocks.addNames();

		proxy.initSounds();
		proxy.initRenderers();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {

	}
}
