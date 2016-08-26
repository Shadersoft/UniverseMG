package com.Shadersoft.UniverseMG.Commands;

import org.apache.commons.lang.StringUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.ArrayList;
import java.util.List;

public class Command_adminlist implements CommandExecutor
{
    public UniverseMG plugin = UniverseMG.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(args.length != 1)
        {
            return false;
        }
        switch(args[0].toUpperCase())
        {
            case "LIST":
            {
                List<String> admins = new ArrayList();
                
                for(Player p : plugin.adminList.keySet())
                {
                    Rank rank = plugin.adminList.get(p);
                    admins.add(rank.getColor() + p.getName());
                }
                
                sender.sendMessage(StringUtils.join(admins, ChatColor.RED + ", "));
            }
        }
        return true;
    }

    public Rank getRank()
    {
        return Rank.PLAYER;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
