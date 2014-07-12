package com.tenjava.entries.xDukeLukex.t2;

import com.tenjava.entries.xDukeLukex.t2.Listeners.Listeners;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {

    private static TenJava instance;
    private PluginManager pm;
    public String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "NRGuns" + ChatColor.DARK_GRAY + "]: " + ChatColor.RESET;

    public static ItemStack gun = new ItemStack(Material.GOLD_HOE, 1);

    static {
        ItemMeta im = gun.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "GUN");
        gun.setItemMeta(im);
        gun.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 10);
    }

    @Override
    public void onEnable() {
        instance = this;

        pm = getServer().getPluginManager();

        pm.registerEvents(new Listeners(), this);

        ShapedRecipe sr = new ShapedRecipe(gun);
        sr.shape("@@@",
                "  +",
                "  +");
        sr.setIngredient('@', Material.GOLD_BLOCK);
        sr.setIngredient('+', Material.STICK);

        getServer().addRecipe(sr);
    }

    @Override
    public void onDisable() {
        instance = null;
        pm = null;
    }

    public static TenJava getInstance() {
        return instance;
    }
}
