package com.caizii.homewardoraxenpatch.util.configgetter;

import com.caizii.homewardoraxenpatch.util.loader.ItemListLoader;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemListGetter extends ItemListLoader {

    private List<Material> blackList = new ArrayList<>();

    public void init() {
        List<?> minecraft = itemBlackListConfig.getList("MINECRAFT", null);
        for (Object element : minecraft) {
            blackList.add(Material.getMaterial((String) element));
        }
    }

    public List<Material> getBlackList() {
        return blackList;
    }
}
