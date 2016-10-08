package com.Shadersoft.UniverseMG.Coins;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.Shadersoft.UniverseMG.UniverseMG;

public class CoinConfig
{
    public FileConfiguration config     = null;
    public File              configFile = null;
    final UniverseMG         plugin;

    public CoinConfig(UniverseMG instance)
    {
        this.plugin = instance;
    }

    public void loadConfig()
    {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void reloadConfig()
    {
        if(this.configFile == null)
        {
            this.configFile = new File(this.plugin.getDataFolder(), "coins.yml");
        }

        this.config = YamlConfiguration.loadConfiguration(this.configFile);
    }

    public void saveConfig()
    {
        if((this.config == null) || (this.configFile == null))
        {
            return;
        }

        try
        {
            this.config.save(this.configFile);
        }
        catch(IOException e)
        {
        }
    }

    public FileConfiguration getConfig()
    {
        if(this.config == null)
        {
            reloadConfig();
        }

        return this.config;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
