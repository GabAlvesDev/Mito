package me.gabriel.mito.manager;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import me.gabriel.mito.Main;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.npc.skin.SkinnableEntity;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Manager_Npc {
	


    public static void setNpcMito(final String p) {
        //get location and load chunks
    	Bukkit.getConsoleSender().sendMessage("§5[MITO] Npc Mito Atualizado!");
        Main.plugin.getnpcs.forEach(NPC::destroy);
        Main.plugin.holograms.forEach(Hologram::delete);
        Location location = Manager_Npc.NPC();
        Chunk c = location.getChunk();
        c.load();

        //Create Npc and Spawn
        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        NPC Mito = registry.createNPC(EntityType.PLAYER, "");
        Main.plugin.getnpcs.add(Mito);
        Mito.spawn(location);
        Mito.data().setPersistent(NPC.PLAYER_SKIN_UUID_METADATA, p);
        Mito.data().setPersistent(NPC.PLAYER_SKIN_USE_LATEST, false);
        Entity npcEntity = Mito.getEntity();
        if (npcEntity instanceof SkinnableEntity) {
            ((SkinnableEntity) npcEntity).getSkinTracker().notifySkinChange(Mito.data().get(NPC.PLAYER_SKIN_USE_LATEST));
        }

        //Create Hologram
        location.add(0.0D, Main.plugin.getConfig().getDouble("Holo.Altura"), 0.0D);
        final String prefix = PermissionsEx.getUser(p).getGroups()[0].getPrefix().replace("&", "§");
        com.gmail.filoghost.holographicdisplays.api.Hologram Mitohd = HologramsAPI.createHologram(Main.plugin,location.add(0, 1.37, 0));
        Mitohd.insertTextLine(0, Main.plugin.getConfig().getString("Holo.Line.1").replace("&", "§"));
        Mitohd.insertTextLine(1, Main.plugin.getConfig().getString("Holo.Line.2").replace("&", "§").replace("<player>", p).replace("<cargo>", prefix));
        Main.plugin.holograms.add(Mitohd);
    }
    
    

    public static void SetarLocNPC(final Player p) {
        Main.plugin.getConfig().set("Locations.NPC.x", p.getLocation().getX());
        Main.plugin.getConfig().set("Locations.NPC.y", p.getLocation().getY());
        Main.plugin.getConfig().set("Locations.NPC.z", p.getLocation().getZ());
        Main.plugin.getConfig().set("Locations.NPC.pitch", p.getLocation().getPitch());
        Main.plugin.getConfig().set("Locations.NPC.yaw", p.getLocation().getYaw());
        Main.plugin.getConfig().set("Locations.NPC.world", p.getLocation().getWorld().getName());
        Main.plugin.saveConfig();
    }
    

    public static Location NPC() {
        final World w = Bukkit.getServer().getWorld(Main.plugin.getConfig().getString("Locations.NPC.world"));
        final double x = Main.plugin.getConfig().getDouble("Locations.NPC.x");
        final double y = Main.plugin.getConfig().getDouble("Locations.NPC.y");
        final double z = Main.plugin.getConfig().getDouble("Locations.NPC.z");
        final Location npc = new Location(w, x, y, z);
        npc.setPitch((float)Main.plugin.getConfig().getDouble("Locations.NPC.pitch"));
        npc.setYaw((float)Main.plugin.getConfig().getDouble("Locations.NPC.yaw"));
        return npc;
    }



}
