package net23.net.baudelaplace.bau.teleport;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerWarpEvent extends Event {

    Player p;
    Location l;
    String warpName;
    
    // Os métodos listados abaixo são invenção minha:
    // > Construtor
    // > getPlayer()
    // > getWarpName()
    // > getLocation()
    public PlayerWarpEvent(Player p, Location l, String warpName) {
	this.p = p;
	this.l = l;
	this.warpName = warpName;
    }
    
    public Player getPlayer() {
	return p;
    }
    
    public String getWarpName() {
	return warpName;
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
