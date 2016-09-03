package com.Shadersoft.UniverseMG.Inventory;

import com.Shadersoft.UniverseMG.Inventory.*;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.Ranks.RankType;
import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerListGUI implements UMGInventory
{
    private Inventory inv = Bukkit.createInventory(null, getSize(), getName());

    
    @Override
    public String getName() 
    {
        return "Player List";
    }

    @Override
    public void setItems() 
    {
        for(Player p : UniverseMG.plugin.getServer().getOnlinePlayers())
        {
            ItemStack skull = new ItemStack(Material.SKULL_ITEM);
            skull.setDurability((short)3);
            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
            skullMeta.setOwner(p.getName());
            if(Rank.getSenderRank((CommandSender)p).getType() == RankType.STAFF) {skullMeta.setDisplayName(Rank.getSenderRank((CommandSender)p).getDisplayTag() + " " + Rank.getSenderRank((CommandSender)p).getColor() + p.getName());}
            else {skullMeta.setDisplayName(Rank.getSenderRank((CommandSender)p).getColor() + p.getName());}
            skull.setItemMeta(skullMeta);
            inv.addItem(skull);
        }
    }

    @Override
    public int getSize() 
    {
        int size = Bukkit.getOnlinePlayers().size();
        while(size%9 != 0) // Use MODULUS (%) to get if the size can be divided by 9.
        {
            size++;
        }
        return size;
    }
    
    @Override
    public ItemStack[] getItems() 
    {
        return inv.getStorageContents();
    }

    @Override
    public void itemInteract(InventoryClickEvent e) 
    {
        e.setResult(Event.Result.DENY);
        e.setCancelled(true);
        return;
    }

    @Override
    public Inventory getInventory() 
    {
        return inv;
    }

    @Override
    public void register() 
    {
        setItems();
    }
}
