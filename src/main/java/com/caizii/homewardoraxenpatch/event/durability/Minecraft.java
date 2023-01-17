package com.caizii.homewardoraxenpatch.event.durability;

import com.caizii.homewardoraxenpatch.HomeWardOraxenPatch;
import io.th0rgal.oraxen.api.events.OraxenNoteBlockBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class Minecraft implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onBlockDestroy(final OraxenNoteBlockBreakEvent event) {

        Material material = event.getPlayer().getInventory().getItemInMainHand().getType();
        if (HomeWardOraxenPatch.itemListGetter.getBlackList().contains(material) || (material.getMaxDurability() == 0))
            return;

        Bukkit.getServer().getPluginManager().callEvent(new PlayerItemDamageEvent(event.getPlayer(), event.getPlayer().getInventory().getItemInMainHand(), 1));

    }

}
