package com.Shadersoft.UniverseMG.Commands;

import com.Shadersoft.UniverseMG.Ranks.Rank;
import org.bukkit.command.CommandExecutor;

public interface UMGCommand extends CommandExecutor
{
    public Rank getRank();
}
