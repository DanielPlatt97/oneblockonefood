package me.marenji.oneblockonefood;

import me.marenji.oneblockonefood.listeners.BlockBreakListener;
import me.marenji.oneblockonefood.listeners.BlockPlaceListener;
import me.marenji.oneblockonefood.listeners.ConsumeListener;
import me.marenji.oneblockonefood.listeners.RespawnListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class OneBlockOneFood extends JavaPlugin {

    private static OneBlockOneFood single_instance = null;

    public OneBlockOneFood() {
        super();
        initStaticSingleInstance();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("OneBlockOneFood plugin started");

        saveDefaultConfig();
        new BlockBreakListener();
        new ConsumeListener();
        new RespawnListener();
        new BlockPlaceListener();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("OneBlockOneFood plugin stopped");
    }

    public static OneBlockOneFood getInstance()
    {
        if (single_instance == null) {
            throw new AssertionError(
                    "The singleton has not been initialised, call initStaticSingleInstance first"
            );
        }
        return single_instance;
    }

    private void initStaticSingleInstance() {
        if (single_instance != null)
        {
            throw new AssertionError("The singleton was already initialised");
        }
        single_instance = this;
    }

}
