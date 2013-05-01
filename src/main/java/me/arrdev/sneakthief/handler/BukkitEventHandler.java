package me.arrdev.sneakthief.handler;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.PlayerInventory;

public class BukkitEventHandler implements Listener {

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		if (e.isCancelled() || !(e.getRightClicked() instanceof Player))
			return;

		Player player = e.getPlayer();
		Player pp = (Player) e.getRightClicked();

		if (!player.isSneaking()
				|| pp.hasPermission("sneakthief.pickpocket.nosteal")
				|| !player.hasPermission("sneakthief.pickpocket.cansteal"))
			return;

		// TODO: Check other stuff.

		PlayerInventory inv = pp.getInventory();
		player.openInventory(inv);
	}

}
