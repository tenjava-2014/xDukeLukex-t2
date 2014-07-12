package com.tenjava.entries.xDukeLukex.t2.Util;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luke on 12/07/2014.
 */
public enum PlayerState {
    ALIVE, DEAD;

    private static Map<String, PlayerState> playerStates = new HashMap<String, PlayerState>();

    public static void setPlayerState(Player player, PlayerState playerState){
        playerStates.put(player.getName(), playerState);
    }

    public static PlayerState getPlayerState(Player player, PlayerState playerState){
        if(!playerStates.containsKey(player.getName())){
            return null;
        }

        return playerStates.get(player.getName());
    }
}
