package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Command_maintenance implements UMGCommand
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
                    p.kickPlayer("The server is currently in maintenance mode. If you feel as if you should be added to the whitelist. ");
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
    
    @Override
    public List<String> getAliases() 
    {
        return Arrays.asList("closeserver","adminmode");
    }

    @Override
    public String getUsage() 
    {
        return "/<command> <on|off>";
    }

    @Override
    public String getDescription() 
    {
        return "Toggles maintenance on/off.";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
