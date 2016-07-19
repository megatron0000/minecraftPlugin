package net23.net.baudelaplace.bau;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net23.net.baudelaplace.bau.events.block.BlockBreak;
import net23.net.baudelaplace.bau.events.player.PlayerChat;
import net23.net.baudelaplace.bau.events.player.PlayerJoin;
import net23.net.baudelaplace.bau.commands.Basic;

public final class Bau extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("onEnable foi invocada");
		registerCommands();
		registerEvents();
	}

	@Override
	public void onDisable() {
		getLogger().info("onDisable foi invocada");
	}

	public void registerCommands() {
		getCommand("basic").setExecutor(new Basic());

	}

	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new PlayerChat(), this);
		pm.registerEvents(new PlayerJoin(this), this);
	}

}