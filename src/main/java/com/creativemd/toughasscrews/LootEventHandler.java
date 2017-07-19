package com.creativemd.toughasscrews;

import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import toughasnails.api.item.TANItems;

public class LootEventHandler {
	
	@SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event)
    {
		if (!event.getName().equals(LootTableList.CHESTS_NETHER_BRIDGE))
		{
            LootPool main = event.getTable().getPool("main");
            if (main != null)
            {
                main.addEntry(new LootEntryItem(ToughAsScrews.lifeblood_crystal_weak, 1, 0, new LootFunction[0], new LootCondition[0], "toughasscrews:lifeblood_crystal_weak"));
            }
		}
    }
}
