package com.Shadersoft.UniverseMG.Commands;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.UniverseMG;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_unban
  implements CommandExecutor
{  
  private final UniverseMG plugin = UniverseMG.plugin;
    
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
      if (args.length == 0)
      {
        sender.sendMessage(Messages.MOD_TAG + "Correct usage is /unban <player>");
      }
      else if (args.length >= 1)
      {
        OfflinePlayer target = this.plugin.getServer().getOfflinePlayer(args[0]);
        if (target != null)
        {
          if (target.isBanned())
          {
            this.plugin.banconfig.getConfig().set(target.getName().toLowerCase(), null);
            this.plugin.banconfig.saveConfig();
            
            target.setBanned(false);
            
            for (Player p : Bukkit.getOnlinePlayers()) {
          		  p.sendMessage(Messages.STAFF + sender.getName() + " has unbanned " + target.getName());
          }
          }
          else
          {
            sender.sendMessage(Messages.MOD_TAG + "That player isn't banned!");
          }
        }
        else {
          sender.sendMessage(Messages.MOD_TAG + "That player has not joined the server!");
        }
      }
    return true;
  }
}
