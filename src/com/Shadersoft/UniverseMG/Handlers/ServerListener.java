package com.Shadersoft.UniverseMG.Handlers;

import com.Shadersoft.UniverseMG.Ranks.Rank;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
        List<String> motdLines = new ArrayList();
        
        motdLines.add(plugin.config.getString("motd.line0"));
        motdLines.add(plugin.config.getString("motd.line1").replace("%staffcount%", String.valueOf(Rank.getOnlineStaff().size())));
        
        e.setMotd(ChatUtils.colorize(StringUtils.join(motdLines, "\n")));
        e.setMaxPlayers(plugin.getServer().getOnlinePlayers().size() + 1);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
