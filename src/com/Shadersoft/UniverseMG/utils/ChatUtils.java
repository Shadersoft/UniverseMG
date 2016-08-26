package com.Shadersoft.UniverseMG.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtils
{
    public static void bCastMsg(String string)
    {
        for (Player p : Bukkit.getOnlinePlayers())
        {
            p.sendMessage(string);
        }
    }

    public static String colorize(String string)
    {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
