package com.Shadersoft.UniverseMG.Commands;

import com.Shadersoft.UniverseMG.Commands.*;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Command_addadmin implements UMGCommand
{
    private UniverseMG plugin = UniverseMG.plugin;
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
    {
        if(args.length != 2) {return false;}
        if(plugin.rank.getPlayerRank((Player)sender).getPriority() < this.getRank().getPriority())
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
            return true;
        }
        sender.sendMessage("it works"); // TODO Code here
        return true;
    }

    @Override
    public Rank getRank() {
        return Rank.OWNER;
    }

}
