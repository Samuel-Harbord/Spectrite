package com.samuel.spectritemod;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemDoor;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import com.samuel.spectritemod.blocks.*;
import com.samuel.spectritemod.eventhandlers.SpectriteGeneralEventHandler;
import com.samuel.spectritemod.items.*;
import com.samuel.spectritemod.packets.PacketSyncSpectriteBoss;
import com.samuel.spectritemod.proxy.CommonProxy;
import com.samuel.spectritemod.tileentity.*;
import com.samuel.spectritemod.world.WorldGenSpectrite;

@Mod(modid = SpectriteMod.MOD_ID, name = SpectriteMod.MOD_NAME, version = SpectriteMod.VERSION, guiFactory = "com.samuel."
	+ SpectriteMod.MOD_ID + ".client.gui.GUIFactorySpectriteMod", acceptedMinecraftVersions="[1.11.2]")
public class SpectriteMod {
	public static final String MOD_NAME = "Spectrite Mod";
	public static final String MOD_ID = "spectritemod";
	public static final String VERSION = "1.0.1";

	@Mod.Instance
	public static SpectriteMod Instance = new SpectriteMod();
	public static WorldGenSpectrite spectrite = null;
	public static TileEntitySpectriteChest TileEntityMineralChest;
	public static BlockSpectriteChest BlockIronChest;
	public static BlockSpectriteChest BlockGoldChest;
	public static BlockSpectriteChest BlockDiamondChest;
	public static BlockSpectriteChest BlockSpectriteChest;
	public static BlockSpectriteChest BlockTrappedIronChest;
	public static BlockSpectriteChest BlockTrappedGoldChest;
	public static BlockSpectriteChest BlockTrappedDiamondChest;
	public static BlockSpectriteChest BlockTrappedSpectriteChest;
	public static BlockSpectriteOre BlockSpectriteOre;
	public static BlockSpectrite BlockSpectrite;
	public static ItemDiamondRod ItemDiamondRod;
	public static ItemSpectriteRod ItemSpectriteRod;
	public static ItemSpectriteGem ItemSpectriteGem;
	public static ItemSpectriteOrb ItemSpectriteOrb;
	public static ItemSpectriteShovel ItemSpectriteShovel;
	public static ItemSpectriteShovelSpecial ItemSpectriteShovelSpecial;
	public static ItemSpectritePickaxe ItemSpectritePickaxe;
	public static ItemSpectritePickaxeSpecial ItemSpectritePickaxeSpecial;
	public static ItemSpectriteAxe ItemSpectriteAxe;
	public static ItemSpectriteAxeSpecial ItemSpectriteAxeSpecial;
	public static ItemSpectriteSword ItemSpectriteSword;
	public static ItemSpectriteSwordSpecial ItemSpectriteSwordSpecial;
	public static ItemSpectriteSword ItemSpectriteSword2;
	public static ItemSpectriteSwordSpecial ItemSpectriteSword2Special;
	public static ItemSpectriteArmor ItemSpectriteHelmet;
	public static ItemSpectriteArmor ItemSpectriteChestplate;
	public static ItemSpectriteArmor ItemSpectriteLeggings;
	public static ItemSpectriteArmor ItemSpectriteBoots;
	public static ArmorMaterial SPECTRITE = new EnumHelper()
		.addArmorMaterial("spectrite", "spectritemod:spectrite_armor",
		72, new int[]{3, 6, 8, 3}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3);
	public static ToolMaterial SPECTRITE_TOOL = new EnumHelper()
		.addToolMaterial("spectrite_tool", 3, 2400, 10.0F, 4.0F, 15);
	public static ToolMaterial PERFECT_SPECTRITE_TOOL = new EnumHelper()
		.addToolMaterial("perfect_spectrite_tool", 3, 3600, 12.0F, 5.0F, 19);
	public static ToolMaterial SPECTRITE_2_TOOL = new EnumHelper()
			.addToolMaterial("spectrite_2_tool", 3, 5825, 12.0F, 6.0F, 22);
	public static ToolMaterial PERFECT_SPECTRITE_2_TOOL = new EnumHelper()
			.addToolMaterial("perfect_spectrite_2_tool", 3, 7842, 12.0F, 7.0F, 25);
	public static IItemPropertyGetter ItemPropertyGetterSpectrite;

	@SidedProxy(clientSide = "com.samuel.spectritemod.proxy.ClientProxy",
		serverSide = "com.samuel.spectritemod.proxy.CommonProxy")
	public static CommonProxy Proxy;
	public static SimpleNetworkWrapper Network;
	public static SpectriteModConfig Config;
	
	public static final int SPECTRITE_COUNT_SURFACE_DEFAULT = 1;
	public static final int SPECTRITE_MIN_SIZE_SURFACE_DEFAULT = 1;
	public static final int SPECTRITE_MAX_SIZE_SURFACE_DEFAULT = 3;
	public static final int SPECTRITE_MIN_Y_SURFACE_DEFAULT = 1;
	public static final int SPECTRITE_MAX_Y_SURFACE_DEFAULT = 16;
	public static final int SPECTRITE_COUNT_NETHER_DEFAULT = 2;
	public static final int SPECTRITE_MIN_SIZE_NETHER_DEFAULT = 1;
	public static final int SPECTRITE_MAX_SIZE_NETHER_DEFAULT = 5;
	public static final int SPECTRITE_MIN_Y_NETHER_DEFAULT = 1;
	public static final int SPECTRITE_MAX_Y_NETHER_DEFAULT = 127;
	public static final int SPECTRITE_COUNT_END_DEFAULT = 2;
	public static final int SPECTRITE_MIN_SIZE_END_DEFAULT = 1;
	public static final int SPECTRITE_MAX_SIZE_END_DEFAULT = 9;
	public static final int SPECTRITE_MIN_Y_END_DEFAULT = 4;
	public static final int SPECTRITE_MAX_Y_END_DEFAULT = 55;
	public static final double SPECTRITE_TOOL_COOLDOWN_DEFAULT = 1.75d;
	public static final SpectriteModConfig.EnumSpectriteArmourBonusMode SPECTRITE_ARMOUR_BONUS_MODE_DEFAULT =
		SpectriteModConfig.EnumSpectriteArmourBonusMode.NORMAL_BONUSES;
	public static final double SPECTRITE_BOSS_SPAWN_RATE_DEFAULT = 0.4d;
	public static final double SPECTRITE_BOSS_PERFECT_SWORD_RATE_DEFAULT = 25.0d;
	public static final double SPECTRITE_BOSS_ARMOUR_DROP_RATE_DEFAULT = 25.0d;
	public static final double SPECTRITE_BOSS_SWORD_DROP_RATE_DEFAULT = 50.0d;
	public static final double SPECTRITE_BOSS_ORB_DROP_RATE_DEFAULT = 50.0d;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		Network = NetworkRegistry.INSTANCE
			.newSimpleChannel("SpectriteMod");
		Network.registerMessage(
			PacketSyncSpectriteBoss.Handler.class,
			PacketSyncSpectriteBoss.class, 0, Side.CLIENT);
		Config = new SpectriteModConfig(e
			.getSuggestedConfigurationFile());
		MinecraftForge.EVENT_BUS
			.register(Config);
		Proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		Proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		Proxy.postInit(e);
	}
}