/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Shadersoft.UniverseMG.utils;

import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.HashMap;
import java.util.Set;
import static org.bukkit.Bukkit.getPlayer;
import org.bukkit.entity.Player;

/**
 *
 * @author malmar03
 */
public class PlayerUtils 
{
    public static UniverseMG plugin = UniverseMG.plugin;
    
    public static HashMap<Player, String> getPermbans()
    {
        HashMap<Player, String> out = new HashMap();
        Set<String> banNames = plugin.permbanConfig.getConfig().getKeys(false);
        
        for(String pName : banNames)
        {
            Player p = getPlayer(pName);
            String ip = plugin.permbanConfig.getConfig().getString(pName);
            out.put(p, ip);
        }
        return out;
    }
}
