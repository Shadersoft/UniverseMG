package com.Shadersoft.UniverseMG.Commands;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Command_addadmin implements CommandExecutor
{
    private final UniverseMG plugin = UniverseMG.plugin;
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
    {
        if(args.length != 2) {return false;}
        if(sender instanceof Player && plugin.rank.getPlayerRank((Player)sender).getPriority() < this.getRank().getPriority())
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if(player == null)
        {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }
        Rank rank = plugin.rank.getRank(args[1].toUpperCase());
        if(rank == Rank.PLAYER)
        {
            sender.sendMessage(ChatColor.RED + "Rank not found!");
            return true;
        }
        plugin.rank.addAdmin(player, rank);
        
        ChatUtils.bCastMsg(ChatColor.RED + sender.getName() + " - Adding " + ChatColor.YELLOW + player.getName() + ChatColor.RED + " to the rank " + ChatColor.YELLOW + rank.getDisplayName());
        return true;
    }

    public Rank getRank() {
        return Rank.OWNER;
    }

}
