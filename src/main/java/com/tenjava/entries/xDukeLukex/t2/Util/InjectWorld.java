package com.tenjava.entries.xDukeLukex.t2.Util;

import com.tenjava.entries.xDukeLukex.t2.TenJava;

import java.io.File;

/**
 * Created by Luke on 12/07/2014.
 */
public class InjectWorld {
    public static boolean injectWorld(){
        File file = new File(TenJava.getInstance().getServer().getWorldContainer().getAbsolutePath());
        if(!file.delete()){
            return false;
        }

        return true;
    }
}
