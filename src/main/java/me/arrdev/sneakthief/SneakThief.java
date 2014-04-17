package me.arrdev.sneakthief;

import me.arrdev.sneakthief.config.ConfigurationManager;
import me.arrdev.sneakthief.handler.BukkitEventHandler;

import org.bukkit.plugin.java.JavaPlugin;

public class SneakThief extends JavaPlugin {

	private static SneakThief instance;

	@Override
	public void onEnable() {
		new ConfigurationManager(getConfig());

		getServer().getPluginManager().registerEvents(new BukkitEventHandler(), this);
	}
	
	@Override
	public void onDisable() {
		instance = null;
	}

	/**
	 * Gets the current instance
	 * @return the instance
	 */
	public static SneakThief getInstance() {
		return instance;
	}

	public SneakThief() {
		instance = this;
	}

}
