package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import org.bukkit.entity.Player;

public class Command_banhistory implements UMGCommand
{
    private final UniverseMG plugin = UniverseMG.plugin;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(args.length == 0)
        {
            sender.sendMessage(Messages.MOD_TAG + "Correct usage is /banhistory <player>");
            return true;
        }
        
        if(sender instanceof Player)
        {
            if(Rank.getPlayerRank((Player) sender).getPriority() < this.getRank().getPriority())
            {
                sender.sendMessage(Messages.MSG_NO_PERMS);

                return true;
            }
        }
        
        else if(args.length == 1)
        {
            if(this.plugin.banconfig.getConfig().contains(args[0].toLowerCase()))
            {
                sender.sendMessage(Messages.MOD_TAG + args[0] + " was banned for "
                                   + this.plugin.banconfig.getConfig().getString(args[0].toLowerCase()));
            }
            else
            {
                sender.sendMessage(Messages.MOD_TAG + args[0] + " is not banned.");
            }
        }

        return true;
    }

    @Override
    public Rank getRank() {
        return Rank.MOD;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
