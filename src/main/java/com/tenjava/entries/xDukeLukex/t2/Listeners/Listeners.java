package com.tenjava.entries.xDukeLukex.t2.Listeners;

import com.tenjava.entries.xDukeLukex.t2.TenJava;
import com.tenjava.entries.xDukeLukex.t2.Util.Shot;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;
import java.util.Random;

/**
 * Created by Luke on 12/07/2014.
 */
public class Listeners implements Listener {
    @EventHandler
    public void onMobDeath(EntityDeathEvent event){
        if(!(event.getEntity() instanceof Monster)){
            return;
        }

        event.setDroppedExp(0);

        for(int i = 0; i < event.getEntity().getMaxHealth(); i++){
            if((new Random().nextInt(1) % 2) == 0){
                event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.EXPERIENCE_ORB);
            }
        }
    }

    @EventHandler
    public void onExpChange(PlayerExpChangeEvent event){
        event.getPlayer().sendMessage(TenJava.getInstance().prefix + ChatColor.GREEN + "You have gained more energy!");
    }

    @EventHandler
    public void onConsume(FoodLevelChangeEvent event){
        if(!(event.getEntity() instanceof Player)){
            return;
        }

        Player player = (Player) event.getEntity();
        player.giveExp(event.getFoodLevel());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(!event.getPlayer().getItemInHand().equals(TenJava.gun)){
            return;
        }
        event.setCancelled(true);

        if(event.getAction() == Action.LEFT_CLICK_AIR|| event.getAction() == Action.LEFT_CLICK_BLOCK){
            return;
        }

        if(event.getPlayer().getExp() * 100000 >= 10){
            new Shot(event.getPlayer().getEyeLocation(), event.getPlayer().getTargetBlock(new HashSet<Byte>(), 100).getLocation(), event.getPlayer());
            event.getPlayer().setExp(event.getPlayer().getTotalExperience() - 10 / 100000);
        }else{
            event.getPlayer().sendMessage(TenJava.getInstance().prefix + ChatColor.RED + "You do not have enough NRG!" + " You need " + ChatColor.YELLOW + String.valueOf(10 - event.getPlayer().getExp()) + ChatColor.RED + " more!");
            event.getPlayer().playSound(event.getPlayer().getEyeLocation(), Sound.CLICK, 1, 1);
        }
    }
}
