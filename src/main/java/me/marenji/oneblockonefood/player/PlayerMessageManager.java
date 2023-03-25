package me.marenji.oneblockonefood.player;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public final class PlayerMessageManager {

    private CommandSender sender;

    public PlayerMessageManager(CommandSender player) {
        this.sender = player;
    }

    public void message(String text) {
        sender.sendMessage(
                ChatColor.translateAlternateColorCodes('&', text)
        );
    }

}