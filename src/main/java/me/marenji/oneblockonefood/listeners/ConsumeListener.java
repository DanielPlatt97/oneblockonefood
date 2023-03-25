package me.marenji.oneblockonefood.listeners;

import me.marenji.oneblockonefood.OneBlockOneFood;
import me.marenji.oneblockonefood.player.PlayerHungerManager;
import me.marenji.oneblockonefood.player.PlayerMessageManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ConsumeListener implements Listener {
    
    public ConsumeListener() {
        Bukkit.getPluginManager().registerEvents(
                this,
                OneBlockOneFood.getInstance()
        );
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        var itemStack = event.getItem();
        var foodType = itemStack.getType();
        PlayerHungerManager.getInstance().handleCustomFoodValue(foodType, event.getPlayer());
    }
}