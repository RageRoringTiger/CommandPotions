package me.rageroringtiger.commandpotion;

import me.rageroringtiger.commandpotion.commands.CreatePotion;
import me.rageroringtiger.commandpotion.listeners.PlayerDrinkPotion;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandPotion extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "CommandPotion has loaded.");
        getCommand("cpot").setExecutor(new CreatePotion());
        getServer().getPluginManager().registerEvents(new PlayerDrinkPotion(), this);
        setupConfig();
    }

    public void setupConfig(){
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }
}
