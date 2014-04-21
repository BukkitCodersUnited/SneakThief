package me.arrdev.sneakthief.util;

import me.arrdev.sneakthief.config.ConfigurationManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class Utilities {

	/**
	 * Converts a player's inventory to a chest inventory.
	 * @param oinv the inventory to be converted
	 * @return the converted inventory
	 */
	public static Inventory arrangeInventory(Inventory oinv) {
		Inventory inv = Bukkit.getServer().createInventory(oinv.getHolder(), InventoryType.CHEST);

		for (int i = 0; i <= 35; i++) {
			inv.setItem(i, oinv.getItem(i));
		}

		int i = 103;
		for (int j = 45; j <= 48; j++) {
			inv.setItem(j, oinv.getItem(i));
			i--;
		}

		return inv;
	}
	
	/**
	 * This notifies the player if it is necessary.
	 * @param player the player who's robbing
	 * @param pp the player being robbed
	 * @param success if the inventory has been opened
	 * @see ConfigurationManager#shouldAlertPlayer()
	 */
	public static void notifyPlayerIfNecessary(Player player, Player pp, boolean success) {
		if (ConfigurationManager.shouldAlertPlayer())
			pp.sendMessage(player.getDisplayName() + ChatColor.RED + " " + (success ? "was a sneaky bastard and stole from you!" : "tried to steal from you but failed!"));
	}

}
