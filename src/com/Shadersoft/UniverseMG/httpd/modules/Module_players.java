package com.Shadersoft.UniverseMG.httpd.modules;

import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import static com.Shadersoft.UniverseMG.httpd.HTMLGenerationTools.heading;
import static com.Shadersoft.UniverseMG.httpd.HTMLGenerationTools.paragraph;
import com.Shadersoft.UniverseMG.httpd.NanoHTTPD;
import java.util.HashMap;
import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
// Thanks to the TotalFreedomMod developers for this bit of code
*/


public class Module_players extends HTTPDModule
{

    public Module_players(UniverseMG plugin, NanoHTTPD.HTTPSession session)
    {
        super(plugin, session);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String getBody()
    {
        final StringBuilder responseBody = new StringBuilder()
                .append(heading("Players", 1))
                .append(paragraph(
                                "This page is an automatically generated listing of all players and their ranks on the server."));


        HashMap<Player,Rank> map = new HashMap();
        
        for(Player plr : Bukkit.getOnlinePlayers())
        {
        }
        for(Rank rnk : Rank.values())
        {
            if(rnk.equals(Rank.PLAYER) || rnk.equals(Rank.IMPOSTOR)) {}
            else
            {
                responseBody.append("</ul>\r\n").append(heading(rnk.getName(), 3)); //So basically im getting this wierd indentation at the first username
                responseBody.append("<ul>\r\n");
                
                for(OfflinePlayer p : Rank.getOfflineStaff().keySet())
                {
                    if(Rank.getRankFromName(p.getName()).equals(rnk))
                    {
                        responseBody.append(p.getName());
                    }
                }
            }
            responseBody.append("<ul>\r\n");
        }
        
        
        return responseBody.toString();
    }
    
    @Override
    public String getStyle()
    {
        return ".rank{font-weight:bold;}.rankPlayer{padding-left:15px;}li{margin:.15em;padding:.15em;}";
    }
    
    private static String buildPage(HashMap<Player,Rank> players)
    {
        StringBuilder sb = new StringBuilder();

        for(Rank title : Rank.values())
        {
            sb.append(
                "<li><span class=\"rank\">{$RANK}</span>"
                .replace("{$RANK}", escapeHtml4(title.getName())));
            for(Player p : players.keySet())
            {
                if(Rank.getSenderRank((CommandSender)p).equals(title))
                {
                    sb.append(
                        "<br><span class=\"rankPlayer\">{$PLAYER}</span></li>\r\n"
                        .replace("{$CMD_DESC}", escapeHtml4(p.getName())));
                }
            }
        }
        return sb.toString();
    }
}
