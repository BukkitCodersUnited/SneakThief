package me.arrdev.sneakthief.config;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import me.arrdev.sneakthief.SneakThief;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;

public class ConfigurationManager {

	private static Configuration conf;

	private static int playerDistanceSquared = 25;
	private static double successPercentage = .5D;
	private static boolean creativeStealing = false;
	private static int damageOnFailure = 2;
	private static boolean alertPlayer = true;
	private static int maxTries = 3;
	private static int cooldown = 5;

	private static boolean robnpc = false;
	private static int minItems = 1;
	private static int maxItems = 3;
	private static int minStackSize = 1;
	private static int maxStackSize = 16;
	private static double minDurability = .5D;
	private static double maxDurability = 1.0D;
	private static List<Material> possibleItems = new ArrayList<Material>();

	public ConfigurationManager(Configuration conf) {
		ConfigurationManager.conf = conf;

		reload();
	}

	public static void reload() {
		SneakThief.getInstance().reloadConfig();
		conf.options().copyDefaults();
		possibleItems.clear();

		playerDistanceSquared = conf.getInt("pickpocket.player-distance", 5) ^ 2;
		successPercentage = conf.getDouble("pickpocket.success-percentage", .5D);
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		successPercentage = Double.valueOf(twoDForm.format(successPercentage).replace(",", "."));
		if (successPercentage > 1)
			successPercentage = 1;
		creativeStealing = conf.getBoolean("pickpocket.creative-stealing", false);
		damageOnFailure = conf.getInt("pickpocket.damage-on-failure", 2);
		alertPlayer = conf.getBoolean("pickpocket.alert-player", true);
		maxTries = conf.getInt("pickpocket.max-tries-before-cooldown", 3);
		cooldown = conf.getInt("pickpocket.cooldown", 5);

		robnpc = conf.getBoolean("npc.can-rob.npcs", false);
		minItems = conf.getInt("npc.inventory.min-items", 1);
		maxItems = conf.getInt("npc.inventory.max-items", 3);
		minStackSize = conf.getInt("npc.inventory.min-stack-size", 1);
		maxStackSize = conf.getInt("npc.inventory.max-stack-size", 1);
		minDurability = conf.getDouble("npc.inventory.min-durability-percentage", .5D);
		maxDurability = conf.getDouble("npc.inventory.max-durability-percentage", .5D);

		List<String> pi = conf.getStringList("npc.inventory.possible-items");
		for (String i : pi) {
			Material m = Material.matchMaterial(i);
			if (m != null)
				possibleItems.add(m);
		}

		System.out.println(conf.getValues(true));

		SneakThief.getInstance().saveConfig();
	}

	public static Configuration getConfiguration() {
		return conf;
	}

	public static int getPlayerDistanceSquared() {
		return playerDistanceSquared;
	}

	public static double getSuccessPercentage() {
		return successPercentage;
	}

	public static boolean isCreativeStealing() {
		return creativeStealing;
	}

	public static int getDamageOnFailure() {
		return damageOnFailure;
	}

	public static boolean isAlertPlayer() {
		return alertPlayer;
	}

	public static int getMaxTries() {
		return maxTries;
	}

	public static int getCooldown() {
		return cooldown;
	}

	public static void setCooldown(int cooldown) {
		ConfigurationManager.cooldown = cooldown;
	}

	public static boolean canRobNPC() {
		return robnpc;
	}

	public static int getMinItems() {
		return minItems;
	}

	public static int getMaxItems() {
		return maxItems;
	}

	public static int getMinStackSize() {
		return minStackSize;
	}

	public static int getMaxStackSize() {
		return maxStackSize;
	}

	public static double getMinDurability() {
		return minDurability;
	}

	public static double getMaxDurability() {
		return maxDurability;
	}

	public static List<Material> getPossibleItems() {
		return possibleItems;
	}

}
