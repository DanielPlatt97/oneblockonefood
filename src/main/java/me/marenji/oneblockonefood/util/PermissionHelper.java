package me.marenji.oneblockonefood.util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class PermissionHelper {

    public static boolean isSenderAdmin(CommandSender sender) {
        boolean valid = false;
        if ( sender instanceof Player) {
            Player senderPlayer = (Player)sender;
            if ( senderPlayer.isOp() ) {
                valid = true;
            }
        } else {
            valid = true;
        }
        return valid;
    }

}