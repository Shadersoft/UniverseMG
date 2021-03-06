package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.Ranks.RankType;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;

public class Command_removeadmin implements UMGCommand
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

        Player player = Bukkit.getPlayer(args[0]);

        if(player == null)
        {
            sender.sendMessage(ChatColor.RED + "Player not found!");

            return true;
        }
        
        if(Rank.getRankFromName(player.getName()).getType() != RankType.STAFF)
        {
            sender.sendMessage(ChatColor.RED + "This player is not a staff member.");
            
            return true;
        }

        ChatUtils.bCastMsg(ChatColor.RED + sender.getName() + " - Removed " + ChatColor.YELLOW + player.getName()
                           + ChatColor.RED + " from the rank " + ChatColor.YELLOW + Rank.getRankFromName(player.getName()));
        Rank.removeAdmin(player);
        player.setOp(false);

        return true;
    }

    @Override
    public Rank getRank()
    {
        return Rank.OWNER;
    }
    @Override
    public List<String> getAliases() 
    {
        return Arrays.asList("");
    }

    @Override
    public String getUsage() 
    {
        return "/<command> <player>";
    }

    @Override
    public String getDescription() 
    {
        return "Removes an admin.";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
