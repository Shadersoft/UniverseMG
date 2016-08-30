package com.Shadersoft.UniverseMG.Handlers;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import com.Shadersoft.UniverseMG.UniverseMG;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandHandler implements Listener
{
    final UniverseMG plugin;

    public CommandHandler(UniverseMG instance)
    {
        this.plugin = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPreprocessCommand(PlayerCommandPreprocessEvent e)
    {
       String message = e.getMessage();
       String command = message.split(" ")[0];
       Player player = e.getPlayer();
       Rank playerRank = Rank.getSenderRank((CommandSender)player);
       
       if(plugin.config.contains("commandblocker.//*"))
       {
           Rank rankRequired = Rank.getRank(plugin.config.getString("commandblocker.//*"));
           
           if(command.startsWith("//"))
           {
               if(playerRank.getPriority() < rankRequired.getPriority())
               {
                   player.sendMessage(Messages.MSG_NO_PERMS);
                   e.setCancelled(true);
                   return;
               }
           }
       }
       
       if(plugin.config.contains("commandblocker." + command))
       {
           Rank rankRequired = Rank.getRank(plugin.config.getString("commandblocker." + command));
           if(playerRank.getPriority() < rankRequired.getPriority())
               {
                   player.sendMessage(Messages.MSG_NO_PERMS);
                   e.setCancelled(true);
                   return;
               }
       }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
