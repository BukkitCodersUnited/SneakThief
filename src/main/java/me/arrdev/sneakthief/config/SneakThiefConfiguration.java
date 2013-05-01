package me.arrdev.sneakthief.config;

import java.util.List;
import java.util.Map;

public interface SneakThiefConfiguration {

	public Object get(String path);
	
	public Object get(String path, Object def);
	
	public String getString(String path);
	
	public String getString(String path, String def);
	
	public double getDouble(String path);
	
	public double getDouble(String path, double def);
	
	public int getInteger(String path);
	
	public int getInteger(String path, int def);
	
	public List<?> getList(String path);
	
	public List<?> getList(String path, List<?> def);
	
	public List<String> getStringList(String path);
	
	public Map<String, Object> getValues();
	
	public Map<String, Object> getValues(boolean deep);

}
