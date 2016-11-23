package net23.net.baudelaplace.bau.teleport;

import java.util.Iterator;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import net23.net.baudelaplace.bau.utils.SettingsManager;

public class Warp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	SettingsManager settings = SettingsManager.getInstance();
	settings.setup(Bukkit.getPluginManager().getPlugin("Bau_De_Laplace"));

	// Recusar se não for um Player que pede o comando
	if (!(sender instanceof Player)) {
	    sender.sendMessage("Só jogadores podem usar warps !");
	    return false;
	}
	Player p = (Player) sender;

	// Sem argumentos, a pessoa não sabe pedir um "warp"
	if (args.length == 0) {
	    sender.sendMessage(ChatColor.RED + "Especifique o nome do warp!");
	    return false;
	}

	// Com um argumento, queremos ir a um warp
	if (args.length == 1) {

	    // Como o comando pode levar mais do que um só argumento, caso no
	    // qual os primeiro argumentos são subcomandos, nenhum warp poderá
	    // se chamar nem "set" nem "del"
	    if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("del")) {
		sender.sendMessage(ChatColor.RED + "Nenhum warp pode se chamar 'set' ou 'del'");
		return false;
	    }

	    // Recuperar da configuração os dados (coordenadas e
	    // mundo) deste warp. Se não existe, usuário é avisado
	    ConfigurationSection warpData = settings.getData().getConfigurationSection("warps." + args[0]);
	    if (warpData == null) {
		sender.sendMessage(ChatColor.RED + "O warp " + args[0] + " não existe !");
		return false;
	    }

	    // Se o warp pedido está registrado, teleportar
	    // Mundo e localização de destino
	    World world = Bukkit.getServer().getWorld(warpData.getString("world"));
	    double x = warpData.getDouble("x");
	    double y = warpData.getDouble("y");
	    double z = warpData.getDouble("z");
	    // Teleportar
	    Location l = new Location(world, x, y, z);
	    p.teleport(l);
	    // Lançar evento. Dados: Player, posição, nome do warp
	    Bukkit.getPluginManager().callEvent(new PlayerWarpEvent(p, l, args[0]));
	    

	    p.sendMessage(ChatColor.GREEN + "Teleportado a " + args[0]);
	    return true;
	}

	// Com dois argumentos, ou criamos, ou deletamos, ou listamos
	if (args.length == 2) {
	    switch (args[0]) {
	    case "set":
		settings.getData().set("warps." + args[1] + ".x", p.getLocation().getX());
		settings.getData().set("warps." + args[1] + ".y", p.getLocation().getY());
		settings.getData().set("warps." + args[1] + ".z", p.getLocation().getZ());
		settings.getData().set("warps." + args[1] + ".world", p.getWorld().getName());
		settings.saveData();
		p.sendMessage(ChatColor.GREEN + "Criado o warp " + args[1] + "!");
		return true;

	    case "del":
		// Com tudo certo, recuperar da configuração os dados
		// (coordenadas e
		// mundo) deste warp. Se não existe, usuário é avisado
		ConfigurationSection warpData = settings.getData().getConfigurationSection("warps." + args[1]);
		if (warpData == null) {
		    sender.sendMessage(ChatColor.RED + "O warp " + args[1] + " não existe !");
		    return false;
		}

		// Se o warp pedido está registrado, deletá-lo
		settings.getData().set("warps." + args[1], null);
		settings.saveData();
		p.sendMessage(ChatColor.GREEN + "Deletado o warp " + args[1]);
		return true;

	    case "list":
		sender.sendMessage(ChatColor.GREEN + "Os warps existentes são: {");
		Set<String> warps =  settings.getData().getConfigurationSection("warps").getKeys(false);
		Iterator<String> iterator = warps.iterator();
		while(iterator.hasNext()) {
		    String element = iterator.next();
		    sender.sendMessage(ChatColor.GREEN + "  " + element);
		}
		sender.sendMessage(ChatColor.GREEN + "}");
		
		return true;

	    default:
		return false;
	    }
	}

	return false;
    }

}
