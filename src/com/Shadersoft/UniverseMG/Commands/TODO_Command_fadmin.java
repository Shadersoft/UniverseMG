package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.Ranks.RankType;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;

public class TODO_Command_fadmin implements UMGCommand
{
    private final UniverseMG plugin = UniverseMG.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        /*if(Rank.getSenderRank(sender).getPriority() < this.getRank().getPriority())
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);

            return true;
        }
        
        if(args.length != 1)
        {
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);

        if(player == null)
        {
            sender.sendMessage(ChatColor.RED + "Player not found!");

            return true;
        }
        
        if(Rank.getRankFromName(player.getName()).getType() != RankType.STAFF)
        {
            sender.sendMessage(ChatColor.RED + "This player is not a staff member.");
            
            return true;
        }

        ChatUtils.bCastMsg(ChatColor.RED + sender.getName() + " - Forcefully removing " + ChatColor.YELLOW + player.getName()
                           + ChatColor.RED + " from the rank " + ChatColor.YELLOW + Rank.getRankFromName(player.getName()));
        Rank.removeAdmin(player);
        player.setOp(false);
        
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                // strike lightning
                player.getWorld().strikeLightning(player.getLocation());

               
                player.setHealth(0.0);
            }
        }.runTaskLater(plugin, 2L * 20L);

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                // message
                ChatUtils.bCastMsg(ChatColor.GREEN + sender.getName() + " has been a little bitch, and is a bad admin!");
                p.sendMessage(Messages.STAFF + sender.getName() + " banned " + target.getName()
                                  + " from the server for "being a bad admin!"");
          
                // generate explosion
                player.getWorld().createExplosion(player.getLocation(), 0F, false);

                // kick player
                player.kickPlayer(ChatColor.RED + "You have been a bad admin! You're banned and suspended!!");
                
                //ban the player(add him to config)
                offlineTarget.setBanned(true);
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "minecraft:ban " + offlineTarget.getName() + " " + reason);
                sender.sendMessage(Messages.MOD_TAG + "Removed and Forcefully Banned " + ChatColor.DARK_RED + offlineTarget.getName());
                
                for (Player p : Bukkit.getOnlinePlayers())
                }
                

                this.plugin.banconfig.getConfig().set(offlineTarget.getName().toLowerCase(), reason);
                this.plugin.banconfig.saveConfig();
            }

        }.runTaskLater(plugin, 3L * 20L);*/


        return true;
    }

    @Override
    public Rank getRank()
    {
        return Rank.OWNER;
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
