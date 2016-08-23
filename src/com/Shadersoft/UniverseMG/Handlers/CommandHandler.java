package com.Shadersoft.UniverseMG.Handlers;

import com.Shadersoft.UniverseMG.UniverseMG;
import org.bukkit.command.CommandExecutor;


public class CommandHandler implements UMGHandler 
{
    public UniverseMG plugin = UniverseMG.plugin;
    
    public void registerCommand(String command, CommandExecutor executor)
    {
        plugin.getCommand(command).setExecutor(executor);
    }
    
    public void registerCommands()
    {
        //No commands registered
    }
}
