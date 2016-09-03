package com.Shadersoft.UniverseMG.utils;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.ChatType;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtils
{
    private static UniverseMG plugin = UniverseMG.plugin;
    
    public static void msg(Player player, String string)
    {
        player.sendMessage(ChatColor.RED + string);
    }
    public static void msg(CommandSender sender, String string)
    {
        sender.sendMessage(ChatColor.RED + string);
    }
    
    public static String capitalize(String string)
    {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase(); 
    }
    
    public static void announce(Rank rankAtLeast, String string)
    {
        for (CommandSender p : Bukkit.getOnlinePlayers())
        {
            if(Rank.getSenderRank(p).getPriority() >= rankAtLeast.getPriority())
            {
                p.sendMessage(Messages.ANNOUNCEMENT + string);
            }
        }
    }
    
    public static void bCastMsg(String string)
    {
        for (Player p : Bukkit.getOnlinePlayers())
        {
            p.sendMessage(string);
        }
    }
    
    public static void filterCastMsg(String string)
    {
        for (Player p : Bukkit.getOnlinePlayers())
        {
            List<String> swearWords = plugin.config.getStringList("filter.swearwords");
            String message = string;
            if(plugin.swearPlayers.contains(p))
            {
                for(String word : swearWords)
                {
                    message = message.replace(word, "*@$%");
                }
            }
            p.sendMessage(message);
        }
    }

    public static String colorize(String string)
    {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
    
    public static String grammarize(String string) //For the grammar nazis
    {
        if(string.split(" ").length < 2)
        {
            return string;
        }
        
        if(string.endsWith(".")||string.endsWith("!")||string.endsWith("?"))
        {
            return string.substring(0, 1).toUpperCase() + string.substring(1);
        }
        
        return string.substring(0, 1).toUpperCase() + string.substring(1) + ".";
    }
    
    public static void specialChatMessage(Player sender, String string, ChatType type)
    {
        switch(type)
        {
            case HELPER:
            {
                if(Rank.getRankFromName(sender.getName()).getPriority() < Rank.HELPER.getPriority())
                {
                    sender.sendMessage(Messages.MSG_NO_PERMS);
                    return;
                }
                for(Player player : Bukkit.getOnlinePlayers())
                {
                    if(Rank.getRankFromName(player.getName()).getPriority() == Rank.HELPER.getPriority())
                    {
                        sender.sendMessage(colorize("&e[&bHELPER&e] &5" + sender.getName() + ": " + (string)));
                        return;
                    }
                }
            }
            case MODERATOR:
            {
                if(Rank.getRankFromName(sender.getName()).getPriority() < Rank.MODERATOR.getPriority())
                {
                    sender.sendMessage(Messages.MSG_NO_PERMS);
                    return;
                }
                for(Player player : Bukkit.getOnlinePlayers())
                {
                    if(Rank.getRankFromName(player.getName()).getPriority() == Rank.MODERATOR.getPriority())
                    {
                        sender.sendMessage(colorize("&e[&aMODERATOR&e] &a" + sender.getName() + ": " + (string)));
                        return;
                    }
                }
            }
            case ADMIN:
            {
                if(Rank.getRankFromName(sender.getName()).getPriority() < Rank.ADMIN.getPriority())
                {
                    sender.sendMessage(Messages.MSG_NO_PERMS);
                    return;
                }
                for(Player player : Bukkit.getOnlinePlayers())
                {
                    if(Rank.getRankFromName(player.getName()).getPriority() >= Rank.ADMIN.getPriority())
                    {
                        sender.sendMessage(colorize("&e[&6ADMIN&e] &6" + sender.getName() + ": " + (string)));
                        return;
                    }
                }
            }
            case DEVELOPER:
            {
                if(Rank.getRankFromName(sender.getName()).getPriority() < Rank.DEV.getPriority() || Rank.getRankFromName(sender.getName()).getPriority() < Rank.MAINDEV.getPriority())
                {
                    sender.sendMessage(Messages.MSG_NO_PERMS);
                    return;
                }
                for(Player player : Bukkit.getOnlinePlayers())
                {
                    if(Rank.getRankFromName(player.getName()).getPriority() == Rank.DEV.getPriority() || Rank.getRankFromName(player.getName()).getPriority() == Rank.MAINDEV.getPriority())
                    {
                        sender.sendMessage(colorize("&e[&5DEV&e] &5" + sender.getName() + ": " + (string)));
                        return;
                    }
                }
            }
            case OWNER:
            {
                if(Rank.getRankFromName(sender.getName()).getPriority() < Rank.OWNER.getPriority())
                {
                    sender.sendMessage(Messages.MSG_NO_PERMS);
                    return;
                }
                for(Player player : Bukkit.getOnlinePlayers())
                {
                    if(Rank.getRankFromName(player.getName()).getPriority() == Rank.OWNER.getPriority())
                    {
                        sender.sendMessage(colorize("&e[&4OWNER&e] &4" + sender.getName() + ": " + (string)));
                        return;
                    }
                }
            }
        }
        
        
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
