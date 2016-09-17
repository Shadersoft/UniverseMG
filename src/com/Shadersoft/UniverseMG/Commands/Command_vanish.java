package com.Shadersoft.UniverseMG.Commands;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import static com.Shadersoft.UniverseMG.utils.ChatUtils.msg;
import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Command_vanish implements UMGCommand
{
    public UniverseMG plugin = UniverseMG.plugin;
    
    @Override
    public Rank getRank() 
    {
        return Rank.MODERATOR;
    }

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
            if(args.length != 0)
            {
                return false;
            }
            
            if(!plugin.vanishPlayers.contains((Player)sender))
            {
                plugin.vanishPlayers.remove((Player)sender);
                msg(sender, "Vanish is now &eOFF");
                ((Player)sender).showPlayer((Player)sender);
                ((Player)sender).setPlayerListName(Rank.getSenderRank(sender).getDisplayTag() + Rank.getSenderRank(sender).getColor() + sender.getName());
                ChatUtils.bCastMsg(ChatColor.YELLOW + sender.getName() + " joined the game.");
                return true;
            }
            else
            {
                plugin.vanishPlayers.add((Player)sender);
                msg(sender, "Vanish is now &eON");
                ((Player)sender).hidePlayer((Player)sender);
                ChatUtils.bCastMsg(ChatColor.YELLOW + sender.getName() + " left the game.");
                return true;
            }
        }
        
        switch(args[0].toUpperCase())
        {
            case "ON":
            {
                if(plugin.vanishPlayers.contains((Player)sender))
                {
                    //Do nothing.
                    msg(sender, "Vanish is now &eON");
                    ((Player)sender).hidePlayer((Player)sender);
                    return true;
                }
                else
                {
                    plugin.vanishPlayers.add((Player)sender);
                    msg(sender, "Vanish is now &eON");
                    ((Player)sender).hidePlayer((Player)sender);
                    ChatUtils.bCastMsg(ChatColor.YELLOW + sender.getName() + " left the game.");
                    return true;
                }
            }
            case "OFF":
            {
                if(!plugin.vanishPlayers.contains((Player)sender))
                {
                    //Do nothing.
                    msg(sender, "Vanish is now &eOFF");
                    ((Player)sender).showPlayer((Player)sender);
                    return true;
                }
                else
                {
                    ((Player)sender).setPlayerListName(Rank.getSenderRank(sender).getDisplayTag() + Rank.getSenderRank(sender).getColor() + sender.getName());
                    plugin.vanishPlayers.remove((Player)sender);
                    msg(sender, "Vanish is now &eOFF");
                    ((Player)sender).showPlayer((Player)sender);
                    ChatUtils.bCastMsg(ChatColor.YELLOW + sender.getName() + " joined the game.");
                    return true;
                }
            }
        }
        
        return true;
    }
    
    @Override
    public List<String> getAliases() 
    {
        return Arrays.asList("v","admin");
    }

    @Override
    public String getUsage() 
    {
        return "/<command> [on|off]";
    }

    @Override
    public String getDescription() 
    {
        return "Hides you.";
    }
}
