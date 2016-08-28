package com.Shadersoft.UniverseMG.Ranks;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


import com.Shadersoft.UniverseMG.UniverseMG;
import org.bukkit.command.CommandSender;

public enum Rank
{
    OWNER(RankType.STAFF, "Owner", 6, "Owner", ChatColor.DARK_RED),
    MAINDEV(RankType.STAFF, "Main Developer", 5, "MDev", ChatColor.YELLOW),
    DEV(RankType.STAFF, "Developer", 4, "Dev", ChatColor.DARK_PURPLE),
    ADMIN(RankType.STAFF, "Admin", 3, "Admin", ChatColor.GOLD),
    MOD(RankType.STAFF, "Moderator", 2, "Mod", ChatColor.GREEN),
    HELPER(RankType.STAFF, "Helper", 1, "Helper", ChatColor.AQUA),
    PLAYER(RankType.PLAYER, "Player", 0, null, ChatColor.WHITE),
    IMPOSTOR(RankType.PLAYER, "Imposter(IP)", -1, "Imp", ChatColor.ITALIC);

    // not sure if we need imposter, server is not cracked?

    private String     tag      = null;
    private String     name     = null;
    private int        priority = 0;
    private ChatColor  color    = ChatColor.WHITE;
    private static UniverseMG plugin   = UniverseMG.plugin;
    private RankType   type;

    Rank(RankType type, String name, int priority, String tag, ChatColor color)
    {
        this.name     = name;
        this.priority = priority;
        this.color    = color;
        this.tag      = tag;
        this.type     = type;
    }

    public static void addAdmin(Player admin, Rank rank)
    {
        plugin.config.set("ranks." + admin.getName(), rank.toString());
        plugin.saveConfig();
    }

    public static void removeAdmin(Player admin)
    {
        plugin.config.set("ranks." + admin.getName(), null);
        plugin.saveConfig();
    }
    
    public ChatColor getColor()
    {
        return color;
    }

    public String getDisplayName()
    {
        return color + name;
    }

    public String getDisplayTag()
    {
        return color + tag;
    }

    public String getName()
    {
        return name;
    }

    public static Rank getSenderRank(CommandSender player)
    {
        if(!(player instanceof Player)) {return OWNER;}
        
        if(plugin.config.contains("ranks." + player.getName()))
        {
            return getRank(plugin.config.getString("ranks." + player.getName()));
        }
        return PLAYER;
    }

    public int getPriority()
    {
        return priority;
    }

    public static Rank getRank(String string)
    {
        for (Rank rank : Rank.values())
        {
            if(string.equalsIgnoreCase(rank.toString()))
            {
                return rank;
            }
        }

        return Rank.PLAYER;
    }

    public static Rank getRankFromName(String playerName)
    {
        if(plugin.config.contains("ranks." + playerName))
        {
            return getRank(plugin.config.getString("ranks." + playerName));
        }
        return PLAYER;
    }

    public String getTag()
    {
        return tag;
    }

    public RankType getType()
    {
        return type;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
