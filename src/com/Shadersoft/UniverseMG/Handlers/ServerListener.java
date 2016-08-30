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
        
        motdLines.add("&r&b&m&l----&r&8&m&l[---&r &r&b&lUniverse&a&lMG &r&8&m&l---]&b&m&l----");
        
        String secondLine = "&r&a" + String.valueOf(Rank.getOnlineStaff().size()) + " &bStaff Online";
        if(Rank.getOnlineStaff().size() > 0)
        {
            int lastPri = 0;
            Player highestPlr = null;
            Rank highestRank = Rank.HELPER;
            for(Player player : Rank.getOnlineStaff().keySet())
            {
                if(lastPri < Rank.getSenderRank((CommandSender)player).getPriority())
                {
                    lastPri = Rank.getSenderRank((CommandSender)player).getPriority();
                    highestPlr = player;
                    highestRank = Rank.getSenderRank((CommandSender)player);
                }
            }
            
            secondLine = "&r&a" + String.valueOf(Rank.getOnlineStaff().size()) + " &bStaff Online    &f&l|   &r&bHighest Staff: " + highestRank.getColor() + highestPlr.getName();
        }
        motdLines.add(secondLine);
        
        e.setMotd(ChatUtils.colorize(StringUtils.join(motdLines, "\n")));
        e.setMaxPlayers(plugin.getServer().getOnlinePlayers().size() + 1);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
