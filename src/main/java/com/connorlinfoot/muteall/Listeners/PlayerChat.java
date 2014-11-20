package com.connorlinfoot.muteall.Listeners;

import com.connorlinfoot.muteall.MuteAll;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if(MuteAll.isMuted && !player.hasPermission("muteall.bypass"))
            event.setCancelled(true);
    }

}
