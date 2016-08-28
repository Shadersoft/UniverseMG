package com.Shadersoft.UniverseMG;

import java.util.HashMap;
import java.util.List;

import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.Shadersoft.UniverseMG.Banning.BanConfig;
import com.Shadersoft.UniverseMG.Commands.*;
import com.Shadersoft.UniverseMG.Handlers.Handlerlist;
import com.Shadersoft.UniverseMG.Handlers.PlayerHandler;
import com.Shadersoft.UniverseMG.Ranks.Rank;

public class UniverseMG extends JavaPlugin
{
    public static UniverseMG                plugin;
    public HashMap<String, CommandExecutor> commandList = new HashMap();
    public FileConfiguration                config;
    public BanConfig                        banconfig;
    public String                           pluginName;
    public String                           pluginVersion;
    public List<String>                     pluginAuthors;
    public PluginDescriptionFile            info;
    public Handlerlist                      handlers;

    public UniverseMG()
    {
        this.banconfig = new BanConfig(this);
    }

    @Override
    public void onDisable()
    {
        System.out.println(pluginName + " version " + pluginVersion + " has been enabled!");
    }

    @Override
    public void onEnable()
    {
        // Initialize Variables
        handlers      = new Handlerlist();
        plugin        = this;
        config        = getConfig();
        info          = getDescription();
        pluginName    = info.getFullName();
        pluginVersion = info.getVersion();
        pluginAuthors = info.getAuthors();

        // Initialize Commands
        getCommand("universemg").setExecutor(new Command_universemg());
        getCommand("addadmin").setExecutor(new Command_addadmin());
        getCommand("unban").setExecutor(new Command_unban());
        getCommand("ban").setExecutor(new Command_ban());
        getCommand("banhistory").setExecutor(new Command_banhistory());
        getCommand("adminlist").setExecutor(new Command_adminlist());
        getCommand("sban").setExecutor(new Command_sban());
        getCommand("kick").setExecutor(new Command_kick());
        getCommand("removeadmin").setExecutor(new Command_removeadmin());

        // Listeners / Handlers
        getServer().getPluginManager().registerEvents(new PlayerHandler(this), this);

        // Create config
        this.saveDefaultConfig();

        // Send Message
        System.out.println(pluginName + " version " + pluginVersion + " has been enabled!");
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
