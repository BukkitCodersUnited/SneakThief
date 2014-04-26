package me.arrdev.sneakthief.config;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import me.arrdev.sneakthief.SneakThief;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

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

	/**
	 * Reloads the configuration from disk.
	 */
	public static void reload() {
		SneakThief.getInstance().reloadConfig();
		conf.setDefaults(YamlConfiguration.loadConfiguration(SneakThief.getInstance().getResource("config.yml")));
		conf.options().copyDefaults(true);
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
		cooldown = conf.getInt("pickpocket.cooldown", 5) * 1000;

		robnpc = conf.getBoolean("npc.can-rob.npcs", false);
		minItems = conf.getInt("npc.inventory.min-items", 1);
		maxItems = conf.getInt("npc.inventory.max-items", 3);
		minStackSize = conf.getInt("npc.inventory.min-stack-size", 1);
		maxStackSize = conf.getInt("npc.inventory.max-stack-size", 16);
		minDurability = conf.getDouble("npc.inventory.min-durability-percentage", .5D);
		maxDurability = conf.getDouble("npc.inventory.max-durability-percentage", 1D);

		List<String> pi = conf.getStringList("npc.inventory.possible-items");
		for (String i : pi) {
			Material m = Material.matchMaterial(i);
			if (m != null)
				possibleItems.add(m);
		}

		System.out.println(conf.getValues(true));

		SneakThief.getInstance().saveConfig();
	}

	/**
	 * Returns the Configuration file
	 * @return the configuration
	 */
	public static Configuration getConfiguration() {
		return conf;
	}

	/**
	 * Returns the maximum squared distance between two players to be able to steal.
	 * If the maximum distance is 5 blocks, then this function will return 25.
	 * @return the squared maximum distance
	 */
	public static int getPlayerDistanceSquared() {
		return playerDistanceSquared;
	}

	/**
	 * Returns the chance of success. The value will always be between 0 and 1.
	 * @return the success rate
	 */
	public static double getSuccessPercentage() {
		return successPercentage;
	}

	/**
	 * If this returns false and the stealing player or the player being robbed is in creative, then the plugin will not allow stealing.
	 * @return allow creative stealing
	 */
	public static boolean allowCreativeStealing() {
		return creativeStealing;
	}

	/**
	 * Returns the amount of hearts the robber will be damaged when the rob fails.
	 * @return the amount of damage
	 */
	public static int getDamageOnFailure() {
		return damageOnFailure;
	}

	/**
	 * Returns true if we should alert the player if someone tried to steal from him.
	 * @return true if we should alert the player
	 */
	public static boolean shouldAlertPlayer() {
		return alertPlayer;
	}

	/**
	 * Gets the amount of tries before cooldown.
	 * @return amount of tries
	 */
	public static int getMaxTries() {
		return maxTries;
	}

	/**
	 * Returns the amount of milliseconds the player will be in cooldown.
	 * @return cooldown in milliseconds
	 */
	public static int getCooldown() {
		return cooldown;
	}

	/**
	 * Returns true if the player should be able to steal from a Citizen's NPC.
	 * @return true if the player should be able to steal from an NPC
	 */
	public static boolean canRobNPC() {
		return robnpc;
	}

	/**
	 * Returns the minimum amount of items in the NPC's inventory.
	 * @return the minimum amount of items
	 */
	public static int getMinItems() {
		return minItems;
	}

	/**
	 * Returns the maximum amount of items in the NPC's inventory.
	 * @return the maximum amount of items
	 */
	public static int getMaxItems() {
		return maxItems;
	}

	/**
	 * Returns the minimum stack size of an item in the NPC's inventory.
	 * @return the minimum stack size of an item
	 */
	public static int getMinStackSize() {
		return minStackSize;
	}

	/**
	 * Returns the maximum stack size of an item in the NPC's inventory.
	 * @return the maximum stack size of an item
	 */
	public static int getMaxStackSize() {
		return maxStackSize;
	}

	/**
	 * Returns the minimum durability of an item in the NPC's inventory. This value should be between 0 and 1 and smaller than {@link #getMaxDurability()}.
	 * @return the minimum durability of an item
	 */
	public static double getMinDurability() {
		return minDurability;
	}

	/**
	 * Returns the maximum durability of an item in the NPC's inventory. This value should be between 0 and 1.
	 * @return the maximum durability of an item
	 */
	public static double getMaxDurability() {
		return maxDurability;
	}

	/**
	 * Returns a List of items which can occur in an NPC's inventory.
	 * @return the list of items
	 */
	public static List<Material> getPossibleItems() {
		return possibleItems;
	}

}
