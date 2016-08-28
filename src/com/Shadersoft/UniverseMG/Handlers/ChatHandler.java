package com.Shadersoft.UniverseMG.Handlers;

import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.Ranks.RankType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
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

        if(Rank.getSenderRank((CommandSender)player).getType() == RankType.STAFF)
        {
            String prefix = null;
            switch(Rank.getSenderRank((CommandSender)player))
            {
                case HELPER: prefix = ChatColor.DARK_GRAY + "[" + Rank.HELPER.getDisplayTag() + ChatColor.GRAY + "]";
                case MOD: prefix = ChatColor.DARK_GRAY + "[" + Rank.MOD.getDisplayTag() + ChatColor.GRAY + "]";
                case ADMIN: prefix = ChatColor.DARK_GRAY + "[" + Rank.ADMIN.getDisplayTag() + ChatColor.GRAY + "]";
                case DEV: prefix = ChatColor.DARK_GRAY + "[" + Rank.DEV.getDisplayTag() + ChatColor.GRAY + "]";
                case MAINDEV: prefix = ChatColor.DARK_GRAY + "[" + Rank.MAINDEV.getDisplayTag() + ChatColor.GRAY + "]";
                case OWNER: prefix = ChatColor.DARK_GRAY + "[" + Rank.OWNER.getDisplayTag() + ChatColor.GRAY + "]";
            }
            if(!player.getDisplayName().startsWith(prefix))
            {
                String oldName = player.getDisplayName();
                String newName = prefix + " " + oldName;
                
                player.setDisplayName(newName);
            }
        }
        
        if(plugin.playerChats.containsKey(player))
        {
            ChatUtils.specialChatMessage(player, event.getMessage(), plugin.playerChats.get(player));
            event.setCancelled(true);
        }
        
        if(plugin.muted.contains(player))
        {
            event.setCancelled(true);
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
