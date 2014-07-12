package com.tenjava.entries.xDukeLukex.t2.Listeners;

import com.tenjava.entries.xDukeLukex.t2.TenJava;
import com.tenjava.entries.xDukeLukex.t2.Util.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Created by Luke on 12/07/2014.
 */
public class Listeners implements Listener {
    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event){
        if(GameState.getGameState() != GameState.LOBBYING){
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, TenJava.getInstance().prefix + ChatColor.RED + "This game has already started!");
        }
        if(Bukkit.getOnlinePlayers().length >= 4){
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, TenJava.getInstance().prefix + ChatColor.RED + "This game is full!");
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.setJoinMessage(TenJava.getInstance().prefix + ChatColor.GREEN + event.getPlayer().getDisplayName() + ChatColor.YELLOW + " joined the game! (" + ChatColor.LIGHT_PURPLE + String.valueOf(Bukkit.getOnlinePlayers().length) + ChatColor.YELLOW + "/" + ChatColor.LIGHT_PURPLE + "4" + ChatColor.YELLOW + ")");
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onLeafDecay(LeavesDecayEvent event){
        event.setCancelled(true);
    }
}
