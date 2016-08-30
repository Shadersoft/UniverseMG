package com.Shadersoft.UniverseMG.Ranks;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


import com.Shadersoft.UniverseMG.UniverseMG;
import org.bukkit.command.CommandSender;

public enum Rank
{
    OWNER(RankType.STAFF, "Owner", 6, "Owner", ChatColor.DARK_RED, "the"),
    MAINDEV(RankType.STAFF, "Main Developer", 5, "Main Dev", ChatColor.YELLOW, "the"),
    DEV(RankType.STAFF, "Developer", 4, "Dev", ChatColor.DARK_PURPLE, "a"),
    ADMIN(RankType.STAFF, "Admin", 3, "Admin", ChatColor.GOLD, "an"),
    MODERATOR(RankType.STAFF, "Moderator", 2, "Mod", ChatColor.GREEN, "a"),
    HELPER(RankType.STAFF, "Helper", 1, "Helper", ChatColor.AQUA, "a"),
    PLAYER(RankType.PLAYER, "Player", 0, null, ChatColor.WHITE, "a"),
    IMPOSTOR(RankType.PLAYER, "Imposter(IP)", -1, "Imp", ChatColor.ITALIC, "an");

    // not sure if we need imposter, server is not cracked?
    private static final UniverseMG plugin   = UniverseMG.plugin;
    
    private String     tag      = null;
    private String     name     = null;
    private int        priority = 0;
    private ChatColor  color    = ChatColor.WHITE;
    private RankType   type;
    private String stringPrefix = null;

    Rank(RankType type, String name, int priority, String tag, ChatColor color, String stringPrefix)
    {
        this.name         = name;
        this.priority     = priority;
        this.color        = color;
        this.tag          = tag;
        this.type         = type;
        this.stringPrefix = stringPrefix;
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
    
    public String getStringPrefix()
    {
        return stringPrefix;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
