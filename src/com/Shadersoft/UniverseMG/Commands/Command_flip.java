package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.material.Lever;

public class Command_flip implements UMGCommand
{
    public UniverseMG plugin = UniverseMG.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(Rank.getSenderRank(sender).getPriority() < this.getRank().getPriority())
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);

            return true;
        }
        
        if(args.length != 3)
        {
            if(args.length != 0)
            {
                return false;
            }
            int maxDistance = 50;// whatever you need
            Block block = ((Player)sender).getTargetBlock((Set<Material>)null, maxDistance);
            if(block.getType() == Material.LEVER)
            {
                if(block.getData() < 8)
                {
                    block.setData((byte)(block.getData()+8),true);
                }
                else
                {
                    block.setData((byte)(block.getData()-8),true);
                }
                return true;
            }
            return true;
        }
        
        Location loc = new Location(((Player)sender).getWorld(),Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]));
        Block block = loc.getBlock();
        
        if(block.getType() == Material.LEVER)
        {
            if(block.getData() < 8)
            {
                block.setData((byte)(block.getData()+8),true);
            }
            else
            {
                block.setData((byte)(block.getData()-8),true);
            }
            return true;
        }
        
        return true;
    }

    @Override
    public Rank getRank()
    {
        return Rank.JUNIORMOD;
    }
    
    @Override
    public List<String> getAliases() 
    {
        return Arrays.asList("");
    }

    @Override
    public String getUsage() 
    {
        return "/<command> [x] [y] [z]";
    }

    @Override
    public String getDescription() 
    {
        return "Flips a lever.";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
