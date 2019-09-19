package me.gabriel.mito.listners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.gabriel.mito.Main;

public class JoinListner implements Listener{
	

    @EventHandler
    private void onLogin(final PlayerJoinEvent e) {
        if (e.getPlayer().getName().toLowerCase().equalsIgnoreCase(Main.plugin.getConfig().getString("Mito").toLowerCase().trim()) && Main.plugin.logou.length() > 1) {
            Bukkit.broadcastMessage(Main.plugin.logou.replaceAll("<player>", e.getPlayer().getName()));
        }
    }

}
