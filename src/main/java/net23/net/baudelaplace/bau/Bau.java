package net23.net.baudelaplace.bau;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net23.net.baudelaplace.bau.commands.Basic;
import net23.net.baudelaplace.bau.commands.GetBau;
import net23.net.baudelaplace.bau.eventListeners.block.BlockBreak;
import net23.net.baudelaplace.bau.eventListeners.player.PlayerChat;
import net23.net.baudelaplace.bau.eventListeners.player.PlayerJoin;
import net23.net.baudelaplace.bau.teleport.Warp;
import net23.net.baudelaplace.bau.utils.SettingsManager;

public final class Bau extends JavaPlugin {

    public SettingsManager settings = SettingsManager.getInstance();

    @Override
    public void onEnable() {
	// Inicializar as configurações, para outros componentes do plugin
	// poderem usá-las
	settings.setup(this);
	
	Bukkit.getLogger().info("onEnable foi invocada");
	
	registerCommands();
	registerEventListeners();

    }

    @Override
    public void onDisable() {
	getLogger().info("onDisable foi invocada");
    }

    public void registerCommands() {
	getCommand("basic").setExecutor(new Basic());
	getCommand("getBau").setExecutor(new GetBau());
	getCommand("warp").setExecutor(new Warp());
    }

    public void registerEventListeners() {
	PluginManager pm = getServer().getPluginManager();
	pm.registerEvents(new BlockBreak(), this);
	pm.registerEvents(new PlayerChat(), this);
	pm.registerEvents(new PlayerJoin(this), this);
    }

}