package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;

public class Command_ban implements UMGCommand
{
    private final UniverseMG plugin = UniverseMG.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(args.length == 0)
        {
            sender.sendMessage(Messages.MOD_TAG + "Usage: /ban <player> <reason>");
            return true;
        }

        if(Rank.getSenderRank(sender).getPriority() < this.getRank().getPriority())
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);

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
                String reason = "Banned!";

                target.kickPlayer(Messages.MOD_TAG + sender.getName() + " has banned you for " + reason
                                  + " appeal on the forums: " + plugin.config.getBoolean("forums"));
                target.setBanned(true);

                for (Player p : Bukkit.getOnlinePlayers())
                {
                    p.sendMessage(Messages.STAFF + sender.getName() + " banned " + target.getName()
                                  + " from the server for " + reason);
                }

                this.plugin.banconfig.getConfig().set(target.getName().toLowerCase(), reason);
                this.plugin.banconfig.saveConfig();
            }
            else
            {
                String reason = "Banned!";

                offlineTarget.setBanned(true);

                for (Player p : Bukkit.getOnlinePlayers())
                {
                    p.sendMessage(Messages.STAFF + sender.getName() + " banned " + offlineTarget.getName()
                                  + " from the server for " + reason);
                }

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

                target.kickPlayer("You were banned from the server.\nReason: �c" + reason);
                target.setBanned(true);

                for (Player p : Bukkit.getOnlinePlayers())
                {
                    p.sendMessage(Messages.STAFF + sender.getName() + " banned " + target.getName()
                                  + " from the server for " + reason);
                }

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

                for (Player p : Bukkit.getOnlinePlayers())
                {
                    p.sendMessage(Messages.STAFF + sender.getName() + " banned " + offlineTarget.getName()
                                  + " from the server for " + reason);
                }

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
}


//~ Formatted by Jindent --- http://www.jindent.com
