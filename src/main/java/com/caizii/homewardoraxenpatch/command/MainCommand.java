package com.caizii.homewardoraxenpatch.command;

import com.caizi.mf.annotations.*;
import com.caizi.mf.base.CommandBase;
import com.caizi.utils.logs.LoggerManipulationType;
import com.caizii.homewardoraxenpatch.HomeWardOraxenPatch;
import com.caizii.homewardoraxenpatch.util.configgetter.ItemListGetter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("horaxen")
public class MainCommand extends CommandBase {

    @Default
    public void defaultCommand(final CommandSender commandSender) {
        HomeWardOraxenPatch.customLogger.send("Welcome to use HomewardOraxenPatch", (Player) commandSender);
    }

    @SubCommand("reload")
    public void reloadPlugin(final CommandSender commandSender, @Completion("#reloadType") @Optional final String type) {

        if (type != null) {
            switch (type) {
                case "durability":

                    HomeWardOraxenPatch.itemListGetter = new ItemListGetter();
                    HomeWardOraxenPatch.itemListGetter.init();


                    HomeWardOraxenPatch.consoleLogger.send(LoggerManipulationType.LOAD, "Durability List reload success");
                    if (commandSender instanceof Player)
                        HomeWardOraxenPatch.customLogger.send("Durability List reload success", (Player) commandSender);


            }
        } else {

        }

    }


    @SubCommand("showblacklist")
    public void showBlackList(final CommandSender commandSender) {
        HomeWardOraxenPatch.consoleLogger.send(LoggerManipulationType.INFO, HomeWardOraxenPatch.itemListGetter.getBlackList().toString());
        if (commandSender instanceof Player)
            HomeWardOraxenPatch.customLogger.send(HomeWardOraxenPatch.itemListGetter.getBlackList().toString(), (Player) commandSender);
    }

    @SubCommand("checkhavedamage")
    public void checkHaveDamage(final CommandSender commandSender) {
        Player player = (Player) commandSender;
        var asString = player.getInventory().getItemInMainHand().getType().getMaxDurability();


        HomeWardOraxenPatch.customLogger.send(String.valueOf(asString), (Player) commandSender);
    }

}
