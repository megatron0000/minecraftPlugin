package net23.net.baudelaplace.bau.eventListeners.player;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
	// Só jogadores com permissão
	if (!event.getPlayer().hasPermission("healSign"))
	    return;
	
	// Quem escreve "[cura]" na placa produz a ativação da "mágica"
	if (event.getLines().length == 1 && event.getLine(0).equalsIgnoreCase("[cura]")) {
	    event.setLine((0), ChatColor.BLUE+ "[cura]" );
	    event.setLine(1, ChatColor.BLUE + "ativada...");
	}
    }
    
    // Faz com que uma placa com a palavra "[cura]" seja usada para cura
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
	// Somente jogadores permitidos
	if (!event.getPlayer().hasPermission("healSign"))
	    return;
	
	// Só interessam cliques com mão direita em Signs
	if (!(event.getClickedBlock().getState() instanceof Sign) || event.getAction() != Action.RIGHT_CLICK_BLOCK)
	    return;

	Sign clickedSign = (Sign) event.getClickedBlock().getState();

	System.out.println(clickedSign.getLines());
	
	// Só é Sign de cura se tiver a palavra "[cura]" na primeira linha e a
	// palavra "Funcionando..." na segunda linha
	if (!clickedSign.getLine(0).equalsIgnoreCase("[cura]") || !clickedSign.getLine(1).equalsIgnoreCase("ativada..."))
	    return;
	
	// Verificadas as condições de contorno, recuperar a vida do jogador
	Player p = event.getPlayer();
	p.setHealth(p.getMaxHealth());
    }

}
