/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Shadersoft.UniverseMG.utils;

import com.Shadersoft.UniverseMG.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author malmar03
 */
public class ChatUtils 
{
    public static String colorize(String string){return ChatColor.translateAlternateColorCodes('&', string);}
    public static void bCastMsg(String string)
    {
        for(Player p : Bukkit.getOnlinePlayers())
        {
            p.sendMessage(string);
        }
    }
}
