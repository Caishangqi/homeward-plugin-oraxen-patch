package com.caizii.homewardoraxenpatch;

import com.caizi.mf.base.CommandManager;
import com.caizi.utils.logs.ConsoleLogger;
import com.caizi.utils.logs.CustomLogger;
import com.caizi.utils.logs.LoggerManipulationType;
import com.caizii.homewardoraxenpatch.command.MainCommand;
import com.caizii.homewardoraxenpatch.event.durability.Minecraft;
import com.caizii.homewardoraxenpatch.event.durability.PlayerItemDamageListener;
import com.caizii.homewardoraxenpatch.util.configgetter.ItemListGetter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;


public final class HomeWardOraxenPatch extends JavaPlugin {

    private static HomeWardOraxenPatch plugin;
    public static ConsoleLogger consoleLogger;
    public static CustomLogger customLogger;
    public static CommandManager commandManager;

    public static ItemListGetter itemListGetter;

    public static FileConfiguration config;

    private static String pluginPrefix = "&5HOraxenPatch &f| ";

    @Override
    public void onEnable() {
        // Plugin startup logic
        initializedComponents();
        registerCommands();
        loadConfigurations();
        registerEvent();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        consoleLogger.send(LoggerManipulationType.UNLOAD, "关闭 " + this.getName() + " 中...");
    }

    private void initializedComponents() {
        plugin = this;
        consoleLogger = new ConsoleLogger(pluginPrefix);
        customLogger = new CustomLogger(pluginPrefix);
        itemListGetter = new ItemListGetter();
        consoleLogger.send(LoggerManipulationType.LOAD, "正在初始化 " + this.getName() + " 的必要组件");
    }

    private void registerCommands() {
        consoleLogger.send(LoggerManipulationType.LOAD, "正在注册 " + this.getName() + " 的指令");
        commandManager = new CommandManager(this);
        commandManager.getCompletionHandler().register("#reloadType", input -> {
            List<String> reloadType = new ArrayList<>();
            reloadType.add("durability");
            return reloadType;
        });
        commandManager.register(new MainCommand());
    }

    private void loadConfigurations() {
        //注册默认Config,没有的话创建一个
        saveDefaultConfig();
        config = getConfig();

        consoleLogger.send(LoggerManipulationType.LOAD, "正在加载 " + this.getName() + " 的配置文件");
        this.saveResource("durability-patch/item-blacklist.yml", true);
        itemListGetter.init();

    }

    private void registerEvent() {
        consoleLogger.send(LoggerManipulationType.LOAD, "正在注册 " + this.getName() + " 的事件");
        Bukkit.getPluginManager().registerEvents(new Minecraft(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerItemDamageListener(), this);
    }

    public static HomeWardOraxenPatch getInstance() {
        if (plugin != null) return plugin;
        else throw new RuntimeException("plugin has not been initialized!");

    }


}
