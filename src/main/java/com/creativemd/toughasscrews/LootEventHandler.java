package com.creativemd.toughasscrews;

import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import toughasnails.api.item.TANItems;

public class LootEventHandler {
	
	@SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event)
    {		
		if (event.getName().equals(LootTableList.CHESTS_IGLOO_CHEST) || event.getName().equals(LootTableList.CHESTS_VILLAGE_BLACKSMITH))
		{
           LootPool main = event.getTable().getPool("main");
           if (main != null)
           {
               main.addEntry(new LootEntryItem(ToughAsScrews.lifeblood_crystal_weak, 3, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1, 2))}, new LootCondition[0], "toughasscrews:lifeblood_crystal_weak"));
           }
		}
		
		if (event.getName().equals(LootTableList.CHESTS_ABANDONED_MINESHAFT) || event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE) || event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON) || event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CORRIDOR))
		{
           LootPool main = event.getTable().getPool("main");
           if (main != null)
           {
               main.addEntry(new LootEntryItem(ToughAsScrews.lifeblood_crystal_weak, 3, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1, 3))}, new LootCondition[0], "toughasscrews:lifeblood_crystal_weak"));
           }
		}
		
		if (event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID) || event.getName().equals(LootTableList.CHESTS_NETHER_BRIDGE))
		{
           LootPool main = event.getTable().getPool("main");
           if (main != null)
           {
               main.addEntry(new LootEntryItem(ToughAsScrews.lifeblood_crystal_weak, 5, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1, 3))}, new LootCondition[0], "toughasscrews:lifeblood_crystal_weak"));
           }
		}
		
		if (event.getName().equals(LootTableList.CHESTS_END_CITY_TREASURE))
		{
           LootPool main = event.getTable().getPool("main");
           if (main != null)
           {
               main.addEntry(new LootEntryItem(ToughAsScrews.lifeblood_crystal_weak, 5, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(2, 7))}, new LootCondition[0], "toughasscrews:lifeblood_crystal_weak"));
           }
		}
    }
}
