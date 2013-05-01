package me.arrdev.sneakthief;

import me.arrdev.sneakthief.config.SneakThiefConfiguration;

import org.bukkit.plugin.java.JavaPlugin;

public class SneakThief extends JavaPlugin {
	
	private static SneakThief instance;
	private static SneakThiefConfiguration config;
	
	public void onEnable() {
		
	}
	
	public static SneakThiefConfiguration getConfiguration() {
		return config;
	}

	public static SneakThief getInstance() {
		return instance;
	}
	
	public SneakThief() {
		instance = this;
	}

}
