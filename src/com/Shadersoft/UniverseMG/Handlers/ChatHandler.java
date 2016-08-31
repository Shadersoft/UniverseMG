package com.Shadersoft.UniverseMG.Handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatHandler implements Listener
{
    final UniverseMG plugin;

    public ChatHandler(UniverseMG instance)
    {
        this.plugin = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();
        
        if(plugin.playerChats.containsKey(player))
        {
            ChatUtils.specialChatMessage(player, event.getMessage(), plugin.playerChats.get(player));
            event.setCancelled(true);
            return;
        }
        
        if(plugin.muted.contains(player))
        {
            event.setCancelled(true);
            return;
        }
        
        if(plugin.prefixes.containsKey(player))
        {
            ChatUtils.filterCastMsg(plugin.prefixes.get(player) + ChatColor.WHITE + " <" + player.getDisplayName() + ChatColor.WHITE + "> " + event.getMessage());
            event.setCancelled(true);
        }
        else
        {
            ChatUtils.filterCastMsg(ChatColor.WHITE + " <" + player.getDisplayName() + ChatColor.WHITE + "> " + event.getMessage());
            event.setCancelled(true);
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
