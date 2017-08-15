package com.Shadersoft.UniverseMG.utils;

import com.Shadersoft.UniverseMG.UniverseMG;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Date;

public abstract class UMGOfflinePlayer implements OfflinePlayer {

    private String banReason = UniverseMG.plugin.config.getString("default-ban-reason");

    public boolean setBanned(boolean input, BanList.Type type, Date expires) {
        if(this.isBanned() == input) {
            return false;
        }

        Bukkit.getBanList(type).addBan(this.getName(), banReason, expires, "Undefined");
        return true;
    }

    public void setBanReason(String banReason) {
        this.banReason = banReason;
    }

    public String getBanReason() {
        return banReason;
    }
}
