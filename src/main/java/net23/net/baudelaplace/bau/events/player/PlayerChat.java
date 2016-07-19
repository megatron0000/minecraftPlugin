package net23.net.baudelaplace.bau.events.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener{
		
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String message = event.getMessage().toLowerCase();
		if (message.contains("chave")) {
			event.setCancelled(true);
			player.sendMessage(ChatColor.RED + "Não pode usar essa palavra =P");
		}
	}
}
