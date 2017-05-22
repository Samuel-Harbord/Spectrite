package com.samuel.spectritemod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.GuiConfigEntries.NumberSliderEntry;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpectriteModConfig {
	
	public static enum EnumSpectriteArmourBonusMode implements IStringSerializable {
		HALF_BONUSES, NORMAL_BONUSES, OVERPOWERED_BONUSES, BEAST_MODE, YGTBK, BROKEN_BONUSES;
		
		public float getHealthIncreaseMultiplier() {
			return Math.max(this.ordinal(), 0.5f);
		}
		
		public int getAbsorptionDelay() {
			int absorptionDelay = 0;
			
			switch (this.ordinal()) {
				case 0:
				case 1:
					absorptionDelay = 100;
					break;
				case 2:
					absorptionDelay = 75;
					break;
				case 3:
					absorptionDelay = 50;
					break;
				case 4:
					absorptionDelay = 25;
					break;
				case 5:
					absorptionDelay = 10;
					break;
			}
			
			return absorptionDelay;
		}

		@Override
		public String getName() {
			return I18n.translateToLocal("config.spectrite_armour_bonus_mode.options." + this.ordinal());
		}
		
		public static String[] getAllNames() {
			List<String> names = new ArrayList<String>();
			
			for (EnumSpectriteArmourBonusMode mode : values()) {
				names.add(mode.getName());
			}
			
			return names.toArray(new String[0]);
		}
		
		public static EnumSpectriteArmourBonusMode forName(String name) {
			if (name != null) {
				for (EnumSpectriteArmourBonusMode mode : values()) {
					if (name.equals(mode.getName())) {
						return mode;
					}
				}
			}
			
			return SpectriteMod.SPECTRITE_ARMOUR_BONUS_MODE_DEFAULT;
		}
	}
	
	public static int spectriteCountSurface = SpectriteMod.SPECTRITE_COUNT_SURFACE_DEFAULT;
	public static int spectriteMinSizeSurface = SpectriteMod.SPECTRITE_MIN_SIZE_SURFACE_DEFAULT;
	public static int spectriteMaxSizeSurface = SpectriteMod.SPECTRITE_MAX_SIZE_SURFACE_DEFAULT;
	public static int spectriteMinYSurface = SpectriteMod.SPECTRITE_MIN_Y_SURFACE_DEFAULT;
	public static int spectriteMaxYSurface = SpectriteMod.SPECTRITE_MAX_Y_SURFACE_DEFAULT;
	public static int spectriteCountNether = SpectriteMod.SPECTRITE_COUNT_NETHER_DEFAULT;
	public static int spectriteMinSizeNether = SpectriteMod.SPECTRITE_MIN_SIZE_NETHER_DEFAULT;
	public static int spectriteMaxSizeNether = SpectriteMod.SPECTRITE_MAX_SIZE_NETHER_DEFAULT;
	public static int spectriteMinYNether = SpectriteMod.SPECTRITE_MIN_Y_NETHER_DEFAULT;
	public static int spectriteMaxYNether = SpectriteMod.SPECTRITE_MAX_Y_NETHER_DEFAULT;
	public static int spectriteCountEnd = SpectriteMod.SPECTRITE_COUNT_END_DEFAULT;
	public static int spectriteMinSizeEnd = SpectriteMod.SPECTRITE_MIN_SIZE_END_DEFAULT;
	public static int spectriteMaxSizeEnd = SpectriteMod.SPECTRITE_MAX_SIZE_END_DEFAULT;
	public static int spectriteMinYEnd = SpectriteMod.SPECTRITE_MIN_Y_END_DEFAULT;
	public static int spectriteMaxYEnd = SpectriteMod.SPECTRITE_MAX_Y_END_DEFAULT;
	public static EnumSpectriteArmourBonusMode spectriteArmourBonusMode = SpectriteMod.SPECTRITE_ARMOUR_BONUS_MODE_DEFAULT;
	public static double spectriteToolCooldown = SpectriteMod.SPECTRITE_TOOL_COOLDOWN_DEFAULT;
	public static double spectriteBossSpawnRate = SpectriteMod.SPECTRITE_BOSS_SPAWN_RATE_DEFAULT;
	public static double spectriteBossPerfectSwordRate = SpectriteMod.SPECTRITE_BOSS_PERFECT_SWORD_RATE_DEFAULT;
	public static double spectriteBossArmourDropRate = SpectriteMod.SPECTRITE_BOSS_ARMOUR_DROP_RATE_DEFAULT;
	public static double spectriteBossSwordDropRate = SpectriteMod.SPECTRITE_BOSS_SWORD_DROP_RATE_DEFAULT;
	public static double spectriteBossOrbDropRate = SpectriteMod.SPECTRITE_BOSS_ORB_DROP_RATE_DEFAULT;
	
	public static Property propSpectriteCountSurface = null;
	public static Property propSpectriteMinSizeSurface = null;
	public static Property propSpectriteMaxSizeSurface = null;
	public static Property propSpectriteMinYSurface = null;
	public static Property propSpectriteMaxYSurface = null;
	public static Property propSpectriteCountNether = null;
	public static Property propSpectriteMinSizeNether = null;
	public static Property propSpectriteMaxSizeNether = null;
	public static Property propSpectriteMinYNether = null;
	public static Property propSpectriteMaxYNether = null;
	public static Property propSpectriteCountEnd = null;
	public static Property propSpectriteMinSizeEnd = null;
	public static Property propSpectriteMaxSizeEnd = null;
	public static Property propSpectriteMinYEnd = null;
	public static Property propSpectriteMaxYEnd = null;
	public static Property propSpectriteArmourBonusMode = null;
	public static Property propSpectriteToolCooldown = null;
	public static Property propSpectriteBossSpawnRate = null;
	public static Property propSpectriteBossPerfectSwordRate = null;
	public static Property propSpectriteBossArmourDropRate = null;
	public static Property propSpectriteBossSwordDropRate = null;
	public static Property propSpectriteBossOrbDropRate = null;
	
	public static List<String> propertyNames = null;
	public static List<String> propertyDescriptions = null;
	
	public final Configuration configuration;
	
	public SpectriteModConfig(File file)
	{
		this.configuration = new Configuration(file, true);
		
		final String[] propertyKeys = new String[] {
			"spectrite_count_surface",
			"spectrite_minSize_surface",
			"spectrite_maxSize_surface",
			"spectrite_minY_surface",
			"spectrite_maxY_surface",
			"spectrite_count_nether",
			"spectrite_minSize_nether",
			"spectrite_maxSize_nether",
			"spectrite_minY_nether",
			"spectrite_maxY_nether",
			"spectrite_count_end",
			"spectrite_minSize_end",
			"spectrite_maxSize_end",
			"spectrite_minY_end",
			"spectrite_maxY_end",
			"spectrite_armour_bonus_mode",
			"spectrite_tool_cooldown",
			"spectrite_boss_spawn_rate",
			"spectrite_boss_perfect_sword_rate",
			"spectrite_boss_armour_drop_rate",
			"spectrite_boss_sword_drop_rate",
			"spectrite_boss_orb_drop_rate",
		};
		
		this.propertyNames = new ArrayList<String>();
		this.propertyDescriptions = new ArrayList<String>();
		
		for (String k : propertyKeys) {
			this.propertyNames.add(I18n.translateToLocal("config." + k + ".name"));
			this.propertyDescriptions.add(I18n.translateToLocal("config." + k + ".desc"));
		}

		this.loadConfig();
		
		configuration.setCategoryPropertyOrder(configuration.CATEGORY_GENERAL, propertyNames);
	}

	private void loadConfig()
	{
		configuration.setCategoryRequiresMcRestart(configuration.CATEGORY_GENERAL, false);
		propSpectriteCountSurface = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(0), SpectriteMod.SPECTRITE_COUNT_SURFACE_DEFAULT,
				propertyDescriptions.get(0), 0, 20);
		propSpectriteMinSizeSurface = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(1), SpectriteMod.SPECTRITE_MIN_SIZE_SURFACE_DEFAULT,
				propertyDescriptions.get(1), 0, 20);
		propSpectriteMaxSizeSurface = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(2), SpectriteMod.SPECTRITE_MAX_SIZE_SURFACE_DEFAULT,
				propertyDescriptions.get(2), Math.max(0, propSpectriteMinSizeSurface.getInt(SpectriteMod.SPECTRITE_MIN_SIZE_SURFACE_DEFAULT)), 20);
		propSpectriteMinSizeSurface.setMaxValue(Math.min(20, propSpectriteMaxSizeSurface.getInt(SpectriteMod.SPECTRITE_MAX_SIZE_SURFACE_DEFAULT)));
		propSpectriteMinYSurface = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(3), SpectriteMod.SPECTRITE_MIN_Y_SURFACE_DEFAULT,
				propertyDescriptions.get(3), 1, 128);
		propSpectriteMaxYSurface = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(4), SpectriteMod.SPECTRITE_MAX_Y_SURFACE_DEFAULT,
				propertyDescriptions.get(4), Math.max(1, propSpectriteMinYSurface.getInt(SpectriteMod.SPECTRITE_MIN_Y_SURFACE_DEFAULT)), 128);
		propSpectriteMinYSurface.setMaxValue(Math.min(128, propSpectriteMaxYSurface.getInt(SpectriteMod.SPECTRITE_MAX_Y_SURFACE_DEFAULT)));
		propSpectriteCountNether = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(5), SpectriteMod.SPECTRITE_COUNT_NETHER_DEFAULT,
				propertyDescriptions.get(5), 0, 20);
		propSpectriteMinSizeNether = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(6), SpectriteMod.SPECTRITE_MIN_SIZE_NETHER_DEFAULT,
				propertyDescriptions.get(6), 0, 20);
		propSpectriteMaxSizeNether = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(7), SpectriteMod.SPECTRITE_MAX_SIZE_NETHER_DEFAULT,
				propertyDescriptions.get(7), Math.max(0, propSpectriteMinSizeNether.getInt(SpectriteMod.SPECTRITE_MIN_SIZE_NETHER_DEFAULT)), 20);
		propSpectriteMinSizeNether.setMaxValue(Math.min(20, propSpectriteMaxSizeNether.getInt(SpectriteMod.SPECTRITE_MAX_SIZE_NETHER_DEFAULT)));
		propSpectriteMinYNether = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(8), SpectriteMod.SPECTRITE_MIN_Y_NETHER_DEFAULT,
				propertyDescriptions.get(8), 1, 127);
		propSpectriteMaxYNether = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(9), SpectriteMod.SPECTRITE_MAX_Y_NETHER_DEFAULT,
				propertyDescriptions.get(9), Math.max(1, propSpectriteMinYNether.getInt(SpectriteMod.SPECTRITE_MIN_Y_NETHER_DEFAULT)), 127);
		propSpectriteMinYNether.setMaxValue(Math.min(127, propSpectriteMaxYNether.getInt(SpectriteMod.SPECTRITE_MAX_Y_NETHER_DEFAULT)));
		propSpectriteCountEnd = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(10), SpectriteMod.SPECTRITE_COUNT_END_DEFAULT,
				propertyDescriptions.get(10), 0, 20);
		propSpectriteMinSizeEnd = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(11), SpectriteMod.SPECTRITE_MIN_SIZE_END_DEFAULT,
				propertyDescriptions.get(11), 0, 20);
		propSpectriteMaxSizeEnd = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(12), SpectriteMod.SPECTRITE_MAX_SIZE_END_DEFAULT,
				propertyDescriptions.get(12), Math.max(0, propSpectriteMinSizeEnd.getInt(SpectriteMod.SPECTRITE_MIN_SIZE_END_DEFAULT)), 20);
		propSpectriteMinSizeEnd.setMaxValue(Math.min(20, propSpectriteMaxSizeEnd.getInt(SpectriteMod.SPECTRITE_MAX_SIZE_END_DEFAULT)));
		propSpectriteMinYEnd = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(13), SpectriteMod.SPECTRITE_MIN_Y_END_DEFAULT,
				propertyDescriptions.get(13), 4, 70);
		propSpectriteMaxYEnd = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(14), SpectriteMod.SPECTRITE_MAX_Y_END_DEFAULT,
				propertyDescriptions.get(14), Math.max(4, propSpectriteMinYEnd.getInt(SpectriteMod.SPECTRITE_MIN_Y_END_DEFAULT)), 70);
		propSpectriteMinYEnd.setMaxValue(Math.min(55, propSpectriteMaxYEnd.getInt(SpectriteMod.SPECTRITE_MAX_Y_END_DEFAULT)));
		propSpectriteArmourBonusMode = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(15), SpectriteMod.SPECTRITE_ARMOUR_BONUS_MODE_DEFAULT.getName(),
				propertyDescriptions.get(15), EnumSpectriteArmourBonusMode.getAllNames());
		propSpectriteToolCooldown = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(16), SpectriteMod.SPECTRITE_TOOL_COOLDOWN_DEFAULT,
				propertyDescriptions.get(16), 0, 60);
		propSpectriteBossSpawnRate = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(17), SpectriteMod.SPECTRITE_BOSS_SPAWN_RATE_DEFAULT,
				propertyDescriptions.get(17), 0, 100);
		propSpectriteBossPerfectSwordRate = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(18), SpectriteMod.SPECTRITE_BOSS_PERFECT_SWORD_RATE_DEFAULT,
				propertyDescriptions.get(18), 0, 100);
		propSpectriteBossArmourDropRate = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(19), SpectriteMod.SPECTRITE_BOSS_ARMOUR_DROP_RATE_DEFAULT,
				propertyDescriptions.get(19), 0, 100);
		propSpectriteBossSwordDropRate = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(20), SpectriteMod.SPECTRITE_BOSS_SWORD_DROP_RATE_DEFAULT,
				propertyDescriptions.get(20), 0, 100);
		propSpectriteBossOrbDropRate = configuration.get(
				Configuration.CATEGORY_GENERAL,
				propertyNames.get(21), SpectriteMod.SPECTRITE_BOSS_ORB_DROP_RATE_DEFAULT,
				propertyDescriptions.get(21), 0, 100);
		this.spectriteCountSurface = propSpectriteCountSurface.getInt(SpectriteMod.SPECTRITE_COUNT_SURFACE_DEFAULT);
		this.spectriteMinSizeSurface = propSpectriteMinSizeSurface.getInt(SpectriteMod.SPECTRITE_MIN_SIZE_SURFACE_DEFAULT);
		this.spectriteMaxSizeSurface = propSpectriteMaxSizeSurface.getInt(SpectriteMod.SPECTRITE_MAX_SIZE_SURFACE_DEFAULT);
		this.spectriteMinYSurface = propSpectriteMinYSurface.getInt(SpectriteMod.SPECTRITE_MIN_Y_SURFACE_DEFAULT);
		this.spectriteMaxYSurface = propSpectriteMaxYSurface.getInt(SpectriteMod.SPECTRITE_MAX_Y_SURFACE_DEFAULT);
		this.spectriteCountNether = propSpectriteCountNether.getInt(SpectriteMod.SPECTRITE_COUNT_NETHER_DEFAULT);
		this.spectriteMinSizeNether = propSpectriteMinSizeNether.getInt(SpectriteMod.SPECTRITE_MIN_SIZE_NETHER_DEFAULT);
		this.spectriteMaxSizeNether = propSpectriteMaxSizeNether.getInt(SpectriteMod.SPECTRITE_MAX_SIZE_NETHER_DEFAULT);
		this.spectriteMinYNether = propSpectriteMinYNether.getInt(SpectriteMod.SPECTRITE_MIN_Y_NETHER_DEFAULT);
		this.spectriteMaxYNether = propSpectriteMaxYNether.getInt(SpectriteMod.SPECTRITE_MAX_Y_NETHER_DEFAULT);
		this.spectriteCountEnd = propSpectriteCountEnd.getInt(SpectriteMod.SPECTRITE_COUNT_END_DEFAULT);
		this.spectriteMinSizeEnd = propSpectriteMinSizeEnd.getInt(SpectriteMod.SPECTRITE_MIN_SIZE_END_DEFAULT);
		this.spectriteMaxSizeEnd = propSpectriteMaxSizeEnd.getInt(SpectriteMod.SPECTRITE_MAX_SIZE_END_DEFAULT);
		this.spectriteMinYEnd = propSpectriteMinYEnd.getInt(SpectriteMod.SPECTRITE_MIN_Y_END_DEFAULT);
		this.spectriteMaxYEnd = propSpectriteMaxYEnd.getInt(SpectriteMod.SPECTRITE_MAX_Y_END_DEFAULT);
		this.spectriteArmourBonusMode = EnumSpectriteArmourBonusMode.forName(propSpectriteArmourBonusMode.getString());
		this.spectriteToolCooldown = propSpectriteToolCooldown.getDouble(SpectriteMod.SPECTRITE_TOOL_COOLDOWN_DEFAULT);
		this.spectriteBossSpawnRate = propSpectriteBossSpawnRate.getDouble(SpectriteMod.SPECTRITE_BOSS_SPAWN_RATE_DEFAULT);
		this.spectriteBossPerfectSwordRate = propSpectriteBossPerfectSwordRate.getDouble(SpectriteMod.SPECTRITE_BOSS_PERFECT_SWORD_RATE_DEFAULT);
		this.spectriteBossArmourDropRate = propSpectriteBossArmourDropRate.getDouble(SpectriteMod.SPECTRITE_BOSS_ARMOUR_DROP_RATE_DEFAULT);
		this.spectriteBossSwordDropRate = propSpectriteBossSwordDropRate.getDouble(SpectriteMod.SPECTRITE_BOSS_SWORD_DROP_RATE_DEFAULT);
		this.spectriteBossOrbDropRate = propSpectriteBossOrbDropRate.getDouble(SpectriteMod.SPECTRITE_BOSS_ORB_DROP_RATE_DEFAULT);

		if (this.configuration.hasChanged())
		{
			this.configuration.save();
		}
	}
	
	private int getInt(ConfigCategory category, String name, int defaultValue)
	{
		return this.configuration.get(category.getName(), name, defaultValue).getInt();
	}


	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent e)
	{
		if(e.getModID().equals(SpectriteMod.MOD_ID))
		{
			this.loadConfig();
			
			int temp = 0;
			if (this.spectriteMinSizeSurface > this.spectriteMaxSizeSurface) {
				temp = this.spectriteMinSizeSurface;
				this.spectriteMinSizeSurface = this.spectriteMaxSizeSurface;
				this.spectriteMaxSizeSurface = temp;
			}
			if (this.spectriteMinYSurface > this.spectriteMaxYSurface) {
				temp = this.spectriteMinYSurface;
				this.spectriteMinYSurface = this.spectriteMaxYSurface;
				this.spectriteMaxYSurface = temp;
			}
			if (this.spectriteMinSizeNether > this.spectriteMaxSizeNether) {
				temp = this.spectriteMinSizeNether;
				this.spectriteMinSizeNether = this.spectriteMaxSizeNether;
				this.spectriteMaxSizeNether = temp;
			}
			if (this.spectriteMinYNether > this.spectriteMaxYNether) {
				temp = this.spectriteMinYNether;
				this.spectriteMinYNether = this.spectriteMaxYNether;
				this.spectriteMaxYNether = temp;
			}
			if (this.spectriteMinSizeEnd > this.spectriteMaxSizeEnd) {
				temp = this.spectriteMinSizeEnd;
				this.spectriteMinSizeEnd = this.spectriteMaxSizeEnd;
				this.spectriteMaxSizeEnd = temp;
			}
			if (this.spectriteMinYEnd > this.spectriteMaxYEnd) {
				temp = this.spectriteMinYEnd;
				this.spectriteMinYEnd = this.spectriteMaxYEnd;
				this.spectriteMaxYEnd = temp;
			}
		}
	}

}