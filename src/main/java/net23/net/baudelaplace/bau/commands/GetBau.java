package net23.net.baudelaplace.bau.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net23.net.baudelaplace.bau.utils.HttpMessenger;


public class GetBau implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
	// S� usu�rios com permiss�o
	if (!sender.hasPermission("getbau")) {
	    sender.sendMessage(ChatColor.RED + "Voc� n�o tem permiss�o para usar este comando !");
	}
	
	// Deprecated
	// String serverResponse = get("http://localhost:3000/?test=Hola");

	String response = "";
	try {
	    response = HttpMessenger.get("contact.html?test=1");
	} catch (Exception e) {
	    response = e.getMessage();
	}
	sender.sendMessage(response);
	
	response = "";
	try {
	    response = HttpMessenger.post("anything.html");
	} catch (Exception e) {
	    response = e.getMessage();
	}
	sender.sendMessage(response);

	return true;
    }
}
