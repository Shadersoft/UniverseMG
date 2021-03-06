package com.Shadersoft.UniverseMG.Commands.Chats;

import com.Shadersoft.UniverseMG.Commands.UMGCommand;
import org.apache.commons.lang.StringUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.ChatType;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Command_ownerchat implements UMGCommand
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

        if(!(sender instanceof Player))
        {
            sender.sendMessage(Messages.MSG_PLAYER_ONLY);
            
            return true;
        }
        
        if(args.length == 0)
        {
            if(plugin.playerChats.containsKey((Player)sender))
            {
                plugin.playerChats.put((Player)sender, ChatType.OWNER);
                sender.sendMessage(ChatColor.RED + "Toggled ownerchat ON");
            }
            else
            {
                plugin.playerChats.remove((Player)sender);
                sender.sendMessage(ChatColor.RED + "Toggled ownerchat OFF");
            }
            
            return true;
        }
        
        String message = StringUtils.join(args, " ");
        ChatUtils.specialChatMessage((Player) sender, message, ChatType.OWNER);
        
        return true;
    }

    @Override
    public Rank getRank()
    {
        return Rank.OWNER;
    }
    
    @Override
    public List<String> getAliases() 
    {
        return Arrays.asList("oc","ochat");
    }

    @Override
    public String getUsage() 
    {
        return "/<command> [message]";
    }

    @Override
    public String getDescription() 
    {
        return "Use the Owner chat.";
    }
}

//~ Formatted by Jindent --- http://www.jindent.com
