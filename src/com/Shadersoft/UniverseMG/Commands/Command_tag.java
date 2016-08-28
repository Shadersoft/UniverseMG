package com.Shadersoft.UniverseMG.Commands;

import org.apache.commons.lang.StringUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import java.util.ArrayList;
import java.util.List;
import static org.bukkit.Bukkit.getOfflinePlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Command_tag implements UMGCommand
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
        
        if(args.length != 1)
        {
            return false;
        }
        
        if(args[0].equalsIgnoreCase("clean"))
        {
            ChatUtils.bCastMsg(Messages.MOD_TAG + sender.getName() + " cleaned all tags.");
            for(Player tagPlr : plugin.prefixes.keySet())
            {
                String newTag = plugin.prefixes.get(tagPlr).replace('§','&').replace("&0", "").replace("&k", "").replace("&o", "").replace("&m", "").replace("&n", "");
                plugin.prefixes.put(tagPlr, newTag);
            }
        }
        else if(args[0].equalsIgnoreCase("clear"))
        {
            plugin.prefixes.clear();
        }
        else
        {
            plugin.prefixes.put((Player)sender, ChatUtils.colorize(StringUtils.join(args, " ")));
        }
        
        return true;
    }

    @Override
    public Rank getRank()
    {
        return Rank.MOD;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
