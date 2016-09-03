/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Shadersoft.UniverseMG.Coins;

import com.Shadersoft.UniverseMG.Coins.*;
import com.Shadersoft.UniverseMG.UniverseMG;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 *
 * @author malmar03
 */
public class Coins 
{
    private static UniverseMG plugin = UniverseMG.plugin;
    private static FileConfiguration config = plugin.coinconfig.getConfig();
    
    public static int get(Player player)
    {
        if(config.contains(player.getName()))return config.getInt(player.getName());
        else return 0;
    }
    
    public static boolean hasEnough(Player player, int amount)
    {
        if(config.contains(player.getName()))return config.getInt(player.getName()) >= amount;
        else return false;
    }
    
    public static void initPlr(Player player)
    {
        if(!config.contains(player.getName()))config.set(player.getName(), 0);
        plugin.coinconfig.saveConfig();
    }
    
    public static void add(Player player, int amount)
    {
        if(config.contains(player.getName()))
        {
            int old = get(player);
            config.set(player.getName(), old + amount);
        }
        plugin.coinconfig.saveConfig();
    }
    
    public static void remove(Player player, int amount)
    {
        if(config.contains(player.getName()))
        {
            int old = get(player);
            config.set(player.getName(), old - amount);
        }
        plugin.coinconfig.saveConfig();
    }
    
    public static void set(Player player, int amount)
    {
        config.set(player.getName(), amount);
        plugin.coinconfig.saveConfig();
    }
    
    public static void pay(Player sender, Player reciever, int amount)
    {
        if(hasEnough(sender, amount))
        {
            remove(sender, amount);
            add(reciever, amount);
        }
    }
}
