package me.arrdev.sneakthief.handler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import me.arrdev.sneakthief.config.ConfigurationManager;
import me.arrdev.sneakthief.event.NPCStealEvent;
import me.arrdev.sneakthief.event.PlayerStealEvent;
import me.arrdev.sneakthief.util.Utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

	private Map<UUID, Long> cooldowns = new HashMap<UUID, Long>();
	static Map<UUID, List<UUID>> views = new HashMap<UUID, List<UUID>>();

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		if (e.isCancelled() || !(e.getRightClicked() instanceof Player))
			return;

		Player player = e.getPlayer();
		Player pp = (Player) e.getRightClicked();
		Inventory inv = pp.getInventory();

		if (!player.isSneaking() || player.getLocation().distanceSquared(pp.getLocation()) > ConfigurationManager.getPlayerDistanceSquared() || pp.hasPermission("pickpocket.nosteal") || !player.hasPermission("pickpocket.cansteal") || (!ConfigurationManager.allowCreativeStealing() && (player.getGameMode() == GameMode.CREATIVE || pp.getGameMode() == GameMode.CREATIVE)) || (!ConfigurationManager.canRobNPC() && pp.hasMetadata("NPC")))
			return;

		if (cooldowns.containsKey(player.getUniqueId()) && System.currentTimeMillis() - cooldowns.get(player.getUniqueId()) <= ConfigurationManager.getCooldown()) {
			player.sendMessage(ChatColor.RED + "You need to wait " + BigDecimal.valueOf(System.currentTimeMillis() - cooldowns.get(player.getUniqueId()) / 1000).setScale(1, BigDecimal.ROUND_HALF_UP) + " seconds");

			return;
		}

		if (ConfigurationManager.getSuccessPercentage() < 1 && (Math.random() > ConfigurationManager.getSuccessPercentage())) {
			if (ConfigurationManager.getDamageOnFailure() > 0)
				player.damage(ConfigurationManager.getDamageOnFailure());

			Utilities.notifyPlayerIfNecessary(player, pp, false);

			return;
		}

		PlayerStealEvent ev = new PlayerStealEvent(player, pp);

		if (ConfigurationManager.canRobNPC() && pp.hasMetadata("NPC")) {
			inv = Bukkit.createInventory(pp, InventoryType.PLAYER);

			int items = ConfigurationManager.getMinItems() + rand.nextInt(ConfigurationManager.getMaxItems() + 1 - ConfigurationManager.getMinItems());

			if (items > 0)
				for (int i = 0; i < items; i++) {
					int j = rand.nextInt(ConfigurationManager.getPossibleItems().size());
					ItemStack is = new ItemStack(ConfigurationManager.getPossibleItems().get(j));

					short defDur = is.getType().getMaxDurability();
					if (is.getDurability() == 0)
						is.setDurability((short) (rand.nextInt((int) (ConfigurationManager.getMaxDurability() * defDur + 1 - ConfigurationManager.getMinDurability() * defDur)) + ConfigurationManager.getMinDurability() * defDur));

					is.setAmount(ConfigurationManager.getMinStackSize() + rand.nextInt(ConfigurationManager.getMaxStackSize() + 1 - ConfigurationManager.getMinStackSize()));
				}

			ev = new NPCStealEvent(player, pp, inv);
		}

		// TODO: Check other stuff.

		Bukkit.getServer().getPluginManager().callEvent(ev);
		if (ev.isCancelled())
			return;

		player.openInventory(inv);

		Utilities.notifyPlayerIfNecessary(player, pp, true);

		cooldowns.put(player.getUniqueId(), System.currentTimeMillis());
	}
}
