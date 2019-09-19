package me.gabriel.mito.listners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import me.gabriel.mito.Main;


public class ChatListner implements Listener{
	

    @EventHandler
    public void Chat(final ChatMessageEvent e) {
        if (e.getTags().contains("msmito") && e.getSender().getName().equals(Main.plugin.player)) {
            final String tag = Main.plugin.tag.replace("&", "§");
            e.setTagValue("mito", tag);
        }
    }


}
