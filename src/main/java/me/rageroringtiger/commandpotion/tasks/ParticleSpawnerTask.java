package me.rageroringtiger.commandpotion.tasks;

import me.rageroringtiger.commandpotion.CommandPotion;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleSpawnerTask extends BukkitRunnable {
    Plugin plugin = CommandPotion.getPlugin(CommandPotion.class);

    String potion;
    Player p;

    public ParticleSpawnerTask(Player p, String potion){
        this.p = p;
        this.potion = potion;
    }

    @Override
    public void run() {
        p.getWorld().spawnParticle(Particle.valueOf(plugin.getConfig().getString(potion + ".particle")), p.getLocation(), 20, 0.5, 0.5, 0.5);
    }
}
