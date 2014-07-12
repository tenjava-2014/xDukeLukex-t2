package com.tenjava.entries.xDukeLukex.t2.Util;

import com.tenjava.entries.xDukeLukex.t2.TenJava;

/**
 * Created by Luke on 12/07/2014.
 */
public enum GameState {
    LOBBYING, IN_PROGRESS, RESTARTING;

    public static void setGameState(GameState gameState){
        TenJava.getInstance().setGameState(gameState);
    }

    public static GameState getGameState(){
        return TenJava.getInstance().getGameState();
    }
}
