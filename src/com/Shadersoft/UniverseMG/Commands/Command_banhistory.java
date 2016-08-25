package com.Shadersoft.UniverseMG.Commands;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.UniverseMG;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class Command_banhistory  implements CommandExecutor
{
    
private final UniverseMG plugin = UniverseMG.plugin;    
    
public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
      if (args.length == 0) {
        sender.sendMessage(Messages.MOD_TAG + "Correct usage is /baninfo <player>");
      } else if (args.length == 1) {
        if (this.plugin.banconfig.getConfig().contains(args[0].toLowerCase())) {
          sender.sendMessage(Messages.MOD_TAG + args[0] + " is banned for " + this.plugin.banconfig.getConfig().getString(args[0].toLowerCase()));
        } else {
          sender.sendMessage(Messages.MOD_TAG + args[0] + " is not banned.");
        }
      }
    return true;
  }
}
