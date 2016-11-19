package net23.net.baudelaplace.bau;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class SettingsManager {

    private SettingsManager() {
    }

    static SettingsManager instance = new SettingsManager();

    public static SettingsManager getInstance() {
	return instance;
    }

    FileConfiguration config;
    // Pouco importa para a lógica. É só uma referência ao arquivo
    File cFile;

    public void setup(Plugin p) {
	config = p.getConfig();
	config.options().copyDefaults(true);
	cFile = new File(p.getDataFolder(), "config.yml");
	this.saveConfig();
    }

    public FileConfiguration getConfig() {
	return config;
    }

    public void saveConfig() {
	try {
	    config.save(cFile);
	} catch (IOException e) {
	    Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml !");
	}
    }
    
    public void reloadConfig() {
	config = YamlConfiguration.loadConfiguration(cFile);
    }

}
