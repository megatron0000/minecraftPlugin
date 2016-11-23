package net23.net.baudelaplace.bau.eventListeners.player;

import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.minecraft.server.v1_10_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle.EnumTitleAction;
import net23.net.baudelaplace.bau.Bau;
import net.minecraft.server.v1_10_R1.PlayerConnection;

public class PlayerJoin implements Listener {

	// private Bau plugin;

	public PlayerJoin(Bau pl) {
		// plugin = pl;
	}

	@EventHandler
	private void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		/**
		 *	Construir packets de mensagem, depois enviá-las ao cliente 
		 */
		PacketPlayOutTitle welcomeTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE,
				ChatSerializer
						.a("{\"text\":\"Bem-vindo ao servidor !!\",\"bold\":true,\"color\":\"gold\",\"italic\":true}"),
				20, 40, 30);
		PacketPlayOutTitle welcomeSubtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE,
				ChatSerializer
						.a("{\"text\":\"Aproveite, " + player.getDisplayName() + "\",\"bold\":true,\"italic\":true}"),
				20, 40, 30);
		
		PlayerConnection con = ((CraftPlayer) player).getHandle().playerConnection;
		con.sendPacket(welcomeTitle);
		con.sendPacket(welcomeSubtitle);
		
		
	}

}
