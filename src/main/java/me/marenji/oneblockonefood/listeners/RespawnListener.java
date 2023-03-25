package me.marenji.oneblockonefood.listeners;

import me.marenji.oneblockonefood.OneBlockOneFood;
import me.marenji.oneblockonefood.player.PlayerHungerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {

    public RespawnListener() {
        Bukkit.getPluginManager().registerEvents(
                this,
                OneBlockOneFood.getInstance()
        );
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        var hungerManager = PlayerHungerManager.getInstance();
        var plugin = OneBlockOneFood.getInstance();

        // apply the status effect 3 ticks after respawn since the player will not be alive yet
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                hungerManager.handleRespawn(player);
            }
        }, 3L);
    }

}

