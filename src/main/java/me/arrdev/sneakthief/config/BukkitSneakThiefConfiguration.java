package me.arrdev.sneakthief.config;

import java.util.List;
import java.util.Map;

import org.bukkit.configuration.Configuration;

public class BukkitSneakThiefConfiguration implements SneakThiefConfiguration {
	
	private Configuration config;
	
	public BukkitSneakThiefConfiguration(Configuration config) {
		this.config = config;
	}

	@Override
	public Object get(String path) {
		return config.get(path);
	}

	@Override
	public Object get(String path, Object def) {
		return config.get(path, def);
	}

	@Override
	public String getString(String path) {
		return config.getString(path);
	}

	@Override
	public String getString(String path, String def) {
		return config.getString(path, def);
	}

	@Override
	public double getDouble(String path) {
		return config.getDouble(path);
	}

	@Override
	public double getDouble(String path, double def) {
		return config.getDouble(path, def);
	}

	@Override
	public int getInteger(String path) {
		return config.getInt(path);
	}

	@Override
	public int getInteger(String path, int def) {
		return config.getInt(path, def);
	}

	@Override
	public List<?> getList(String path) {
		return config.getList(path);
	}

	@Override
	public List<?> getList(String path, List<?> def) {
		return config.getList(path, def);
	}

	@Override
	public List<String> getStringList(String path) {
		return config.getStringList(path);
	}

	@Override
	public Map<String, Object> getValues() {
		return getValues(false);
	}

	@Override
	public Map<String, Object> getValues(boolean deep) {
		return config.getValues(deep);
	}

}
