package com.tenjava.entries.xDukeLukex.t2;

import com.tenjava.entries.xDukeLukex.t2.Listeners.Listeners;
import com.tenjava.entries.xDukeLukex.t2.Util.GameState;
import com.tenjava.entries.xDukeLukex.t2.Util.InjectWorld;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {

    private static TenJava instance;
    private PluginManager pm;
    private GameState gameState;
    public String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Xenn" + ChatColor.DARK_GRAY + "]: " + ChatColor.RESET;

    @Override
    public void onEnable() {
        instance = this;
        if(!InjectWorld.injectWorld()){
            getLogger().info(ChatColor.RED + "Could not inject the Arena world into the server! Disabling Xenn...");
            getServer().getPluginManager().disablePlugin(this);
        }

        pm = getServer().getPluginManager();

        pm.registerEvents(new Listeners(), this);

        GameState.setGameState(GameState.LOBBYING);

    }

    @Override
    public void onDisable() {
        instance = null;
        pm = null;
    }

    public static TenJava getInstance(){
        return instance;
    }

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }

    public GameState getGameState(){
        return gameState;
    }
}
