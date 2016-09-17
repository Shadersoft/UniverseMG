package com.Shadersoft.UniverseMG.httpd.modules;

import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.Collection;
import com.Shadersoft.UniverseMG.httpd.NanoHTTPD;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
// Thanks to the TotalFreedomMod developers for this bit of code
*/


public class Module_list extends HTTPDModule
{

    public Module_list(UniverseMG plugin, NanoHTTPD.HTTPSession session)
    {
        super(plugin, session);
    }

    @Override
    public String getBody()
    {
        final StringBuilder body = new StringBuilder();

        final Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();

        body.append("<p>There are ").append(onlinePlayers.size()).append("/").append(Bukkit.getMaxPlayers()).append(" players online:</p>\r\n");

        body.append("<ul>\r\n");

        for (Player player : onlinePlayers)
        {
            String tag = "[" + Rank.getSenderRank((CommandSender)player).getTag() + "] ";
            body.append("<li>").append(tag).append(player.getName()).append("</li>\r\n");
        }

        body.append("</ul>\r\n");

        return body.toString();
    }

    @Override
    public String getTitle()
    {
        return "UniverseMG - " + String.valueOf(Bukkit.getOnlinePlayers().size()) + " Online Users";
    }
}
