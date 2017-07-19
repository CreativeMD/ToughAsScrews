package com.creativemd.toughasscrews;

import static toughasnails.api.item.TANItems.lifeblood_crystal;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import toughasnails.core.ToughAsNails;
import toughasnails.handler.LootTableEventHandler;
import toughasnails.init.ModItems;
import toughasnails.item.ItemLifebloodCrystal;
import toughasnails.util.inventory.CreativeTabTAN;

@Mod(modid = ToughAsScrews.modid, version = ToughAsScrews.version, name = "ToughAsScrews", acceptedMinecraftVersions = "")
public class ToughAsScrews {

	public static final String modid = "toughasscrews";
	public static final String version = "1.0";
	
	public static Item lifeblood_crystal_weak;
	
	public static int weakCrystalMaxHeal = 12;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		weakCrystalMaxHeal = config.getInt("weakCrystalMaxHeal", "crystal", weakCrystalMaxHeal, 1, Integer.MAX_VALUE, "");
		config.save();
		
		lifeblood_crystal_weak = new ItemLifebloodCrystalWeak().setUnlocalizedName("lifeblood_crystal_weak").setRegistryName(ToughAsScrews.modid, "lifeblood_crystal_weak").setCreativeTab(CreativeTabTAN.instance);
		
		GameRegistry.register(lifeblood_crystal_weak);
		
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
			ModelLoader.setCustomModelResourceLocation(lifeblood_crystal_weak, 0, new ModelResourceLocation(modid + ":lifeblood_crystal_weak", "inventory"));
		
		MinecraftForge.EVENT_BUS.register(new LootEventHandler());
    }
	
}
