package com.creativemd.toughasscrews;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import toughasnails.api.HealthHelper;
import toughasnails.item.ItemLifebloodCrystal;

public class ItemLifebloodCrystalWeak extends ItemLifebloodCrystal {
		
	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
		ItemStack stack = player.getHeldItem(hand);
    	if (HealthHelper.getActiveHearts(player) < 6 && HealthHelper.addActiveHearts(player, 1))
    	{
    		for (int i = 0; i < 8; i++)
    		{
    			double d0 = world.rand.nextGaussian() * 0.02D;
            	double d1 = world.rand.nextGaussian() * 0.02D;
            	double d2 = world.rand.nextGaussian() * 0.02D;
            	world.spawnParticle(EnumParticleTypes.HEART, player.posX + (double)(world.rand.nextFloat() * player.width * 2.0F) - (double)player.width, player.posY + 0.5D + (double)(world.rand.nextFloat() * player.height), player.posZ + (double)(world.rand.nextFloat() * player.width * 2.0F) - (double)player.width, d0, d1, d2, new int[0]);
    		}
    		world.playSound(player, player.getPosition(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 0.75F, 1.0F);
            stack.setCount(stack.getCount() - 1);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    	}
    	else
    	{
    		return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
    	}
    }
	
}
