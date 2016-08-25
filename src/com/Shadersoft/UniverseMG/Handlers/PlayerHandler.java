package com.Shadersoft.UniverseMG.Handlers;

import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.UniverseMG;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PlayerHandler
  implements Listener
{
  final UniverseMG plugin;
  
  public PlayerHandler(UniverseMG instance)
  {
    this.plugin = instance;
  }

@EventHandler(priority=EventPriority.MONITOR)
  public void onJoin(AsyncPlayerPreLoginEvent event)
  {
    String player = event.getName().toLowerCase();
    if (this.plugin.banconfig.getConfig().getString(player) != null) {
      event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, Messages.MOD_TAG + "You are banned from this server for " + this.plugin.banconfig.getConfig().getString(player));
    }
  }
}
