package com.Shadersoft.UniverseMG.Commands;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Command_kick implements CommandExecutor {

    private final UniverseMG plugin = UniverseMG.plugin;
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player)
        {
            if(Rank.getPlayerRank((Player) sender).getPriority() < this.getRank().getPriority())
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
        }
    	
        if (args.length == 0)
        {
            sender.sendMessage(Messages.MOD_TAG + "Correct usage is /kick <player> <reason>");
            return true;
        }

        if(args[0].equals(sender))
        {
        	sender.sendMessage(Messages.MOD_TAG + "You cannot kick yourself!");
        }
        
        final String reason = StringUtils.join(args, " ", 1, args.length);

        Player player = Bukkit.getServer().getPlayer(args[0]);
        
        if (player == null) {
                  player.sendMessage(ChatColor.RED + "Invalid Player");
                  return true;
              }
        
    	if (player == null)
        {
            sender.sendMessage(Messages.MOD_TAG + "Player not found!");
            return true;
        }

        if (args.length < 2)
        {
            sender.sendMessage(Messages.MOD_TAG + "Please specify a reason!");
        }
    	
    	player.kickPlayer(Messages.MOD_TAG + "You have been kicked by " + sender.getName() + " for " + reason);
        
        sender.sendMessage(Messages.STAFF + "You kicked " + player.getName() + " from the server for " + reason);
    	
        return true;
    }
    
    public Rank getRank()
    {
        return Rank.ADMIN;
    }
    
    }