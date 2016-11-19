package net23.net.baudelaplace.bau;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net23.net.baudelaplace.bau.commands.Basic;
import net23.net.baudelaplace.bau.commands.GetBau;
import net23.net.baudelaplace.bau.events.block.BlockBreak;
import net23.net.baudelaplace.bau.events.player.PlayerChat;
import net23.net.baudelaplace.bau.events.player.PlayerJoin;

public final class Bau extends JavaPlugin {

    public SettingsManager settings = SettingsManager.getInstance();
    
    @Override
    public void onEnable() {
	settings.setup(this);
	FileConfiguration conf = settings.getConfig();
	Bukkit.getLogger().info(conf.toString());
	Bukkit.getLogger().info("onEnable foi invocada");
	registerCommands();
	registerEvents();
	
    }

    @Override
    public void onDisable() {
	getLogger().info("onDisable foi invocada");
    }

    public void registerCommands() {
	getCommand("basic").setExecutor(new Basic());
	getCommand("getBau").setExecutor(new GetBau());
    }

    public void registerEvents() {
	PluginManager pm = getServer().getPluginManager();
	pm.registerEvents(new BlockBreak(), this);
	pm.registerEvents(new PlayerChat(), this);
	pm.registerEvents(new PlayerJoin(this), this);
    }

}