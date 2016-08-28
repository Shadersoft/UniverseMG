package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Command_maintenancemode implements UMGCommand
{
    public UniverseMG plugin = UniverseMG.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(Rank.getSenderRank(sender).getPriority() < this.getRank().getPriority())
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);

            return true;
        }
        
        if(args.length < 1)
        {
            return false;
        }
        
        switch(args[0].toUpperCase())
        {
            case "ON":
            {
                plugin.config.set("maintenance_mode", true);
                plugin.saveConfig();
                for(Player p : Bukkit.getOnlinePlayers())
                {
                    p.kickPlayer("Server is activating Maintenance mode.");
                }
                return true;
            }
            case "OFF":
            {
                plugin.config.set("maintenance_mode", false);
                plugin.saveConfig();
                return true;
            }
        }
        return false;
    }

    @Override
    public Rank getRank()
    {
        return Rank.ADMIN;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
