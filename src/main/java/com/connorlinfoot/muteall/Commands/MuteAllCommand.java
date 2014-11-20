package com.connorlinfoot.muteall.Commands;

import com.connorlinfoot.muteall.MuteAll;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteAllCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if( args.length >= 1 && args[0].equalsIgnoreCase("reload") ){
            if( !sender.hasPermission("muteall.reload") ){
                sender.sendMessage(MuteAll.Prefix + ChatColor.RED + "You do not have the permission \"" + ChatColor.BOLD + "muteall.reload" + ChatColor.RESET + "" + ChatColor.RED + "\"");
                return false;
            }
            MuteAll.broadcastMessage = MuteAll.getPlugin().getConfig().getBoolean("Broadcast Message");
            MuteAll.broadcastMuted = MuteAll.getPlugin().getConfig().getString("Broadcast Mute Message");
            MuteAll.broadcastUnmuted = MuteAll.getPlugin().getConfig().getString("Broadcast Unmute Message");

            sender.sendMessage(MuteAll.Prefix + ChatColor.GREEN + "Config has been reloaded");
            return true;
        }

        if( sender.hasPermission("muteall.use") ){
            if( MuteAll.isMuted ){
                MuteAll.isMuted = false;
                if( MuteAll.broadcastMessage ){
                    String message = MuteAll.broadcastUnmuted;
                    message = message.replaceAll("%playername%",sender.getName());
                    Bukkit.broadcastMessage(message);
                }
                sender.sendMessage(MuteAll.Prefix + ChatColor.GREEN + "Chat as been un-muted");
            } else {
                MuteAll.isMuted = true;
                if( MuteAll.broadcastMessage ){
                    String message = MuteAll.broadcastMuted;
                    message = message.replaceAll("%playername%",sender.getName());
                    Bukkit.broadcastMessage(message);
                }
                sender.sendMessage(MuteAll.Prefix + ChatColor.GREEN + "Chat as been muted");
            }
            return true;
        }
        sender.sendMessage(ChatColor.AQUA + "\"" + MuteAll.getPlugin().getDescription().getName() + "\" - Version: " + MuteAll.getPlugin().getDescription().getVersion());
        return true;
    }

}
