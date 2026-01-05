package org.maboroshi.junction;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import org.maboroshi.junction.command.JunctionCommand;
import org.maboroshi.junction.config.ConfigManager;
import org.maboroshi.junction.handler.MessageHandler;
import org.maboroshi.junction.listener.PlayerJoinListener;
import org.maboroshi.junction.listener.PlayerQuitListener;
import org.maboroshi.junction.permission.PermissionProvider;
import org.maboroshi.junction.permission.ProviderManager;
import org.maboroshi.junction.util.Logger;
import org.maboroshi.junction.util.UpdateChecker;

public final class Junction extends JavaPlugin {
    private static Junction plugin;
    private ConfigManager config;
    private PermissionProvider permissionProvider;
    private Logger log;
    private MessageHandler messageHandler;

    @Override
    public void onEnable() {
        plugin = this;
        this.log = new Logger(this);
        UpdateChecker updateChecker = new UpdateChecker(this);
        updateChecker.checkForUpdates();
        if (!reload()) {
            log.error("Disabling plugin due to critical configuration error.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        @SuppressWarnings("unused")
        Metrics metrics = new Metrics(this, 28238);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        getServer().getPluginManager().registerEvents(updateChecker, this);
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            JunctionCommand junctionCommand = new JunctionCommand(this);
            event.registrar().register(junctionCommand.createCommand("junction"), "Main Junction command");
        });
        log.info("Plugin enabled successfully");
    }

    public boolean reload() {
        try {
            this.config = new ConfigManager(getDataFolder());
            this.config.load();
            this.permissionProvider = ProviderManager.initializeProvider(this);
            return true;
        } catch (Exception e) {
            log.error("Failed to load configuration: " + e.getMessage());
            return false;
        }
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

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public PermissionProvider getPermissionProvider() {
        return permissionProvider;
    }
}
