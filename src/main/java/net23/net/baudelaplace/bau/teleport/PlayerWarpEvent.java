package net23.net.baudelaplace.bau.teleport;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerWarpEvent extends Event {

    Player p;
    Location l;
    
    public PlayerWarpEvent(Player p, Location l) {
	this.p = p;
	this.l = l;
    }
    
    public Player getPlayer() {
	return p;
    }
    
    public Location getLocation() {
	return l;
    }
    
    private static final HandlerList handlers = new HandlerList();
    
    public static HandlerList getHandlerList() {
	return handlers;
    }
    
    @Override
    public HandlerList getHandlers() {
	return handlers;
    }

}
