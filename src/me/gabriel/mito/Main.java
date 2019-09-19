package me.gabriel.mito;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import me.gabriel.mito.commands.Comando_Mito;
import me.gabriel.mito.listners.ChatListner;
import me.gabriel.mito.listners.DeathListner;
import me.gabriel.mito.listners.JoinListner;
import me.gabriel.mito.manager.Manager_Npc;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

public class Main extends JavaPlugin{

	public static Main plugin;
    public String novo;
    public String atual;
    public String atual2;
    public String logou;
    public String tag;
    public String player;
    public ArrayList<NPC> getnpcs = new ArrayList();
    public ArrayList<Hologram> holograms = new ArrayList();

    public Main() {
        this.novo = "";
        this.atual = "";
        this.atual2 = "";
        this.logou = "";
        this.tag = "";
        this.player = "";
    }
	
	public void onEnable() {
		Main.plugin = this;
		this.saveDefaultConfig();
        this.getServer().getPluginCommand("mito").setExecutor((CommandExecutor)new Comando_Mito());
        this.getServer().getPluginCommand("setmito").setExecutor((CommandExecutor)new Comando_Mito());
        this.getServer().getPluginCommand("setnpcmito").setExecutor((CommandExecutor)new Comando_Mito());
        this.getServer().getPluginManager().registerEvents((Listener)new DeathListner(), this);
        this.getServer().getPluginManager().registerEvents((Listener)new JoinListner(), this);
        this.getServer().getPluginManager().registerEvents((Listener)new ChatListner(), this);
        this.novo = this.getConfig().getString("Mensagens.Novo").replaceAll("&", "§");
        this.atual = this.getConfig().getString("Mensagens.Atual").replaceAll("&", "§");
        this.atual2 = this.getConfig().getString("Mensagens.Atual2");
        this.logou = this.getConfig().getString("Mensagens.Logou").replaceAll("&", "§");
        this.tag = this.getConfig().getString("Tag").replaceAll("&", "§");
        this.player = this.getConfig().getString("Mito");
        Manager_Npc.setNpcMito(player);
	}
	
	public void onDisable() {
        getnpcs.forEach(NPC::destroy);
        holograms.forEach(Hologram::delete);
	}

}
