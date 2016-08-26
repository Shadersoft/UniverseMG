package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.command.CommandExecutor;

import com.Shadersoft.UniverseMG.Ranks.Rank;

public interface UMGCommand extends CommandExecutor
{
    public Rank getRank();
}


//~ Formatted by Jindent --- http://www.jindent.com
