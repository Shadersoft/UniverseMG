package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.Arrays;
import java.util.List;
import net.md_5.bungee.api.ChatColor;

public class Command_sban implements UMGCommand
{
    private final UniverseMG plugin = UniverseMG.plugin;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(Rank.getSenderRank(sender).getPriority() < this.getRank().getPriority())
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);

            return true;
        }
        
        if(args.length == 0)
        {
            sender.sendMessage(Messages.MOD_TAG + "Usage: /sban <player> <reason>");
            return true;
        }
            
        if(Rank.getRankFromName(args[0]).getPriority()
            > Rank.getRankFromName(sender.getName()).getPriority())
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);

            return true;
        }
       
        else if(args.length == 1)
        {
            Player        target        = this.plugin.getServer().getPlayer(args[0]);
            OfflinePlayer offlineTarget = this.plugin.getServer().getOfflinePlayer(args[0]);

            if(target != null)
            {
                String reason = "You're account has been suspended from UniverseMG";

                target.kickPlayer(Messages.MOD_TAG + sender.getName() + " has banned you " + reason
                                  + " appeal @   " + plugin.config.getBoolean("forums"));
                target.setBanned(true);
                sender.sendMessage(Messages.MOD_TAG + "Banned " + ChatColor.DARK_RED + target.getName());

                this.plugin.banconfig.getConfig().set(target.getName().toLowerCase(), reason);
                this.plugin.banconfig.saveConfig();
            }
            else
            {
                String reason = "Your account has been suspended from UniverseMG";

                offlineTarget.setBanned(true);
                sender.sendMessage(Messages.MOD_TAG + "Banned " + ChatColor.DARK_RED + offlineTarget.getName());

                this.plugin.banconfig.getConfig().set(offlineTarget.getName().toLowerCase(), reason);
                this.plugin.banconfig.saveConfig();
            }
        }
        else if(args.length >= 2)
        {
            Player        target        = this.plugin.getServer().getPlayer(args[0]);
            OfflinePlayer offlineTarget = this.plugin.getServer().getOfflinePlayer(args[0]);

            if(target != null)
            {
                String reason = "";

                for (int i = 1; i < args.length; i++)
                {
                    reason = reason + args[i] + " ";
                }

                target.kickPlayer("Your account has been suspended from UniverseMG \nReason: " + ChatColor.RED + reason);
                target.setBanned(true);
                
                sender.sendMessage(Messages.MOD_TAG + "Banned " + ChatColor.DARK_RED + target.getName());

                this.plugin.banconfig.getConfig().set(target.getName().toLowerCase(), reason);
                this.plugin.banconfig.saveConfig();
            }
            else
            {
                String reason = "";

                for (int i = 1; i < args.length; i++)
                {
                    reason = reason + args[i] + " ";
                }

                offlineTarget.setBanned(true);

                sender.sendMessage(Messages.MOD_TAG + "[SILENT] Banned " + ChatColor.DARK_RED + offlineTarget.getName());

                this.plugin.banconfig.getConfig().set(offlineTarget.getName().toLowerCase(), reason);
                this.plugin.banconfig.saveConfig();
            }
        }

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
        return Arrays.asList("silentban");
    }

    @Override
    public String getUsage() 
    {
        return "/<command> <player> <reason>";
    }

    @Override
    public String getDescription() 
    {
        return "Bans a player silently.";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
