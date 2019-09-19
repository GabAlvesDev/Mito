package me.gabriel.mito.manager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.gabriel.mito.Main;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Manager_Mito {
	
    public static void novoMito(final Player mito, final String antigo) {
        for (final String comandos2 : Main.plugin.getConfig().getStringList("Config.Comandos_Mito_Antigo")) {
            Main.plugin.getServer().dispatchCommand((CommandSender)Main.plugin.getServer().getConsoleSender(), comandos2.replaceAll("<antigo-mito>", antigo));
        }
        for (final String cmds : Main.plugin.getConfig().getStringList("Config.Comandos_Mito_Novo")) {
            Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), cmds.replaceAll("<player>", mito.getName()));
        }
    	Manager_Npc.setNpcMito(mito.getName());
        mito.playEffect(mito.getLocation(), Effect.MOBSPAWNER_FLAMES, 3);
        mito.getWorld().strikeLightningEffect(mito.getLocation().add(2.0, 0.0, 2.0));
        mito.getWorld().strikeLightningEffect(mito.getLocation().add(-2.0, 0.0, -2.0));
        mito.getWorld().strikeLightningEffect(mito.getLocation().add(2.0, 0.0, -2.0));
        mito.getWorld().strikeLightningEffect(mito.getLocation().add(-2.0, 0.0, 2.0));
        mito.getWorld().strikeLightningEffect(mito.getLocation().add(2.5, 0.0, 0.0));
        mito.getWorld().strikeLightningEffect(mito.getLocation().add(-2.5, 0.0, 0.0));
        mito.getWorld().strikeLightningEffect(mito.getLocation().add(0.0, 0.0, 2.5));
        mito.getWorld().strikeLightningEffect(mito.getLocation().add(0.0, 0.0, -2.5));
		
        final ArrayList<Entity> ents = new ArrayList<Entity>();
        for (int i = 0; i < 10; ++i) {
            ents.add(mito.getWorld().spawnEntity(mito.getLocation(), EntityType.BAT));
        }
        final ArrayList<Entity> ents2 = ents;
        Bukkit.getServer().getScheduler().runTaskLater(Main.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                for (final Entity e : ents2) {
                    e.remove();
                }
            }
        }, 30L);
    }

}
