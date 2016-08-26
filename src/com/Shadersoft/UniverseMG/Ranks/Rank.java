package com.Shadersoft.UniverseMG.Ranks;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;

import com.Shadersoft.UniverseMG.UniverseMG;

public enum Rank
{
    OWNER(RankType.OWNER, "Owner", 6, "Owner", ChatColor.DARK_RED),
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
        plugin.adminList.put(admin, rank);
        plugin.handlers.configHandler.overrideSaveHashMap(plugin.adminList, "ranks");
    }

    public static void removeAdmin(Player admin)
    {
        plugin.adminList.remove(admin);
        plugin.handlers.configHandler.overrideSaveHashMap(plugin.adminList, "ranks");
    }

    public static HashMap<Player, Rank> getAdminList()
    {
        HashMap<Player, Rank> al   = new HashMap();
        Set<String>           keys = plugin.config.getConfigurationSection("ranks").getKeys(false);

        for (String key : keys)
        {
            Player admin = getPlayer(key);
            Rank   rank  = getRank(plugin.config.getString("ranks." + key));

            al.put(admin, rank);
        }

        return al;
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

    public static Rank getPlayerRank(Player player)
    {
        if(plugin.adminList.containsKey(player))
        {
            return plugin.adminList.get(player);
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
            if(string.equalsIgnoreCase(rank.getName())
                    || string.equalsIgnoreCase(rank.getDisplayName())
                    || string.equalsIgnoreCase(rank.toString()))
            {
                return rank;
            }
        }

        return Rank.PLAYER;
    }

    public static Rank getRankFromName(String playerName)
    {
        if(plugin.handlers.configHandler.getStringHashMap("ranks").containsKey(playerName))
        {
            return Rank.getRank(plugin.handlers.configHandler.getStringHashMap("ranks")
                                                                    .get(playerName)
                                                                    .toUpperCase());
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
