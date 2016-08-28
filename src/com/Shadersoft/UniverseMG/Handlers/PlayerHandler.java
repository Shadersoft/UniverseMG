package com.Shadersoft.UniverseMG.Handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.Ranks.RankType;
import com.Shadersoft.UniverseMG.UniverseMG;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerHandler implements Listener
{
    final UniverseMG plugin;

    public PlayerHandler(UniverseMG instance)
    {
        this.plugin = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPreJoin(AsyncPlayerPreLoginEvent event)
    {
        String player = event.getName().toLowerCase();

        if(this.plugin.banconfig.getConfig().getString(player) != null)
        {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED,
                           Messages.MOD_TAG + "You are banned from this server for "
                           + this.plugin.banconfig.getConfig().getString(player));
        }
        
        if(plugin.config.getBoolean("maintenance_mode"))
        {
            if(Rank.getRankFromName(event.getName()).getType() != RankType.STAFF)
            {
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Messages.MOD_TAG + "Server is currently in maintenance, only staff may join.");
            }
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        Rank playerRank = Rank.getSenderRank((CommandSender)player);
        
        if(playerRank.getType() == RankType.STAFF)
        {
            event.setJoinMessage(playerRank.getDisplayTag() + " " + playerRank.getColor() + player.getName() + ChatColor.YELLOW + " joined the game");
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
