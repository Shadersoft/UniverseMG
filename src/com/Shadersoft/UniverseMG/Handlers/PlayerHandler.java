package com.Shadersoft.UniverseMG.Handlers;

import com.Shadersoft.UniverseMG.Inventory.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.Ranks.RankType;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import com.Shadersoft.UniverseMG.utils.TablistUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerHandler implements Listener
{
    final UniverseMG plugin;

    public PlayerHandler(UniverseMG instance)
    {
        this.plugin = instance;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryInteract(InventoryClickEvent e)
    {
        Player player = (Player) e.getWhoClicked(); // The player that clicked the item
        ItemStack clicked = e.getCurrentItem(); // The item that was clicked
        Inventory inventory = e.getInventory(); // The inventory that was clicked in
        
        PlayerListGUI playerListGUI = new PlayerListGUI();
        AdminListGUI adminListGUI = new AdminListGUI();
        
        playerListGUI.register();
        adminListGUI.register();
        
        if(inventory.getName().equalsIgnoreCase(playerListGUI.getName())) {playerListGUI.itemInteract(e);}
        if(inventory.getName().equalsIgnoreCase(adminListGUI.getName())) {adminListGUI.itemInteract(e);}
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDeath(PlayerDeathEvent e)
    {
        Player player = e.getEntity();
        
        ItemStack skull = new ItemStack(Material.SKULL_ITEM);
        skull.setDurability((short)3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwner(player.getName());
        skull.setItemMeta(skullMeta);
        
        player.getWorld().dropItemNaturally(player.getLocation(), skull);
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPreJoin(AsyncPlayerPreLoginEvent event)
    {
        String player = event.getName().toLowerCase();
        /*
        if(this.plugin.banconfig.getConfig().getString(player) != null)
        {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED,
                           Messages.MOD_TAG + "You are banned from this server for "
                           + this.plugin.banconfig.getConfig().getString(player));
        }
        */
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
        
        for(Player op : plugin.getServer().getOnlinePlayers())
        {
            TablistUtil.setForPlayer(op, ChatUtils.colorize(StringUtils.join(plugin.config.getStringList("tablist.header"),"\n").replace("%playercount%", String.valueOf(plugin.getServer().getOnlinePlayers().size()))), ChatUtils.colorize(StringUtils.join(plugin.config.getStringList("tablist.footer"),"\n").replace("%forum%", plugin.config.getString("forums"))));
        }
        
        if(playerRank.getType() == RankType.STAFF)
        {
            player.setPlayerListName(Rank.getSenderRank((CommandSender)player).getDisplayTag() + Rank.getSenderRank((CommandSender)player).getColor() + player.getName());
            String prefix = prefix = Rank.getSenderRank((CommandSender)player).getDisplayTag();
            event.setJoinMessage(prefix + " " + playerRank.getColor() + player.getName() + ChatColor.YELLOW + " joined the game");
            plugin.prefixes.put(player, prefix);
        }
        if(player.isBanned()) 
        {
            player.kickPlayer(Messages.MOD_TAG + "You are banned from this server");
        }
        
    }
}


//~ Formatted by Jindent --- http://www.jindent.com