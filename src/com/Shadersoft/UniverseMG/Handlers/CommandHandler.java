package com.Shadersoft.UniverseMG.Handlers;

import com.Shadersoft.UniverseMG.Coins.Coins;
import com.Shadersoft.UniverseMG.Messages;
import com.Shadersoft.UniverseMG.Ranks.Rank;
import com.Shadersoft.UniverseMG.Ranks.RankType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import com.Shadersoft.UniverseMG.UniverseMG;
import com.Shadersoft.UniverseMG.utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandHandler implements Listener
{
    private static UniverseMG plugin;

    public CommandHandler(UniverseMG instance)
    {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPreprocessCommand(PlayerCommandPreprocessEvent e)
    {

    }
}

