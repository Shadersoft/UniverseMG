package com.Shadersoft.UniverseMG;

import com.Shadersoft.UniverseMG.Commands.Chats.*;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.Shadersoft.UniverseMG.Banning.BanConfig;
import com.Shadersoft.UniverseMG.Commands.*;
import com.Shadersoft.UniverseMG.Handlers.*;
import com.Shadersoft.UniverseMG.Ranks.ChatType;
import java.util.ArrayList;

public class UniverseMG extends JavaPlugin
{
    public static UniverseMG                               plugin;
    public HashMap<String, CommandExecutor>                commandList;
    public FileConfiguration                               config;
    public BanConfig                                       banconfig;
    public String                                          pluginName;
    public String                                          pluginVersion;
    public List<String>                                    pluginAuthors;
    public PluginDescriptionFile                           info;
    public HashMap<Player, ChatType>                       playerChats;
    public List<Player>                                    muted;
    public HashMap<Player, String>                         prefixes;
    public List<Player>                                    swearPlayers;

    @Override
    public void onDisable()
    {
        System.out.println(pluginName + " version " + pluginVersion + " has been disabled!");
    }

    @Override
    public void onEnable()
    {
        // Initialize Variables
        
        commandList   = new HashMap();
        banconfig     = new BanConfig(this);
        plugin        = this;
        config        = getConfig();
        info          = getDescription();
        pluginName    = info.getFullName();
        pluginVersion = info.getVersion();
        pluginAuthors = info.getAuthors();
        playerChats   = new HashMap();
        muted         = new ArrayList();
        prefixes      = new HashMap();
        swearPlayers  = new ArrayList();

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
        getCommand("mute").setExecutor(new Command_mute());
        getCommand("tag").setExecutor(new Command_tag());
        getCommand("broadcast").setExecutor(new Command_broadcast());
        getCommand("maintenance").setExecutor(new Command_maintenance());
        getCommand("filter").setExecutor(new Command_filter());
        getCommand("playerlist").setExecutor(new Command_playerlist());
        getCommand("overlord").setExecutor(new Command_overlord());
        getCommand("flip").setExecutor(new Command_flip());
        getCommand("forums").setExecutor(new Command_forums());
        getCommand("twitter").setExecutor(new Command_twitter());
        
        //  Chat Commands        
        getCommand("helperchat").setExecutor(new Command_helperchat());
        getCommand("modchat").setExecutor(new Command_modchat());
        getCommand("adminchat").setExecutor(new Command_adminchat());
        getCommand("devchat").setExecutor(new Command_devchat());
        getCommand("ownerchat").setExecutor(new Command_ownerchat());

        // Listeners / Handlers
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        getServer().getPluginManager().registerEvents(new ChatHandler(this), this);
        getServer().getPluginManager().registerEvents(new CommandHandler(this), this);
        getServer().getPluginManager().registerEvents(new PlayerHandler(this), this);        
        getServer().getPluginManager().registerEvents(new ServerListener(this), this);

        // Create configs
        this.saveDefaultConfig();

        // Send Message
        System.out.println(pluginName + " version " + pluginVersion + " has been enabled!");
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
