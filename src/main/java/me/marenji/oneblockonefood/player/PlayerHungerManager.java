package me.marenji.oneblockonefood.player;

import me.marenji.oneblockonefood.util.ConfigHelper;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

public final class PlayerHungerManager {
    private static PlayerHungerManager single_instance = null;

    private HashMap<Material, Integer> oldFoodForMaterial;
    private HashMap<Material, Integer> newFoodForMaterial;

    private final int defaultHungerChange;

    public PlayerHungerManager() {
        defaultHungerChange = ConfigHelper.getDefaultHungerChange();
        oldFoodForMaterial = new HashMap<>();
        newFoodForMaterial = new HashMap<>();
        oldFoodForMaterial.put(Material.BREAD, 5);
        newFoodForMaterial.put(Material.BREAD, 2);
        oldFoodForMaterial.put(Material.COOKED_COD, 5);
        newFoodForMaterial.put(Material.COOKED_COD, 3);
        oldFoodForMaterial.put(Material.COOKED_SALMON, 6);
        newFoodForMaterial.put(Material.COOKED_SALMON, 3);
        oldFoodForMaterial.put(Material.BAKED_POTATO, 5);
        newFoodForMaterial.put(Material.BAKED_POTATO, 3);
        oldFoodForMaterial.put(Material.CARROT, 3);
        newFoodForMaterial.put(Material.CARROT, 2);
        oldFoodForMaterial.put(Material.GLOW_BERRIES, 2);
        newFoodForMaterial.put(Material.GLOW_BERRIES, 1);
        oldFoodForMaterial.put(Material.SWEET_BERRIES, 2);
        newFoodForMaterial.put(Material.SWEET_BERRIES, 1);
        oldFoodForMaterial.put(Material.HONEY_BOTTLE, 6);
        newFoodForMaterial.put(Material.HONEY_BOTTLE, 4);
        oldFoodForMaterial.put(Material.ROTTEN_FLESH, 4);
        newFoodForMaterial.put(Material.ROTTEN_FLESH, 1);

        oldFoodForMaterial.put(Material.SALMON, 2);
        newFoodForMaterial.put(Material.SALMON, 1);
        oldFoodForMaterial.put(Material.COD, 2);
        newFoodForMaterial.put(Material.COD, 1);
        oldFoodForMaterial.put(Material.POTATO, 1);
        newFoodForMaterial.put(Material.POTATO, 1);

        //oldFoodForMaterial.put(Material.COOKED_MUTTON, 2);
        //newFoodForMaterial.put(Material.COOKED_MUTTON, 2);
        oldFoodForMaterial.put(Material.BEEF, 3);
        newFoodForMaterial.put(Material.BEEF, 2);
        oldFoodForMaterial.put(Material.PORKCHOP, 3);
        newFoodForMaterial.put(Material.PORKCHOP, 2);
        oldFoodForMaterial.put(Material.RABBIT, 3);
        newFoodForMaterial.put(Material.RABBIT, 2);
        //oldFoodForMaterial.put(Material.CHICKEN, 2);
        //newFoodForMaterial.put(Material.CHICKEN, 2);

        oldFoodForMaterial.put(Material.COOKED_MUTTON, 6);
        newFoodForMaterial.put(Material.COOKED_MUTTON, 5);
        oldFoodForMaterial.put(Material.COOKED_BEEF, 8);
        newFoodForMaterial.put(Material.COOKED_BEEF, 5);
        oldFoodForMaterial.put(Material.PORKCHOP, 8);
        newFoodForMaterial.put(Material.PORKCHOP, 5);
        //oldFoodForMaterial.put(Material.RABBIT, 5);
        //newFoodForMaterial.put(Material.RABBIT, 5);
        oldFoodForMaterial.put(Material.CHICKEN, 6);
        newFoodForMaterial.put(Material.CHICKEN, 5);
    }

    public static PlayerHungerManager getInstance()
    {
        if (single_instance == null) {
            single_instance = new PlayerHungerManager();
        }
        return single_instance;
    }

    public void subtractFoodForBlockUpdate(Player player) {
        addFood(player, -defaultHungerChange, false);
    }

    public boolean handleCustomFoodValue(Material material, Player player) {
        if (!oldFoodForMaterial.containsKey(material)) return false;
        var foodChange = newFoodForMaterial.get(material) - oldFoodForMaterial.get(material);
        addFood(player, foodChange, true); // Allow negative as will be bumped back to positive
        return true;
    }

    public boolean canUpdateBlock(Player player) {
        return player.getFoodLevel() >= defaultHungerChange;
    }

    public void handleRespawn(Player player) {
        player.setFoodLevel(1);
    }

    private void addFood(Player player, int food, boolean allowNegative) {
        var origFood = player.getFoodLevel();
        var newFood = origFood + food;
        if (!allowNegative && newFood < 0) newFood = 0;
        player.setFoodLevel(newFood);
    }

}