package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

public class Command_addadmin implements UMGCommand
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
        
        if(args.length != 2)
        {
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);

        if(player == null)
        {
            sender.sendMessage(ChatColor.RED + "Player not found!");

            return true;
        }

        Rank rank = Rank.getRank(args[1].toUpperCase());

        if(rank == Rank.PLAYER)
        {
            sender.sendMessage(ChatColor.RED + "Rank not found!");

            return true;
        }

        Rank.addAdmin(player, rank);
        ChatUtils.bCastMsg(ChatColor.RED + sender.getName() + " - Adding " + ChatColor.YELLOW + player.getName()
                           + ChatColor.RED + " to the rank " + ChatColor.YELLOW + rank.getDisplayName());

        return true;
    }

    @Override
    public Rank getRank()
    {
        return Rank.DEV;
    }

    @Override
    public List<String> getAliases() 
    {
        return Arrays.asList("");
    }

    @Override
    public String getUsage() 
    {
        return "/<command> <player> <rank>";
    }

    @Override
    public String getDescription() 
    {
        return "Adds an admin.";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
