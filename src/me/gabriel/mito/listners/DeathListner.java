package me.gabriel.mito.listners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.gabriel.mito.Main;
import me.gabriel.mito.manager.Manager_Mito;

public class DeathListner implements Listener{

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onDeath(final PlayerDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            final Player killed = e.getEntity();
            final Player killer = e.getEntity().getKiller();
            if (Main.plugin.player.toLowerCase().trim().equalsIgnoreCase(killed.getName().toLowerCase())) {
                Main.plugin.getConfig().set("Mito", (Object)killer.getName());
                Main.plugin.player = killer.getName();
                Main.plugin.saveConfig();
                Main.plugin.reloadConfig();
                Main.plugin.getServer().broadcastMessage(Main.plugin.novo.replaceAll("<player>", killer.getName()));
                Manager_Mito.novoMito(killer, killed.getName());
            }
        }
    }

}
