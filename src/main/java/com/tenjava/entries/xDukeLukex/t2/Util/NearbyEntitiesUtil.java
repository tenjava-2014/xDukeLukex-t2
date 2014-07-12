package com.tenjava.entries.xDukeLukex.t2.Util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luke on 01/06/2014.
 */
public class NearbyEntitiesUtil {

    public static List<Entity> getNearbyEntities(Location where, int range) {
        List<Entity> found = new ArrayList<Entity>();

        for (Entity entity : where.getWorld().getEntities()) {
            if (isInBorder(where, entity.getLocation(), range)) {
                found.add(entity);
            }
        }
        return found;
    }

    private static boolean isInBorder(Location center, Location loc, int range) {
        Vector a = center.toVector();
        Vector b = loc.toVector();

        if (a.distance(b) <= range) {
            return true;
        }

        return false;
    }

}