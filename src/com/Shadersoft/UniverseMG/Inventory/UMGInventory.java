package com.Shadersoft.UniverseMG.Inventory;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public interface UMGInventory 
{
    public void register();
    public String getName();
    public void setItems();
    public int getSize();
    public ItemStack[] getItems();
    public void itemInteract(InventoryClickEvent e);
    public Inventory getInventory();
}
