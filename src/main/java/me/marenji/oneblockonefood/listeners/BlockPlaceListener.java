package me.marenji.oneblockonefood.listeners;

import me.marenji.oneblockonefood.OneBlockOneFood;
import me.marenji.oneblockonefood.player.PlayerHungerManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
    public BlockPlaceListener() {
        Bukkit.getPluginManager().registerEvents(
                this,
                OneBlockOneFood.getInstance()
        );
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event) {
        var player = event.getPlayer();
        var block = event.getBlock();
        var breakSpeed = block.getBreakSpeed(player);
        if (Float.isInfinite(breakSpeed)) return;

        var hungerMan = PlayerHungerManager.getInstance();
        if (!hungerMan.canUpdateBlock(player)) {
            event.setCancelled(true);
            return;
        }

        hungerMan.subtractFoodForBlockUpdate(player);
    }
}
