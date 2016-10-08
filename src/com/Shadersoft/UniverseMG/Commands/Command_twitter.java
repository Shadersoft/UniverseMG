package com.Shadersoft.UniverseMG.Commands;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import java.util.Arrays;
import java.util.List;

public class Command_twitter implements UMGCommand
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

        String[] lines = {"Follow our twitter at " + ChatColor.YELLOW + "@" + plugin.config.getString("twitter"),"link: " + ChatColor.YELLOW + "http://twitter.com/" + plugin.config.getString("twitter").replaceFirst("@", "")};

        for(String line : lines)
        {
            ChatUtils.msg(sender,line);
        }

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
        return Arrays.asList("twitterhandle");
    }

    @Override
    public String getUsage() 
    {
        return "/<command>";
    }

    @Override
    public String getDescription() 
    {
        return "Shows twitter info.";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
