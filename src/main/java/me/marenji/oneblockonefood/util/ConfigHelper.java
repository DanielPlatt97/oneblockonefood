package me.marenji.oneblockonefood.util;

import me.marenji.oneblockonefood.OneBlockOneFood;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Map;


public final class ConfigHelper {

    public static int getDefaultHungerChange() {
        return getInt("defaulthungerchange");
    }

    private static String getString(String key) {
        var stringValue = OneBlockOneFood
                .getInstance()
                .getConfig()
                .getString(key);
        return stringValue;
    }

    private static int getInt(String key) {
        var integerValue = OneBlockOneFood
                .getInstance()
                .getConfig()
                .getInt(key);
        return integerValue;
    }

    private static Iterable<Map<?, ?>> getMapListFromConfig(String key) {
        var objectList = OneBlockOneFood
                .getInstance()
                .getConfig()
                .getMapList(key);
        return objectList;
    }

}
