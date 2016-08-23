package com.Shadersoft.UniverseMG.Handlers;

import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.HashMap;
import org.bukkit.command.CommandExecutor;


public class CommandHandler implements UMGHandler 
{
    public UniverseMG plugin = UniverseMG.plugin;
    
    public HashMap<String, CommandExecutor> getCommands()
    {
        return plugin.commandList;
    }
    
    private void registerCommand(String command, CommandExecutor executor)
    {
        plugin.getCommand(command).setExecutor(executor);
    }
    
    public void registerCommands(HashMap<String, CommandExecutor> commandlist)
    {
        for(String command : commandlist.keySet())
        {
            registerCommand(command, commandlist.get(command));
        }
    }
}
