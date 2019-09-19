package me.gabriel.mito.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.gabriel.mito.Main;
import me.gabriel.mito.manager.Manager_Mito;
import me.gabriel.mito.manager.Manager_Npc;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Comando_Mito implements CommandExecutor{
	

    public boolean onCommand(final CommandSender sender, final Command cmd, final String arg2, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("mito")) {
            if (Main.plugin.getConfig().getString("Mito").length() > 0) {
                sender.sendMessage(Main.plugin.atual.replaceAll("<player>", Main.plugin.player));
            }
            else {
                sender.sendMessage(Main.plugin.atual2.replaceAll("&", "§"));
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("setmito")) {
            if (!sender.hasPermission("mito.admin")) {
                sender.sendMessage("Voc\u00ea n\u00e3o tem permiss\u00e3o.");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage("§c/setmito <nome>");
                return true;
            }
            final Player p = Bukkit.getServer().getPlayer(args[0]);
            if (p == null) {
                sender.sendMessage("§cPlayer n\u00e3o encontrado.");
                return true;
            }
            Manager_Mito.novoMito(p, Main.plugin.player);
            Main.plugin.getConfig().set("Mito", p.getName());
            Main.plugin.player = p.getName();
            Main.plugin.saveConfig();
            Bukkit.broadcastMessage(Main.plugin.novo.replaceAll("<player>", p.getName()));
            
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("setnpcmito")) {
            if (!sender.hasPermission("mito.admin")) {
                sender.sendMessage("Voc\u00ea n\u00e3o tem permiss\u00e3o.");
                return true;
            }
            Manager_Npc.SetarLocNPC((Player) sender);
            sender.sendMessage("§aVocê setou a localização com sucesso.");
            return true;
        }
        return false;
    }

}
