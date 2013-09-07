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
//
@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION)
@NetworkMod(channels = {ModInformation.CHANNEL}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class CoderDojo {
	
	@Instance("CoderDojo")
	public static CoderDojo instance;

	@SidedProxy(clientSide = "coderdojo.proxies.ClientProxy", serverSide = "coderdojo.proxies.CommonProxy")
	public static CommonProxy proxy;

	/**
     * 	FMLPreInitializationEvent : Run before anything else. Read your config, create blocks,
     * 	etc, and register them with the GameRegistry
     */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		Blocks.init();
		Blocks.addNames();
		
		proxy.initSounds();
		proxy.initRenderers();
	}
	
	/**
     * 	FMLInitializationEvent: Do your mod setup. Build whatever data structures you care about. Register recipes,
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {

	}

	/**
     * 	FMLPostInitializationEvent : Handle interaction with other mods, complete your setup based on this.
	 */
	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {

	}
}
