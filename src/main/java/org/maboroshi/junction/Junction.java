package org.maboroshi.junction;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import org.maboroshi.junction.command.JunctionCommand;
import org.maboroshi.junction.config.ConfigManager;
import org.maboroshi.junction.listener.PlayerJoinListener;
import org.maboroshi.junction.listener.PlayerQuitListener;
import org.maboroshi.junction.permission.PermissionProvider;
import org.maboroshi.junction.permission.ProviderManager;
import org.maboroshi.junction.util.Logger;
import org.maboroshi.junction.util.MessageUtils;
import org.maboroshi.junction.util.UpdateChecker;

public final class Junction extends JavaPlugin {
    private static Junction plugin;
    private ConfigManager config;
    private PermissionProvider permissionProvider;
    private Logger log;
    private MessageUtils messageUtils;
    private static boolean isFolia;

    @Override
    public void onEnable() {
        plugin = this;
        isFolia = checkFolia();
        this.log = new Logger(this);
        if (!reload()) {
            log.error("Disabling plugin due to critical configuration error.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        @SuppressWarnings("unused")
        Metrics metrics = new Metrics(this, 28238);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            JunctionCommand junctionCommand = new JunctionCommand(this);
            event.registrar().register(junctionCommand.createCommand("junction"), "Main Junction command");
        });
        new UpdateChecker(this).checkForUpdates();
    }

    public boolean reload() {
        try {
            this.config = new ConfigManager(getDataFolder());
            this.config.load();
            this.messageUtils = new MessageUtils(this.config);
            this.permissionProvider = ProviderManager.initializeProvider(this);
            return true;
        } catch (Exception e) {
            log.error("Failed to load configuration: " + e.getMessage());
            return false;
        }
    }

    private static boolean checkFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isFolia() {
        return isFolia;
    }

    public static Junction getPlugin() {
        return plugin;
    }

    public Logger getPluginLogger() {
        return log;
    }

    public ConfigManager getConfiguration() {
        return config;
    }

    public MessageUtils getMessageUtils() {
        return messageUtils;
    }

    public PermissionProvider getPermissionProvider() {
        return permissionProvider;
    }
}
