package com.Shadersoft.UniverseMG.Handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.Shadersoft.UniverseMG.UniverseMG;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListener implements Listener
{
    final UniverseMG plugin;

    public ServerListener(UniverseMG instance)
    {
        this.plugin = instance;
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent e)
    {
        e.setMotd("§r§b§m§l----§r§8§m§l[---§r §r§b§lUniverse§a§lMG §r§8§m§l---]§b§m§l---");
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
