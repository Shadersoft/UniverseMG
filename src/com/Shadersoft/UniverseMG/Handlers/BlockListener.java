package com.Shadersoft.UniverseMG.Handlers;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListener implements Listener
{
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event)
    {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.CHICKEN) 
        {
            for (ItemStack drops : event.getDrops()) 
            {
                if (drops.getType() == Material.RAW_CHICKEN) 
                {
                    drops.setType(Material.COOKED_CHICKEN);
                }
            }
        }
        if (entity.getType() == EntityType.COW) 
        {
            for (ItemStack drops : event.getDrops()) 
            {
                if (drops.getType() == Material.RAW_BEEF) 
                {
                    drops.setType(Material.COOKED_BEEF);
                }
            }
        }
        if (entity.getType() == EntityType.PIG) 
        {
            for (ItemStack drops : event.getDrops()) 
            {
                if (drops.getType() == Material.PORK) 
                {
                    drops.setType(Material.GRILLED_PORK);
                }
            }
        }
    }
  
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        Block block = event.getBlock();
      
        if (block.getType() == Material.IRON_ORE)
        {
            event.setCancelled(true);
            block.setType(Material.AIR);
            block.getState().update();
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_INGOT));
        }
        if (block.getType() == Material.GOLD_ORE)
        {
            event.setCancelled(true);
            block.setType(Material.AIR);
            block.getState().update();
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GOLD_INGOT));
        }
        if (block.getType() == Material.GRAVEL)
        {
            event.setCancelled(true);
            block.setType(Material.AIR);
            block.getState().update();
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.FLINT));
        }
        if (block.getType() == Material.LEAVES)
        {
            event.setCancelled(true);
            block.setType(Material.AIR);
            block.getState().update();
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.APPLE));
        }
        if (block.getType() == Material.DIAMOND_ORE)
        {
            event.setCancelled(true);
            block.setType(Material.AIR);
            block.getState().update();
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.DIAMOND));
        }
    }
}
