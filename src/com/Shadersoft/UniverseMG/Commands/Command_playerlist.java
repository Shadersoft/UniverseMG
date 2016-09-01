package com.Shadersoft.UniverseMG.Commands;

import com.Shadersoft.UniverseMG.Inventory.PlayerListGUI;
import org.apache.commons.lang.StringUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.ArrayList;
import java.util.List;
import static org.bukkit.Bukkit.getOfflinePlayer;
import org.bukkit.OfflinePlayer;
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
}


//~ Formatted by Jindent --- http://www.jindent.com
