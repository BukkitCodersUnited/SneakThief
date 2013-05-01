package me.arrdev.sneakthief.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.PlayerInventory;

public class NPCStealEvent extends PlayerStealEvent {

	private final PlayerInventory npcInv;

	private static final HandlerList handlers = new HandlerList();

	public NPCStealEvent(Player who, Player pp, PlayerInventory inv) {
		super(who, pp);
		this.npcInv = inv;
	}

	public PlayerInventory getInventory() {
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
