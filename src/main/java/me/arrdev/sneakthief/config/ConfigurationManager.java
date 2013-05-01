package me.arrdev.sneakthief.config;

import me.arrdev.sneakthief.SneakThief;

import org.bukkit.configuration.Configuration;

public class ConfigurationManager {

	private static Configuration conf;

	private static boolean robnpc = false;

	public ConfigurationManager(Configuration conf) {
		ConfigurationManager.conf = conf;
	}

	public static void reload() {
		SneakThief.getInstance().reloadConfig();

	}

	public static Configuration getConfiguration() {
		return conf;
	}

	public static boolean canRobNPC() {
		return robnpc;
	}

}
