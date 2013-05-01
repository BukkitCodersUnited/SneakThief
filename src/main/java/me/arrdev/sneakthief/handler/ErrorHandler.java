package me.arrdev.sneakthief.handler;

import me.arrdev.sneakthief.SneakThief;

public class ErrorHandler {

	public static void printErrorInformation(Throwable e) {
		System.err.println("An error has happened during runtime of SneakThief.");
		System.err.println("Send the following code to the project's page.");
		System.err.println("## START HERE ##");
		System.err.println("## Configuration:");
		System.err.println(SneakThief.getConfiguration().getValues(true));
		System.err.println("## Error:");
		e.printStackTrace();
		System.err.println("## STOP HERE ##");
	}

}
