package com.Shadersoft.UniverseMG.Commands;

import com.Shadersoft.UniverseMG.Coins.Coins;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import static com.Shadersoft.UniverseMG.utils.ChatUtils.msg;
import java.util.Arrays;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import static org.bukkit.Bukkit.getPlayer;
import org.bukkit.entity.Player;

public class Command_points implements UMGCommand
{
    public UniverseMG plugin = UniverseMG.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(args.length != 4)
        {
            if(args.length == 0)
            {
                msg(sender, "You have " + String.valueOf(Coins.get((Player)sender)) + " coins.");
                return true;
            }
            return false;
        }
        
        
        
        
        
        Player player = getPlayer(args[2]);
        
        if(player == null)
        {
            msg(sender, "Player not found.");
            return true;
        }
        
        switch(args[1].toUpperCase())
        {
            case "COINS":
            {
                switch(args[0].toUpperCase())
                {
                    case "ADD":
                    {
                        int amt = Integer.parseInt(args[3]);
                        
                        Coins.add(player, amt);
                        msg(sender, "Gave " + player.getName() + " " + ChatColor.YELLOW + args[2] + ChatColor.RED + " Coins.");
                    }
                    
                    case "REMOVE":
                    {
                        int amt = Integer.parseInt(args[3]);
                        if(!Coins.hasEnough(player, amt))
                        {
                            Coins.set(player, 0);
                        }
                        Coins.remove(player, amt);
                        msg(sender, "Took " + ChatColor.YELLOW + args[2] + ChatColor.RED + " Coins from " + player.getName());
                    }
                    
                    case "SET":
                    {
                        int amt = Integer.parseInt(args[3]);
                        Coins.set(player, amt);
                        msg(sender, "Set " + player.getName() + "'s Coins to" + ChatColor.YELLOW + args[2]);
                    }
                }
            }
        }

        return true;
    }

    @Override
    public Rank getRank()
    {
        return Rank.ADMIN;
    }
    
    @Override
    public List<String> getAliases() 
    {
        return Arrays.asList("");
    }

    @Override
    public String getUsage() 
    {
        return "/<command>";
    }

    @Override
    public String getDescription() 
    {
        return "lorem ipsum";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
