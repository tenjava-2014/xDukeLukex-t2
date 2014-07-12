package com.tenjava.entries.xDukeLukex.t2;

import com.tenjava.entries.xDukeLukex.t2.Listeners.Listeners;
import com.tenjava.entries.xDukeLukex.t2.Util.GameState;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {

    private static TenJava instance;
    private PluginManager pm;
    private GameState gameState;

    @Override
    public void onEnable() {
        pm = getServer().getPluginManager();

        pm.registerEvents(new Listeners(), this);

        instance = this;
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
