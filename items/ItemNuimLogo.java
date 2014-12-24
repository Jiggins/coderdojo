package coderdojo.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemNuimLogo extends Item {

//	private Icon mindsLogo;

	public ItemNuimLogo(int id) {
		super(id);

		setCreativeTab(CreativeTabs.tabMisc);
		setMaxStackSize(1);
		setUnlocalizedName("Minds Logo");
	}


	@Override	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			//If Shift button is not held,  I have another method for Shift+RightClick
			if (!player.isSneaking()) {
				player.addChatMessage("Trying to spawn Golem");
				Entity entity = EntityList.createEntityByID(112, world);

				if (entity != null && entity instanceof EntityLivingBase) {

					//Gets the position (x, y, z) of the block the player is looking at
					MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

					// Because it crashed when I right clicked when looking into the sky (not looking at a block)
					if (movingobjectposition == null) {
						System.out.println("null position!");
					    return itemstack;
					}

					int x =	movingobjectposition.blockX;
					int y =	movingobjectposition.blockY + 1;
					int z =	movingobjectposition.blockZ;

					//Casts the Entity Object above into an Entity Living Object.
					EntityLiving entityliving = (EntityLiving)entity;
					entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);

					//What way the head is facing
					entityliving.rotationYawHead = entityliving.rotationYaw;
					entityliving.renderYawOffset = entityliving.rotationYaw;
					//honestly no idea, but it looks important
//					entityliving.func_110161_a((EntityLivingData)null);
					//finally creates the pig in the world
					world.spawnEntityInWorld(entity);
					entityliving.playLivingSound();
				}
			}
		}
		return itemstack;
	}
}
