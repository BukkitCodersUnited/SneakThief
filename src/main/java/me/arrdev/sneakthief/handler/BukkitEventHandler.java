package me.arrdev.sneakthief.handler;

import java.util.Random;

import me.arrdev.sneakthief.config.ConfigurationManager;
import me.arrdev.sneakthief.event.NPCStealEvent;
import me.arrdev.sneakthief.event.PlayerStealEvent;
import me.arrdev.sneakthief.util.Utilities;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BukkitEventHandler implements Listener {

	private Random rand = new Random();

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		if (e.isCancelled() || !(e.getRightClicked() instanceof Player))
			return;

		Player player = e.getPlayer();
		Player pp = (Player) e.getRightClicked();
		Inventory inv = pp.getInventory();

		if (!player.isSneaking()
				|| player.getLocation().distanceSquared(pp.getLocation()) > ConfigurationManager
						.getPlayerDistanceSquared()
				|| pp.hasPermission("pickpocket.nosteal")
				|| !player.hasPermission("pickpocket.cansteal")
				|| (!ConfigurationManager.isCreativeStealing() && (player
						.getGameMode() == GameMode.CREATIVE || pp.getGameMode() == GameMode.CREATIVE))
				|| (!ConfigurationManager.canRobNPC() && pp.hasMetadata("NPC")))
			return;

		if (ConfigurationManager.getSuccessPercentage() < 1
				&& (rand.nextInt(100) / 100) >= ConfigurationManager
						.getSuccessPercentage()) {
			return;
		}

		PlayerStealEvent ev = new PlayerStealEvent(player, pp);

		if (ConfigurationManager.canRobNPC() && pp.hasMetadata("NPC")) {
			inv = Bukkit.createInventory(pp, InventoryType.PLAYER);

			int items = rand.nextInt(ConfigurationManager.getMaxItems() + 1
					- ConfigurationManager.getMinItems())
					+ ConfigurationManager.getMinItems();

			if (items > 0)
				for (int i = 0; i < items; i++) {
					int j = rand.nextInt(ConfigurationManager
							.getPossibleItems().size());
					ItemStack is = ConfigurationManager.getPossibleItems().get(
							j);

					short defDur = is.getType().getMaxDurability();
					if (is.getDurability() == 0)
						is.setDurability((short) (rand
								.nextInt((int) (ConfigurationManager
										.getMaxDurability() * defDur + 1 - ConfigurationManager
										.getMinDurability() * defDur)) + ConfigurationManager
								.getMinDurability() * defDur));

					is.setAmount(rand.nextInt(ConfigurationManager
							.getMaxStackSize()
							+ 1
							- ConfigurationManager.getMinStackSize())
							+ ConfigurationManager.getMinStackSize());
				}

			inv = Utilities.arrangeInventory(inv);

			ev = new NPCStealEvent(player, pp, inv);
		} else {
			inv = Utilities.arrangeInventory(inv);
		}

		// TODO: Check other stuff.

		Bukkit.getServer().getPluginManager().callEvent(ev);
		if (ev.isCancelled())
			return;

		player.openInventory(inv);
	}
}
