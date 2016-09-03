package com.Shadersoft.UniverseMG.utils;


import java.lang.reflect.Field;
import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_10_R1.PlayerConnection;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;



public class TablistUtil {
    public static void setForPlayer(Player p, String header, String footer){
       
        CraftPlayer craftplayer = (CraftPlayer)p;
        PlayerConnection connection = craftplayer.getHandle().playerConnection;
        IChatBaseComponent top = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
        IChatBaseComponent bottom = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");

        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try
        {
          Field headerField = packet.getClass().getDeclaredField("a");
          headerField.setAccessible(true);
          headerField.set(packet, top);
          headerField.setAccessible(!headerField.isAccessible());

          Field footerField = packet.getClass().getDeclaredField("b");
          footerField.setAccessible(true);
          footerField.set(packet, bottom);
          footerField.setAccessible(!footerField.isAccessible());
        } catch (Exception ev) {
          ev.printStackTrace();
        }

        connection.sendPacket(packet);
    }
}