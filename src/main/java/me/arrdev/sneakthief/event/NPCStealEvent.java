package me.arrdev.sneakthief.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

public class NPCStealEvent extends PlayerStealEvent {

	private final Inventory npcInv;

	private static final HandlerList handlers = new HandlerList();

	public NPCStealEvent(Player who, Player pp, Inventory inv) {
		super(who, pp);
		this.npcInv = inv;
	}

	/**
	 * Returns the NPC's inventory.
	 * 
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return npcInv;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
