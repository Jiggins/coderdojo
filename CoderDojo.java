package coderdojo;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import coderdojo.blocks.BlockCoderDojo;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/*
 * This class is the main class for our mod.  The two @EventHandler methods below is similar to a main method.
 * This is where all the methods and objects from all other classes will be called.
 * This code will produce a lot of errors as its being typed because we use so many different classes.  
 * Usually the fix is simply pressing ctrl+shift+o to automatically import the required classes.
 * 
 * Note: Don't worry about the imports in any of these classes
 * eclipse will automatically import everything needed with the shortcut
 * ctrl+shift+o (Mac: Command+shift+o)
 * if it asks you what to import, always pick the one with either forge, fml or minecraft in its name.
 */


//Tells the game that this class is a mod and to add it to the game.
@Mod(modid = "CoderDojo", name = "Coder Dojo Mod", version = "Week 1")
public class CoderDojo {
	
	//Publicly declares the Block so it can be used anywhere.
	public static Block coderDojoBlock;
	public static Entity mindsGolem;
	public static Item mindsLogo;
	
	/**
	 * Runs before the game loads.
	 * Used to add Blocks, Items, Entities etc. to the game.
	 * @EventHandler is important here, mod will not load without it
	 */
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		/*
		 * Creates the Coder Dojo block from BlockCoderDojo.java
		 * Here I use 1024 as the ID, as I know its not used in Minecraft.
		 * Explained in BlockCoderDojo.java
		 */
		coderDojoBlock = new BlockCoderDojo(1024);
				
		//adds the block created above to the game;
		GameRegistry.registerBlock(coderDojoBlock, "coderDojoBlock");
	}
	
	/**
	 * Runs as the game loads.
	 * Used for adding names and recipes to the game
	 * @EventHandler is important here, mod will not load without it
	 */
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		//Registers the name of the block with Minecraft
		LanguageRegistry.addName(coderDojoBlock, "Coder Dojo Block");
		
		/*
		 * ItemStacks:
		 * An item stack is an item or block as it appears in the players inventory
		 * ItemStacks are created with new ItemeStack(Block) or new ItemStack(Item)
		 * The ItemStack is needed to add recipes to the game.
		 */
		ItemStack coderDojoStack = new ItemStack(coderDojoBlock);
		
		/*
		 * Furnace Recipe:
		 * GameRegistry.addSmelting(int input, ItemStack output, float xp)
		 * @param int input is the block or item id number for the block/item that needs to be smelted to return the output item.
		 * @param ItemStack output is the ItemStack (created above), this is the result of the smelting.
		 * @param float xp is the amount of experience points gained for smelting one block.
		 * 
		 * 
		 * The recipe below smelts one block of stone into a Coder Dojo Block.
		 * To get the item ID for Stone I used Block.stone.blockID, 
		 * I could have used 1 as the id but this way I dont neeed to look for the Item/Block ID's I want.
		 */
		GameRegistry.addSmelting(Block.stone.blockID, coderDojoStack, 1.0F);
	}
}