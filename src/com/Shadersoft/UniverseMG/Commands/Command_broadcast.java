package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.Bukkit;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import java.util.Arrays;
import java.util.List;

public class Command_broadcast implements UMGCommand
{
    private final UniverseMG plugin = UniverseMG.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(Rank.getSenderRank(sender).getPriority() < this.getRank().getPriority())
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);

            return true;
        }
        
        if(args.length == 0)
        {
            sender.sendMessage(Messages.MOD_TAG + "Usage: /broadcast <message>");
            return true;
        }
        
        if(Rank.getRankFromName(args[0]).getPriority()
            > Rank.getRankFromName(sender.getName()).getPriority())
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);

            return true;
        }
        
        String message = StringUtils.join(args, " ");        
        ChatUtils.announce(Rank.PLAYER, message);
  

        return true;
    }

    @Override
    public Rank getRank()
    {
        return Rank.ADMIN;
    }
    
    @Override
    public List<String> getAliases() 
    {
        return Arrays.asList("bc","announce","an");
    }

    @Override
    public String getUsage() 
    {
        return "/<command> <message>";
    }

    @Override
    public String getDescription() 
    {
        return "Broadcasts a message to the server.";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
