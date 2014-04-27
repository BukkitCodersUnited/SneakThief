package me.arrdev.sneakthief.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.arrdev.sneakthief.config.ConfigurationManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class PlayerMoveTask implements Runnable {

	@Override
	public void run() {
		for (UUID uid : new ArrayList<UUID>(BukkitEventHandler.views)) {
			Player player = Bukkit.getPlayer(uid);
			List<HumanEntity> viewers = player.getInventory().getViewers();

			for (HumanEntity he : viewers) {
				Player pp = he instanceof Player ? (Player) he : null;

				if (he == null)
					continue;

				if (player.getLocation().distanceSquared(pp.getLocation()) > ConfigurationManager.getPlayerDistanceSquared()) {
					pp.closeInventory();

					if (viewers.size() == 1) {
						BukkitEventHandler.views.remove(uid);
					}
				}
			}
		}
	}

}
