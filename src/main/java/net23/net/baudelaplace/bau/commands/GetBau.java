package net23.net.baudelaplace.bau.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net23.net.baudelaplace.bau.utils.HttpMessenger;


public class GetBau implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
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
