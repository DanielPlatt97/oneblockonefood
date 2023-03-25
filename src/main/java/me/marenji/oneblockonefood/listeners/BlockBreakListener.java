package me.marenji.oneblockonefood.listeners;

import me.marenji.oneblockonefood.OneBlockOneFood;
import me.marenji.oneblockonefood.player.PlayerHungerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    public BlockBreakListener() {
        Bukkit.getPluginManager().registerEvents(
                this,
                OneBlockOneFood.getInstance()
        );
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        var hungerMan = PlayerHungerManager.getInstance();
        var player = event.getPlayer();
        if (!hungerMan.canUpdateBlock(player)) {
            event.setCancelled(true);
            return;
        }

        var block = event.getBlock();
        if (block.getType() == Material.HAY_BLOCK) {
            event.setDropItems(false);
        }

        var breakSpeed = block.getBreakSpeed(player);
        if (Float.isInfinite(breakSpeed)) return;
        hungerMan.subtractFoodForBlockUpdate(player);
    }
}
