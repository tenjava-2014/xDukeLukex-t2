package com.tenjava.entries.xDukeLukex.t2.Util;

import com.tenjava.entries.xDukeLukex.t2.TenJava;
import net.minecraft.server.v1_7_R3.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import java.util.ArrayList;

/**
 * Created by Luke on 12/07/2014.
 */
public class Shot {

    private double a;
    private Location b;
    private Location c;
    private LivingEntity shooter;

    public Shot(Location loc1, Location loc2, LivingEntity shooter) {
        Vector v = loc2.toVector();
        Vector v2 = loc1.toVector();

        this.a = v.distance(v2);

        this.b = loc2;
        this.c = loc1;
        this.shooter = shooter;
        this.shoot();
    }

    public int getIDistance() {
        return (int) a;
    }

    public double getDDistance() {
        return a;
    }

    public ArrayList<Location> path() {
        ArrayList<Location> paths = new ArrayList<Location>();
        double x = b.getX() - c.getX();
        double y = b.getY() - c.getY();
        double z = b.getZ() - c.getZ();

        double xD = x / this.getDDistance();
        double yD = y / this.getDDistance();
        double zD = z / this.getDDistance();

        for (int d = 1; d <= this.getIDistance(); d++) {
            if ((d % 2) == 0) {
                Location loc = new Location(c.getWorld(), c.getX() + (xD * d), c.getY() + (yD * d), c.getZ() + (zD * d));
                paths.add(loc);
            }
        }

        return paths;
    }

    public void shoot() {

        final ArrayList<Location> loc = path();

        for (int i = 0; i < loc.size(); i++) {
            final int d = i;
            Bukkit.getScheduler().scheduleSyncDelayedTask(TenJava.getInstance(), new Runnable() {
                @Override
                public void run() {
                    for (Entity e : NearbyEntitiesUtil.getNearbyEntities(loc.get(d), 2)) {
                        if (e instanceof LivingEntity) {
                            LivingEntity l = (LivingEntity) e;
                            if (l != shooter) {
                                l.damage((double) 1000);
                                sendPacket("explode", loc.get(d), 0, 100);
                            }
                        }
                    }
                    if (d == 0) {
                        sendPacket("largesmoke", loc.get(d), 0, 1);
                        loc.get(d).getWorld().playSound(loc.get(d), Sound.FIREWORK_BLAST, 10, 2f);
                    } else {
                        if (d == loc.size() - 1) {
                            sendPacket("explode", loc.get(d), 0, 100);
                        } else {

                            final FallingBlock fb = shooter.getWorld().spawnFallingBlock(loc.get(d), Material.ICE, (byte) 0);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(TenJava.getInstance(), new Runnable() {
                                @Override
                                public void run() {
                                    fb.remove();
                                }
                            }, 4);

                            sendPacket("reddust", loc.get(d), 0, 100);
                            loc.get(d).getWorld().playEffect(loc.get(d), Effect.STEP_SOUND, 79);
                        }
                    }
                }
            }, (long) d);
        }
    }

    public void sendPacket(String particle, Location location, float data, int amount) {
        PacketPlayOutWorldParticles p = new PacketPlayOutWorldParticles(particle, (float) location.getX(), (float) location.getY(), (float) location.getZ(), 0, 0, 0, data, amount);
        for (Player player : location.getWorld().getPlayers()) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(p);
        }
    }
}