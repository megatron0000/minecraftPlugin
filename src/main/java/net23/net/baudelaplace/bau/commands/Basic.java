package net23.net.baudelaplace.bau.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;

public class Basic implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {

	File dataFolder, saveTo;
	YamlConfiguration config;
	try {
	    /**
	     * Descobri que dava para simplesmente passar a instância do plugin
	     * no construtor desta classe Basic... Mas vou deixar deste jeito
	     * complicado porque levei um bom tempo para aprender =)
	     */
	    dataFolder = Bukkit.getPluginManager().getPlugin("Bau_De_Laplace").getDataFolder();
	    if (!dataFolder.exists()) {
		dataFolder.mkdir();
	    }

	    saveTo = new File(dataFolder, "command_basic_database.yml");
	    if (!saveTo.exists()) {
		saveTo.createNewFile();
	    }

	    config = YamlConfiguration.loadConfiguration(saveTo);
	} catch (IOException e) {
	    sender.sendMessage(ChatColor.RED + "Erro de IO durante execução do comando");
	    return false;
	}

	// Requerer que o comando não seja executado do console
	if (!(sender instanceof Player)) {
	    sender.sendMessage(ChatColor.RED + "Não é possível executar pelo console");
	    return false;
	}

	// Com 1 só argumento, usuário quer saber o valor de um parâmetro de
	// configuração
	if (args.length == 1) {
	    String resposta = config.getString(args[0]);
	    if (resposta != null) {
		sender.sendMessage(ChatColor.GREEN + args[0] + " : " + resposta);
		return true;
	    } else {
		sender.sendMessage(ChatColor.RED + "Esse parâmetro não existe");
		return false;
	    }
	}
	// Com 2 argumentos, usuário quer colocar novo valor para parâmetro
	// de configuração
	else if (args.length == 2) {
	    String valor_original = config.getString(args[0]);
	    config.set(args[0], args[1]);
	    sender.sendMessage(ChatColor.GREEN + "Mudado de " + valor_original + " para " + config.getString(args[0]));
	    // Atualiza o arquivo no HD (para não deixar alterações só na
	    // memória RAM)
	    try {
		config.save(saveTo);
	    } catch (IOException e) {
		sender.sendMessage(ChatColor.RED + "Erro de IO durante execução do comando");
		return false;
	    }
	    return true;
	}
	// Se o número de parâmetros estiver errado...
	else
	    return false;
    }

}
