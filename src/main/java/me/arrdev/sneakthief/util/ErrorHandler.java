package me.arrdev.sneakthief.util;

import me.arrdev.sneakthief.config.ConfigurationManager;

public class ErrorHandler {

	public static void printErrorInformation(Throwable e) {
		System.err.println("An error has happened during runtime of SneakThief.");
		System.err.println("Post the following text as an issue");
		System.err.println("## BEGIN ##");
		System.err.println("## Configuration:");
		System.err.println(ConfigurationManager.getConfiguration().getValues(true));
		System.err.println("## Error:");
		e.printStackTrace();
		System.err.println("## END ##");
	}

}
