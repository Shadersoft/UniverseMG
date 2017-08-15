package com.Shadersoft.UniverseMG.Commands;

import com.Shadersoft.UniverseMG.utils.TimeUtils;
import com.Shadersoft.UniverseMG.utils.UMGOfflinePlayer;
import com.Shadersoft.UniverseMG.utils.UMGPlayer;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import net.md_5.bungee.api.ChatColor;

public class Command_ban implements UMGCommand
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
            sender.sendMessage(Messages.MOD_TAG + "Usage: /ban <player> <time> <reason>");
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
            UMGPlayer        target        = (UMGPlayer) this.plugin.getServer().getPlayer(args[0]);
            UMGOfflinePlayer offlineTarget = (UMGOfflinePlayer) this.plugin.getServer().getOfflinePlayer(args[0]);

            if(target != null)
            {
                String reason = "You broke the server rules!";

                target.setBanReason(reason);
                target.kickPlayer(Messages.MOD_TAG + sender.getName() + " has banned you for: " + reason
                                  + ", appeal at " + plugin.config.getString("forums"));
                target.setBanned(true, BanList.Type.NAME, new Date(TimeUtils.yearsToMillis(256)));
                sender.sendMessage(Messages.MOD_TAG + "BANNED " + ChatColor.DARK_RED + target.getName());

                for (Player p : Bukkit.getOnlinePlayers())
                {
                    p.sendMessage(Messages.STAFF + sender.getName() + " banned " + target.getName()
                                  + " from the server for: " + reason);
                }

                this.plugin.banconfig.getConfig().set(target.getName().toLowerCase(), reason);
                this.plugin.banconfig.saveConfig();
            }
            else
            {
                String reason = "Your account has been suspended from UniverseMG";

                target.setBanReason(reason);
                target.setBanned(true, BanList.Type.NAME, new Date(TimeUtils.yearsToMillis(256)));
                sender.sendMessage(Messages.MOD_TAG + "BANNED " + ChatColor.DARK_RED + offlineTarget.getName());

                for (Player p : Bukkit.getOnlinePlayers())
                {
                    p.sendMessage(Messages.STAFF + sender.getName() + " BANNED " + offlineTarget.getName()
                                  + " from " + plugin.config.getString("server-name") + " for " + reason);
                }

                this.plugin.banconfig.getConfig().set(offlineTarget.getName().toLowerCase(), reason);
                this.plugin.banconfig.saveConfig();
            }
        }
        else if(args.length >= 2)
        {
            UMGPlayer        target        = (UMGPlayer) this.plugin.getServer().getPlayer(args[0]);
            UMGOfflinePlayer offlineTarget = (UMGOfflinePlayer) this.plugin.getServer().getOfflinePlayer(args[0]);
            String[] dateValues = args[1].split(":");
            long dateMillis = 0;

            for(String value : dateValues) {
                if(!NumberUtils.isNumber(value.substring(0, value.length() - 1))) {
                    sender.sendMessage(Messages.MOD_TAG + "You have not specified a correct date.");
                    return true;
                }

                String dateType = value.substring(value.length() - 1, 1);
                int amount = Integer.parseInt(value.substring(0, value.length() - 1));

                switch(dateType) {
                    case "s":
                        dateMillis += TimeUtils.secondsToMillis(amount);
                        break;

                    case "m":
                        dateMillis += TimeUtils.minutesToMillis(amount);
                        break;

                    case "h":
                        dateMillis += TimeUtils.hoursToMillis(amount);
                        break;

                    case "d":
                        dateMillis += TimeUtils.daysToMillis(amount);
                        break;

                    case "y":
                        dateMillis += TimeUtils.yearsToMillis(amount);
                        break;

                    default:
                        sender.sendMessage(Messages.MOD_TAG + "You have not specified a correct date.");
                        return true;
                }
            }
            LocalDateTime currentDate = LocalDateTime.now();
            Date expires = new Date(TimeUtils.secondsToMillis(currentDate.getSecond()) +
                    TimeUtils.minutesToMillis(currentDate.getMinute()) +
                    TimeUtils.hoursToMillis(currentDate.getHour()) +
                    TimeUtils.daysToMillis(currentDate.getDayOfYear()) +
                    TimeUtils.yearsToMillis(currentDate.getYear()) +
                    dateMillis);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd at HH:mm");

            if(target != null)
            {
                String reason = target.getBanReason();

                if(args.length >= 3) {
                    for (int i = 2; i < args.length; i++)
                    {
                        reason = reason + args[i] + " ";
                    }
                }

                target.kickPlayer("Your account has been suspended from UniverseMG \nReason: \"" + reason + "\"\nBan Expires: " + dateFormat.format(expires));
                target.setBanReason(reason);
                target.setBanned(true, BanList.Type.NAME, expires);
                sender.sendMessage(Messages.MOD_TAG + "BANNED " + ChatColor.DARK_RED + target.getName() + " until " + dateFormat.format(expires));

                for (Player p : Bukkit.getOnlinePlayers())
                {
                    p.sendMessage(Messages.STAFF + sender.getName() + " BANNED " + target.getName()
                                  + " from UniverseMG until " + dateFormat.format(expires) + " for \"" + reason + "\"");
                }

                this.plugin.banconfig.getConfig().set(target.getName().toLowerCase(), reason);
                this.plugin.banconfig.saveConfig();
            }
            else
            {
                String reason = target.getBanReason();

                if(args.length >= 3) {
                    for (int i = 2; i < args.length; i++)
                    {
                        reason = reason + args[i] + " ";
                    }
                }

                offlineTarget.setBanReason(reason);
                offlineTarget.setBanned(true, BanList.Type.NAME, expires);
                sender.sendMessage(Messages.MOD_TAG + "BANNED " + ChatColor.DARK_RED + offlineTarget.getName() + " until " + dateFormat.format(expires));

                for (Player p : Bukkit.getOnlinePlayers())
                {
                    p.sendMessage(Messages.STAFF + sender.getName() + " BANNED " + offlineTarget.getName()
                            + " from UniverseMG until " + dateFormat.format(expires) + " for \"" + reason + "\"");
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
    @Override
    public List<String> getAliases() 
    {
        return Arrays.asList("gtfo");
    }

    @Override
    public String getUsage() 
    {
        return "/<command> <player> <reason>";
    }

    @Override
    public String getDescription() 
    {
        return "Bans a player.";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
