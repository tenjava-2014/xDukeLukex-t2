package com.tenjava.entries.xDukeLukex.t2.Util;

import com.tenjava.entries.xDukeLukex.t2.TenJava;

/**
 * Created by Luke on 12/07/2014.
 */
public class InjectWorld {
    public static String injectWorld(){
        String pluginFolderLoc = TenJava.getInstance().getDataFolder().toString();
        return pluginFolderLoc;
    }
}
