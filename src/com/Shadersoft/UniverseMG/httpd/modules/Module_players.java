package com.Shadersoft.UniverseMG.httpd.modules;

import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.httpd.NanoHTTPD;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
    public NanoHTTPD.Response getResponse()
    {
        final JSONObject responseObject = new JSONObject();

        final JSONArray players = new JSONArray();
        final JSONArray helpers = new JSONArray();
        final JSONArray moderators = new JSONArray();
        final JSONArray admins = new JSONArray();
        final JSONArray developers = new JSONArray();
        final JSONArray maindevelopers = new JSONArray();
        final JSONArray coowners = new JSONArray();
        final JSONArray owners = new JSONArray();

        // All online players
        for (Player player : Bukkit.getOnlinePlayers())
        {
            players.add(player.getName());
        }

        // Admins
        for (OfflinePlayer admin : Rank.getOfflineStaff().keySet())
        {
            final String username = admin.getName();

            switch (Rank.getOfflineStaff().get(admin))
            {
                case HELPER:
                    helpers.add(username);
                    break;
                case MODERATOR:
                    moderators.add(username);
                    break;
                case ADMIN:
                    admins.add(username);
                    break;
                case DEV:
                    developers.add(username);
                    break;
                case MAINDEV:
                    maindevelopers.add(username);
                    break;
                case COOWNER:
                    coowners.add(username);
                    break;
                case OWNER:
                    owners.add(username);
                    break;
            }
        }

        responseObject.put("players", players);
        responseObject.put("helpers", helpers);
        responseObject.put("moderators", moderators);
        responseObject.put("admins", admins);
        responseObject.put("developers", developers);
        responseObject.put("main developers", maindevelopers);
        responseObject.put("co-owners", coowners);
        responseObject.put("owners", owners);

        final NanoHTTPD.Response response = new NanoHTTPD.Response(NanoHTTPD.Response.Status.OK, NanoHTTPD.MIME_JSON, responseObject.toString());
        response.addHeader("Access-Control-Allow-Origin", "*");
        return response;
    }
}
