package com.caizii.homewardoraxenpatch.util.loader;

import com.caizii.homewardoraxenpatch.HomeWardOraxenPatch;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ItemListLoader {

    protected FileConfiguration itemBlackListConfig;

    public ItemListLoader() {
        loadConfiguration();
    }

    public void loadConfiguration() {
        itemBlackListConfig = YamlConfiguration.loadConfiguration(new File(HomeWardOraxenPatch.getInstance().getDataFolder(), "durability-patch/item-blacklist.yml"));
    }



}
