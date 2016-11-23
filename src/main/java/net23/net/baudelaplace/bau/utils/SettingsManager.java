package net23.net.baudelaplace.bau.utils;

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

    FileConfiguration data;
    File dFile;

    public void setup(Plugin p) {

	if (!p.getDataFolder().exists()) {
	    try {
		boolean dirCreated = p.getDataFolder().mkdir();
		if (!dirCreated) {
		    Bukkit.getLogger().severe("Não foi possível criar a pasta de Bau_De_Laplace");
		}
	    } catch (Exception e) {
		Bukkit.getLogger().severe("Não foi possível criar a pasta de Bau_De_Laplace");
	    }
	}

	// Não verificaremos se config.yml existe mesmo, porque a criação deste
	// arquivo (em caso de não-existência) é garantida por outros métodos
	// desta classe (SettingsManager)
	cFile = new File(p.getDataFolder(), "config.yml");
	config = p.getConfig();
	// config.options().copyDefaults(true);
	// this.saveConfig();

	dFile = new File(p.getDataFolder(), "data.yml");

	if (!dFile.exists()) {
	    try {
		dFile.createNewFile();
	    } catch (IOException e) {
		Bukkit.getLogger().severe(ChatColor.RED + "Could not create data.yml !");
	    }
	}

	data = YamlConfiguration.loadConfiguration(dFile);

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

    public FileConfiguration getData() {
	return this.data;
    }

    public void saveData() {
	try {
	    this.data.save(dFile);
	} catch (IOException e) {
	    Bukkit.getLogger().severe(ChatColor.RED + "Could not save in data.yml !");
	}
    }

    public void reloadData() {
	this.data = YamlConfiguration.loadConfiguration(dFile);
    }

}
