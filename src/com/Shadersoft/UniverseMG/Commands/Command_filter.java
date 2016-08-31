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

public class Command_filter implements UMGCommand
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
        if(args.length != 1)
        {
            return false;
        }

        switch(args[0].toUpperCase())
        {
            case "PROFANITY":
            case "SWEAR":
            case "SWEARING":
            {
                if(plugin.swearPlayers.contains((Player)sender))
                {
                    plugin.swearPlayers.remove((Player)sender);
                    sender.sendMessage(ChatColor.RED + "Profanity filter is now " + ChatColor.YELLOW + "OFF");
                    return true;
                }
                else
                {
                    plugin.swearPlayers.add((Player)sender);
                    sender.sendMessage(ChatColor.RED + "Profanity filter is now " + ChatColor.YELLOW + "ON");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Rank getRank()
    {
        return Rank.PLAYER;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
