package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import java.util.Arrays;
import java.util.List;
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
                    getPlr = Bukkit.getPlayer(args[1]);
                    if(!plugin.muted.contains(getPlr))
                    {
                        sender.sendMessage(ChatColor.RED + "Player is not muted.");
                        return true;
                    }
                    ChatUtils.bCastMsg(ChatColor.RED + sender.getName() + " unmuted " + getPlr.getName());
                    plugin.muted.remove(getPlr);
                    return true;
                }
                case "PURGE":
                {
                    if(args.length != 1) {return true;}
                    ChatUtils.bCastMsg(ChatColor.RED + sender.getName() + " is un-muting all players.");
                    for(Player p : plugin.muted)
                    {
                        plugin.muted.remove(p);
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
    
    @Override
    public List<String> getAliases() 
    {
        return Arrays.asList("stfu");
    }

    @Override
    public String getUsage() 
    {
        return "/<command> <<player>|list|off|purge> [player]";
    }

    @Override
    public String getDescription() 
    {
        return "Mute a player.";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
