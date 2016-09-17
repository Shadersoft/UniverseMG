package com.Shadersoft.UniverseMG.Handlers;

import com.Shadersoft.UniverseMG.Coins.Coins;
import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandHandler implements Listener
{
    private static UniverseMG plugin;

    public CommandHandler(UniverseMG instance)
    {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPreprocessCommand(PlayerCommandPreprocessEvent e)
    {
       String message = e.getMessage();
       String command = message.split(" ")[0];
       Player player = e.getPlayer();
       Rank playerRank = Rank.getSenderRank((CommandSender)player);
       
       Command cmd = plugin.getServer().getPluginCommand(command.replaceFirst("/", ""));
       
       if(plugin.config.contains("commands.//*"))
       {
           Rank rankRequired = Rank.getRank(plugin.config.getString("commands.//*"));
           
           if(command.startsWith("//"))
           {
               player.setOp(true);
               if(playerRank.getPriority() < rankRequired.getPriority())
               {
                   player.sendMessage(Messages.MSG_NO_PERMS);
                   e.setCancelled(true);
                   return;
               }
           }
       } else if(plugin.config.contains("commands." + command))
       {
           player.setOp(true);
           String stringRankRequired = plugin.config.getString("commands." + command);
           Rank rankRequired = Rank.getRank(plugin.config.getString("commands." + command));
           if(stringRankRequired.contains(" "))
           {
               String[] splitRankRequired = stringRankRequired.split(" ");
               String type = splitRankRequired[0].toUpperCase();
               String cost = splitRankRequired[1].toLowerCase();
               
               if("COINS".equals(type))
               {
                   int amount = Integer.parseInt(cost);
                   
                   if(Coins.hasEnough(player, amount))
                   {
                       Coins.remove(player, amount);
                       player.sendMessage(ChatColor.RED + "Paid " + ChatColor.YELLOW + cost + " " + ChatColor.RED + ChatUtils.capitalize(type) + " to use command " + ChatColor.YELLOW + command);
                       e.setCancelled(false);
                   }
                   else
                   {
                       player.sendMessage(ChatColor.RED + "You dont have " + ChatColor.YELLOW + cost + ChatColor.RED + " " + ChatUtils.capitalize(type) + " to pay!");
                       e.setCancelled(true);
                   }
               }
           }
           
           if(playerRank.getPriority() < rankRequired.getPriority())
           {
               player.sendMessage(Messages.MSG_NO_PERMS);
               e.setCancelled(true);
               return;
           }
       }
       else
       {
           player.setOp(false);
       }
    }
}

