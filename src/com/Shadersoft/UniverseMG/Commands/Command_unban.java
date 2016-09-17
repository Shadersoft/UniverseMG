package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.Arrays;
import java.util.List;

public class Command_unban implements UMGCommand
{
    private final UniverseMG plugin = UniverseMG.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(args.length == 0)
        {
            sender.sendMessage(Messages.MOD_TAG + "Correct usage is /unban <player>");
            return true;
        }
        
        if(Rank.getSenderRank(sender).getPriority() < this.getRank().getPriority())
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);

            return true;
        }
        
        if(args.length >= 1)
        {
            OfflinePlayer target = this.plugin.getServer().getOfflinePlayer(args[0]);

            if(target != null)
            {
                if(target.isBanned())
                {
                    this.plugin.banconfig.getConfig().set(target.getName().toLowerCase(), null);
                    this.plugin.banconfig.saveConfig();
                    target.setBanned(false);

                    for (Player p : Bukkit.getOnlinePlayers())
                    {
                        p.sendMessage(Messages.STAFF + sender.getName() + " has unbanned " + target.getName());
                    }
                }
                else
                {
                    sender.sendMessage(Messages.MOD_TAG + "That player isn't banned!");
                }
            }
            else
            {
                sender.sendMessage(Messages.MOD_TAG + "That player has not joined the server!");
            }
        }

        return true;
    }

    @Override
    public Rank getRank() {
        return Rank.ADMIN;
    }
    @Override
    public List<String> getAliases() 
    {
        return Arrays.asList("");
    }

    @Override
    public String getUsage() 
    {
        return "/<command> <player>";
    }

    @Override
    public String getDescription() 
    {
        return "Unbans a banned player.";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
