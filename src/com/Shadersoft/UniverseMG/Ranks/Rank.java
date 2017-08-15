package com.Shadersoft.UniverseMG.Ranks;


import com.Shadersoft.UniverseMG.utils.ConfigPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


import com.Shadersoft.UniverseMG.UniverseMG;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.bukkit.Bukkit.getOfflinePlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public enum Rank
{
    FOUNDER(RankType.STAFF, "Founder", 6, "Founder", ChatColor.DARK_RED, "the"),
    OWNER(RankType.STAFF, "Owner", 6, "Owner", ChatColor.BLUE, "an"),
    DEVELOPER(RankType.STAFF, "Developer", 5, "Dev", ChatColor.DARK_PURPLE, "a"),
    ADMIN(RankType.STAFF, "Admin", 4, "Admin", ChatColor.RED, "an"),
    SENIORMOD(RankType.STAFF, "Sr. Moderator", 3, "Sr Mod", ChatColor.DARK_AQUA, "a"),
    MODERATOR(RankType.STAFF, "Moderator", 2, "Mod", ChatColor.GOLD, "a"),
    JUNIORMOD(RankType.STAFF, "Jr. Moderator", 1, "Jr Mod", ChatColor.YELLOW, "a"),
    BUILDER(RankType.SPECIAL, "Builder", 0, "Builder", ChatColor.AQUA, "a"),
    FRIENDS(RankType.PLAYER, "Friend", 0, "Friends", ChatColor.GREEN, "a"),
    MEMBER(RankType.PLAYER, "Member", 0, "Member", ChatColor.DARK_GREEN, "a"),
    IMPOSTOR(RankType.PLAYER, "Impostor(IP)", -1, "Imp", ChatColor.ITALIC, "an");

    // not sure if we need impostor, server is not cracked?
    private static final UniverseMG plugin = UniverseMG.plugin;
    private static final PlayerConfig pConfig = plugin.playerConfig;
    
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
        final List<String> ips = new ArrayList<>();
        ips.add(admin.getAddress().getHostString());
        ConfigPlayer configPlayer = new ConfigPlayer(admin.getName(), admin.getUniqueId(), rank, ips);
        plugin.saveConfig();
    }

    public static void removeAdmin(Player admin)
    {
        pConfig.getConfig().set(admin.getName(), null);
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
        if(tag != "") {return ChatColor.DARK_GRAY + "[" + color + tag + ChatColor.DARK_GRAY + "]";}
        else {return "";}
    }

    public String getName()
    {
        return name;
    }

    public static Rank getSenderRank(CommandSender player)
    {
        if(!(player instanceof Player)) {return OWNER;}

        ConfigPlayer configPlayer = ConfigPlayer.getFromEntry(player.getName());

        if(configPlayer != null)
        {
            return configPlayer.getRank();
        }
        return MEMBER;
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

        return Rank.MEMBER;
    }

    public static boolean isRank(String string)
    {
        for (Rank rank : Rank.values())
        {
            if(string.equalsIgnoreCase(rank.toString()))
            {
                return true;
            }
        }

        return false;
    }

    public static Rank getRankFromName(String playerName)
    {
        ConfigPlayer configPlayer = ConfigPlayer.getFromEntry(playerName);
        if(configPlayer != null)
        {
            return getRank(plugin.config.getString("ranks." + playerName));
        }
        return MEMBER;
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
    
    public static HashMap<Player, Rank> getOnlineStaff()
    {
        HashMap<Player, Rank> onlineStaff = new HashMap();
        for(Player player : plugin.getServer().getOnlinePlayers())
        {
            if(getSenderRank((CommandSender)player).getType() == RankType.STAFF)
            {
                onlineStaff.put(player, getSenderRank((CommandSender)player));
            }
        }
        return onlineStaff;
    }
    
    public static HashMap<OfflinePlayer, Rank> getOfflineStaff()
    {
        HashMap<OfflinePlayer, Rank> admins = new HashMap();
                
        for(String pname : plugin.config.getConfigurationSection("ranks").getKeys(false))
        {
            OfflinePlayer p = getOfflinePlayer(pname);
            Rank rank = Rank.getRank(plugin.config.getString("ranks." + pname));
            admins.put(p, rank);
        }
        
        return admins;
    }
}
