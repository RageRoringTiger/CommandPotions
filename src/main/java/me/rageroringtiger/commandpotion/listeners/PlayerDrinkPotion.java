package me.rageroringtiger.commandpotion.listeners;

import me.rageroringtiger.commandpotion.CommandPotion;
import me.rageroringtiger.commandpotion.tasks.ParticleSpawnerTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class PlayerDrinkPotion implements Listener {
    private Plugin plugin = CommandPotion.getPlugin(CommandPotion.class);
    @EventHandler
    public void onPlayerDrinkPotion(PlayerItemConsumeEvent e){
        Player p = e.getPlayer();
        for (String key : plugin.getConfig().getConfigurationSection("potions").getKeys(false)){
            String potion = "potions." + key;
            if (!key.equals("DO-NOT-REMOVE")) {
                if (e.getItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(potion + ".display-name")))){
                    for (String command : plugin.getConfig().getStringList(potion + ".command-on-drink")){
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", p.getName()));
                    }
                    if (!plugin.getConfig().getBoolean(potion + ".empty-after-drink")){
                        p.getInventory().getItemInMainHand().setAmount(0);
                        p.getInventory().addItem(e.getItem());
                    }

                    BukkitTask task = new ParticleSpawnerTask(p, potion).runTaskTimerAsynchronously(plugin, 0L, plugin.getConfig().getLong("particle-config.rate"));

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                        for (String command : plugin.getConfig().getStringList(potion + ".command-on-expire")){
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", p.getName()));
                        }
                        task.cancel();
                    }, plugin.getConfig().getLong(potion + ".time"));
                }
            }
        }
    }
}
