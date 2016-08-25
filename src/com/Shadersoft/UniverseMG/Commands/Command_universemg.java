package com.Shadersoft.UniverseMG.Commands;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Command_universemg implements CommandExecutor
{
    public UniverseMG plugin = UniverseMG.plugin;
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
    {
        if(args.length != 0) {return false;}
        if(sender instanceof Player && plugin.rank.getPlayerRank((Player)sender).getPriority() < this.getRank().getPriority())
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);
            return true;
        }
        String[] lines = {
            ChatColor.RED + "This server is running UniverseMG 1.0.",
            ChatColor.RED + "Version: " + ChatColor.YELLOW + UniverseMG.plugin.pluginVersion + ChatColor.RED + ".",
            ChatColor.RED + "By: " + ChatColor.YELLOW + StringUtils.join(UniverseMG.plugin.pluginAuthors, ChatColor.RED + ", " + ChatColor.YELLOW) + ChatColor.RED + "."
        };
        
        sender.sendMessage(lines);
        return true;
    }

    public Rank getRank() {
        return Rank.PLAYER;
    }

}
