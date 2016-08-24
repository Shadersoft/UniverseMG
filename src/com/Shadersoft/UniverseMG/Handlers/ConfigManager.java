package com.Shadersoft.UniverseMG.Handlers;

import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.HashMap;
import java.util.Set;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class ConfigManager 
{
    public UniverseMG plugin = UniverseMG.plugin;
    public void saveHashMap(HashMap hm, String prefix)
    {
        String oldPrefix = prefix;
        if(prefix != null) {prefix = oldPrefix + ".";}
        
        Set keys = hm.keySet();
        for(Object obj : keys)
        {
            plugin.config.set(prefix + obj.toString(), hm.get(obj));
        }
        plugin.saveConfig();
    }
    
    public void overrideSaveHashMap(HashMap hm, String prefix)
    {
        plugin.config.set(prefix, null);
        plugin.saveConfig();
        String oldPrefix = prefix;
        if(prefix != null) {prefix = oldPrefix + ".";}
        
        Set keys = hm.keySet();
        for(Object obj : keys)
        {
            plugin.config.set(prefix + obj.toString(), hm.get(obj));
        }
        plugin.saveConfig();
    }
}
