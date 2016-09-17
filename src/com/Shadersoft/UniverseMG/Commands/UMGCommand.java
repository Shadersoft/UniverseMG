package com.Shadersoft.UniverseMG.Commands;

import org.bukkit.command.CommandExecutor;

import com.Shadersoft.UniverseMG.Ranks.Rank;
import java.util.List;

public interface UMGCommand extends CommandExecutor
{
    public Rank getRank();
    public List<String> getAliases();
    public String getUsage();
    public String getDescription();
}


//~ Formatted by Jindent --- http://www.jindent.com
