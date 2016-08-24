package com.Shadersoft.UniverseMG.Commands;

import com.Shadersoft.UniverseMG.Commands.*;
import com.Shadersoft.UniverseMG.UniverseMG;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;


public class Command_universemg implements UMGCommand
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] strings) 
    {
        String[] lines = {
            ChatColor.RED + "This server is running UniverseMG 1.0.",
            ChatColor.RED + "Version: " + ChatColor.YELLOW + UniverseMG.plugin.pluginVersion + ChatColor.RED + ".",
            ChatColor.RED + "By: " + ChatColor.YELLOW + StringUtils.join(UniverseMG.plugin.pluginAuthors, ChatColor.RED + ", " + ChatColor.YELLOW) + ChatColor.RED + "."
        };
        
        sender.sendMessage(lines);
        return true;
    }

}
