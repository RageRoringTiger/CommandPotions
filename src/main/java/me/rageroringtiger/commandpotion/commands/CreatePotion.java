package me.rageroringtiger.commandpotion.commands;

import me.rageroringtiger.commandpotion.CommandPotion;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Objects;

public class CreatePotion implements CommandExecutor {
    Plugin plugin = CommandPotion.getPlugin(CommandPotion.class);
    ItemStack potion = new ItemStack(Material.POTION);
    PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (sender.hasPermission("commandpotions.createpotion")) {
                if (args[0].equals(null)){
                    sender.sendMessage(ChatColor.RED + "Invalid arguments. /cpot <potion> <player>");
                }
                if (args[0].equalsIgnoreCase("reload")){
                    plugin.reloadConfig();
                    sender.sendMessage(ChatColor.GREEN + "Command Potions has reloaded successfully.");
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Command Potions has reload successfully.");
                    return false;
                }
                if (args.length >= 1) {
                    if (plugin.getConfig().getString("potions." + args[0]) == null){
                        sender.sendMessage(ChatColor.RED + "That potion doesn't exist!");
                        return false;
                    }
                    Player p = (Player) sender;
                    potionMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("potions." + args[0] + ".display-name")));
                    if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("aqua")) {
                        potionMeta.setColor(Color.AQUA);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("black")) {
                        potionMeta.setColor(Color.BLACK);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("blue")) {
                        potionMeta.setColor(Color.BLUE);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("fuchsia")) {
                        potionMeta.setColor(Color.FUCHSIA);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("gray")) {
                        potionMeta.setColor(Color.GRAY);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("green")) {
                        potionMeta.setColor(Color.GREEN);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("lime")) {
                        potionMeta.setColor(Color.LIME);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("maroon")) {
                        potionMeta.setColor(Color.MAROON);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("navy")) {
                        potionMeta.setColor(Color.NAVY);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("olive")) {
                        potionMeta.setColor(Color.OLIVE);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("orange")) {
                        potionMeta.setColor(Color.ORANGE);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("purple")) {
                        potionMeta.setColor(Color.PURPLE);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("red")) {
                        potionMeta.setColor(Color.RED);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("silver")) {
                        potionMeta.setColor(Color.SILVER);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("teal")) {
                        potionMeta.setColor(Color.TEAL);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("white")) {
                        potionMeta.setColor(Color.WHITE);
                    } else if (plugin.getConfig().getString("potions." + args[0] + ".color").equalsIgnoreCase("yellow")) {
                        potionMeta.setColor(Color.YELLOW);
                    } else {
                        potionMeta.setColor(Color.BLUE);
                    }
                    if (plugin.getConfig().getInt("potions." + args[0] + ".custom-model-data") > -1) {
                        potionMeta.setCustomModelData(plugin.getConfig().getInt("potions." + args[0] + ".custom-model-data"));
                    }
                    if (plugin.getConfig().getBoolean("main-config.show-time-left")) {
                        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.LUCK, plugin.getConfig().getInt("potions." + args[0] + ".time"), 0, plugin.getConfig().getBoolean("main-config.ambient")), true);
                    }

                    ArrayList<String> potion_lore = new ArrayList<>();
                    potion_lore.add("");
                    potion_lore.add(ChatColor.DARK_PURPLE + "Command(s) on drink:");
                    for (String command_lore : plugin.getConfig().getStringList("potions." + args[0] + ".command-on-drink")){
                        potion_lore.add(ChatColor.GOLD + command_lore);
                    }
                    potion_lore.add("");
                    potion_lore.add(ChatColor.DARK_PURPLE + "Command(s) on expire.");
                    for (String command_lore : plugin.getConfig().getStringList("potions." + args[0] + ".command-on-expire")){
                        potion_lore.add(ChatColor.GOLD + command_lore);
                    }
                    potionMeta.setLore(potion_lore);
                    potion.setItemMeta(potionMeta);
                    if (args.length > 1) {
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if (target instanceof Player) {
                            target.getInventory().addItem(potion);
                            target.sendMessage(ChatColor.GREEN + "You were given a command potion.");
                            p.sendMessage(ChatColor.GREEN + target.getDisplayName() + " was given a command potion.");
                        } else {
                            sender.sendMessage(ChatColor.RED + "That player is not online.");
                        }
                    } else {
                        p.getInventory().addItem(potion);
                        p.updateInventory();
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Useage: /cpot <potion> <player>");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use that command.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You must be a player.");
        }
        return false;
    }
}
