package me.arrdev.sneakthief.handler;

import me.arrdev.sneakthief.event.PlayerStealEvent;

import org.bukkit.Bukkit;
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
				|| !player.hasPermission("sneakthief.pickpocket.cansteal")
				|| (pp.hasMetadata("NPC")))
			return;

		// TODO: Check other stuff.

		PlayerStealEvent ev = new PlayerStealEvent(player, pp);
		Bukkit.getServer().getPluginManager().callEvent(ev);
		if (ev.isCancelled())
			return;

		PlayerInventory inv = pp.getInventory();
		player.openInventory(inv);
	}

}
