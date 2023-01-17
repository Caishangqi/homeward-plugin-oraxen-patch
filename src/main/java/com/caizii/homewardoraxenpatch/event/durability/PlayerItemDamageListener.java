package com.caizii.homewardoraxenpatch.event.durability;

import com.caizii.homewardoraxenpatch.HomeWardOraxenPatch;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class PlayerItemDamageListener implements Listener {

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {
        //HomeWardOraxenPatch.customLogger.send("这应该触发伤害事件", event.getPlayer());
        Damageable itemMeta = (Damageable) event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
        int durabilityLevel = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.DURABILITY);

        //Calculate percentage
        if (durabilityLevel != 0) {
            double percentage = (100 / (durabilityLevel + 1)) * 0.01;
            boolean toDoDeduct = Math.random() < percentage;
            if (toDoDeduct)
                doDamageDeduction(event, itemMeta);
        } else {
            doDamageDeduction(event, itemMeta);
        }


    }

    private void doDamageDeduction(PlayerItemDamageEvent event, Damageable meta) {
        int metaDamage = meta.getDamage();
        meta.setDamage(metaDamage + event.getDamage());
        ItemStack inMainHand = event.getPlayer().getInventory().getItemInMainHand();
        inMainHand.setItemMeta(meta);
        event.getPlayer().getInventory().setItemInMainHand(inMainHand);
    }

}
