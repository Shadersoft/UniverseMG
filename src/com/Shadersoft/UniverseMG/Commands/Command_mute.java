package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Command_mute implements UMGCommand
{
    public UniverseMG plugin = UniverseMG.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(args.length < 1)
        {
            return false;
        }
        
        Player getPlr = getPlayer(args[0]);
        if(getPlr != null)
        {
            if(!plugin.muted.contains(getPlr))
            {
                ChatUtils.bCastMsg(ChatColor.RED + sender.getName() + " muted " + getPlr.getName());
                plugin.muted.add(getPlr);
                return true;
            }
        }
        else
        {
            switch(args[0].toUpperCase())
            {
                case "LIST":
                {
                    if(args.length != 1) {return true;}
                    sender.sendMessage(ChatColor.RED + "Muted Players:");
                    sender.sendMessage(ChatColor.YELLOW + StringUtils.join(plugin.muted, ChatColor.RED + ", " + ChatColor.YELLOW));
                    return true;
                }
                case "OFF":
                {
                    if(args.length != 2) {return true;}
                    if(!plugin.muted.contains((Player)sender))
                    {
                        sender.sendMessage(ChatColor.RED + "Player is not muted.");
                        return true;
                    }
                    ChatUtils.bCastMsg(ChatColor.RED + sender.getName() + " is un-muting " + getPlr.getName());
                    plugin.muted.remove(getPlr);
                    return true;
                }
                case "PURGE":
                {
                    if(args.length != 1) {return true;}
                    ChatUtils.bCastMsg(ChatColor.RED + sender.getName() + " is un-muting all players. .");
                    for(Player p : player.muted)
                    {
                        plugin.muted.remove(getPlr);
                    }
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public Rank getRank()
    {
        return Rank.ADMIN;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
