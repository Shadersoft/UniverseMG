package com.Shadersoft.UniverseMG.Commands;

import org.apache.commons.lang.StringUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.Arrays;
import java.util.List;

public class Command_universemg implements UMGCommand
{
    public UniverseMG plugin = UniverseMG.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(args.length != 0)
        {
            return false;
        }

        if(Rank.getSenderRank(sender).getPriority() < this.getRank().getPriority())
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);

            return true;
        }

        String[] lines = { ChatColor.RED + "This server is running " + ChatColor.YELLOW + UniverseMG.plugin.pluginName + ChatColor.RED + ".",
                           ChatColor.RED + "Version: " + ChatColor.YELLOW + UniverseMG.plugin.pluginVersion
                           + ChatColor.RED + ".",
                           ChatColor.RED + "By: " + ChatColor.YELLOW + StringUtils.join(UniverseMG.plugin.pluginAuthors,
                                                                                        ChatColor.RED + ", "
                                                                                        + ChatColor.YELLOW) + ChatColor.RED
                                                                                                            + "." };

        sender.sendMessage(lines);

        return true;
    }

    @Override
    public Rank getRank()
    {
        return Rank.PLAYER;
    }
    @Override
    public List<String> getAliases() 
    {
        return Arrays.asList("umg");
    }

    @Override
    public String getUsage() 
    {
        return "/<command>";
    }

    @Override
    public String getDescription() 
    {
        return "Shows information about the plugin.";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
