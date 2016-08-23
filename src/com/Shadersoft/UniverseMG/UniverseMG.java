package com.Shadersoft.UniverseMG;

import com.Shadersoft.UniverseMG.Handlers.Handlerlist;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class UniverseMG extends JavaPlugin {

    public static UniverseMG plugin;
    public FileConfiguration config;
    public String pluginName;
    public String pluginVersion;
    public PluginDescriptionFile info;
    
    public Handlerlist handlers;

    @Override
    public void onEnable()
    {
        //Initialize Variables
        handlers = new Handlerlist();
        plugin = this;
        config = getConfig();
        info = getDescription();
        pluginName = info.getFullName();
        pluginVersion = info.getVersion();

        //Initialize Commands
        this.handlers.commandHandler.registerCommands();
        
        //Send Message
        System.out.println(pluginName + " version " + pluginVersion + " has been enabled!");
    }

    @Override
    public void onDisable()
    {
        System.out.println(pluginName + " version " + pluginVersion + " has been enabled!");
    }
}
