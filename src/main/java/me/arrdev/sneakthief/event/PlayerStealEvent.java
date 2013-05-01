package me.arrdev.sneakthief.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerStealEvent extends PlayerEvent implements Cancellable {

	private boolean cancelled = false;
	
	private final Player pp;

	private static final HandlerList handlers = new HandlerList();

	public PlayerStealEvent(Player who, Player pp) {
		super(who);
		this.pp = pp;
	}

	/**
	 * Returns the player who has been robbed by {@link #getPlayer() getPlayer()}.
	 * @return Player who has been robbed
	 */
	public Player getRobbedPlayer() {
		return pp;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
