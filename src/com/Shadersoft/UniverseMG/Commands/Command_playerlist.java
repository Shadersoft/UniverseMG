package com.Shadersoft.UniverseMG.Commands;

import com.Shadersoft.UniverseMG.Inventory.AdminListGUI;
import com.Shadersoft.UniverseMG.Inventory.PlayerListGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.Arrays;
import java.util.List;
import org.bukkit.entity.Player;

public class Command_playerlist implements UMGCommand
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
        
        if(!(sender instanceof Player))
        {
            sender.sendMessage(Messages.MSG_PLAYER_ONLY);
            return true;
        }
        
        if(args.length != 0)
        {
            if(args[0].equalsIgnoreCase("-a"))
            {
                AdminListGUI gui = new AdminListGUI();
                gui.register();
        
                ((Player)sender).openInventory(gui.getInventory());
                return true;
            }
            return false;
        }
        PlayerListGUI gui = new PlayerListGUI();
        gui.register();
        
        ((Player)sender).openInventory(gui.getInventory());
        return true;
    }

    @Override
    public Rank getRank()
    {
        return Rank.PLAYER;
    }
    
    @Override
    public List<String> getAliases() 
    {
        return Arrays.asList("list");
    }

    @Override
    public String getUsage() 
    {
        return "/<command> [-a]";
    }

    @Override
    public String getDescription() 
    {
        return "List all players.";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
