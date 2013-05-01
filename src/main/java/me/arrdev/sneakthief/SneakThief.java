package me.arrdev.sneakthief;

import me.arrdev.sneakthief.config.ConfigurationManager;
import me.arrdev.sneakthief.handler.BukkitEventHandler;

import org.bukkit.plugin.java.JavaPlugin;

public class SneakThief extends JavaPlugin {

	private static SneakThief instance;

	public void onEnable() {
		new ConfigurationManager(getConfig());
		
		getServer().getPluginManager().registerEvents(new BukkitEventHandler(),
				this);
	}

	public static SneakThief getInstance() {
		return instance;
	}

	public SneakThief() {
		instance = this;
	}

}
