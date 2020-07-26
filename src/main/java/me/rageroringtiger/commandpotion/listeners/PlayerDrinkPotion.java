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
        p.sendMessage("1");
        for (String key : plugin.getConfig().getConfigurationSection("potions").getKeys(false)){
            String potion = "potions." + key;
            p.sendMessage("2");
            if (!key.equals("DO-NOT-REMOVE")) {
                p.sendMessage("3");
                if (e.getItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(potion + ".display-name")))){
                    p.sendMessage("4");
                    for (String command : plugin.getConfig().getStringList(potion + ".command-on-drink")){
                        p.sendMessage("5");
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", p.getName()));
                    }
                    if (!plugin.getConfig().getBoolean(potion + ".empty-after-drink")){
                        p.getInventory().getItemInMainHand().setAmount(0);
                        p.getInventory().addItem(e.getItem());
                    }

                    BukkitTask task = new ParticleSpawnerTask(p, potion).runTaskTimerAsynchronously(plugin, 0L, plugin.getConfig().getLong("particle-config.rate"));
                    p.sendMessage("6");

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                        for (String command : plugin.getConfig().getStringList(potion + ".command-on-expire")){
                            p.sendMessage("7");
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", p.getName()));
                        }
                        task.cancel();
                    }, plugin.getConfig().getLong(potion + ".time"));
                }
            }
        }
    }
}
