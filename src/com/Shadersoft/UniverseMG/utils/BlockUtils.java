/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Shadersoft.UniverseMG.utils;

import com.Shadersoft.UniverseMG.utils.*;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

/**
 *
 * @author malmar03
 */
public class BlockUtils 
{
    public static List<Block> getRelatives(Block block)
    {
        // Get relatives
        Block relativeUp = block.getRelative(BlockFace.UP);
        Block relativeDown = block.getRelative(BlockFace.DOWN);
        
        Block relativeNorth = block.getRelative(BlockFace.NORTH);
          Block relativeNorthUp = block.getRelative(BlockFace.NORTH).getRelative(BlockFace.UP);
          Block relativeNorthDown = block.getRelative(BlockFace.NORTH).getRelative(BlockFace.DOWN);
          Block relativeNorthWest = block.getRelative(BlockFace.NORTH_WEST);
          Block relativeNorthEast = block.getRelative(BlockFace.NORTH_EAST);
        
        Block relativeSouth = block.getRelative(BlockFace.SOUTH);
          Block relativeSouthUp = block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.UP);
          Block relativeSouthDown = block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.DOWN);
          Block relativeSouthWest = block.getRelative(BlockFace.SOUTH_WEST);
          Block relativeSouthEast = block.getRelative(BlockFace.SOUTH_EAST);
        
        Block relativeWest = block.getRelative(BlockFace.WEST);
          Block relativeWestUp = block.getRelative(BlockFace.WEST).getRelative(BlockFace.UP);
          Block relativeWestDown = block.getRelative(BlockFace.WEST).getRelative(BlockFace.DOWN);
          
        Block relativeEast = block.getRelative(BlockFace.EAST);
          Block relativeEastUp = block.getRelative(BlockFace.EAST).getRelative(BlockFace.UP);
          Block relativeEastDown = block.getRelative(BlockFace.EAST).getRelative(BlockFace.DOWN);
         
        // Create list
        List<Block> relatives = new ArrayList();
        
        // Add relatives to list
        relatives.add(relativeUp);
        relatives.add(relativeDown);
        
        relatives.add(relativeNorth);
          relatives.add(relativeNorthUp);
          relatives.add(relativeNorthDown);
          relatives.add(relativeNorthWest);
          relatives.add(relativeNorthEast);
        
        relatives.add(relativeSouth);
          relatives.add(relativeSouthUp);
          relatives.add(relativeSouthDown);
          relatives.add(relativeSouthWest);
          relatives.add(relativeSouthEast);
        
        relatives.add(relativeWest);
          relatives.add(relativeWestUp);
          relatives.add(relativeWestDown);
        
        relatives.add(relativeEast);
          relatives.add(relativeEastUp);
          relatives.add(relativeEastDown);
        
        return relatives;
    }
    
    public static List<Material> getMaterials(List<Block> blocks)
    {
        List<Material> materials = new ArrayList();
        
        for(Block block : blocks)
        {
            materials.add(block.getType());
        }
        
        return materials;
    }
}