package com.Shadersoft.UniverseMG.Handlers;

import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.HashMap;
import java.util.Set;


public class ConfigHandler implements UMGHandler
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
    
    public HashMap<String, String> getStringHashMap(String prefix)
    {
        HashMap<String, String> map = new HashMap();
        Set<String> keys = plugin.config.getConfigurationSection(prefix).getKeys(false);
        for(String key : keys)
        {
            map.put(key, plugin.config.getString(prefix + "." + key));
        }
        return map;
    }
}
