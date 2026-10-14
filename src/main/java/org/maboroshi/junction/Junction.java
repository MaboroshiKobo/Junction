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
import org.maboroshi.junction.util.Log;
import org.maboroshi.junction.util.MessageUtils;

public final class Junction extends JavaPlugin {
    private static Junction plugin;
    private ConfigManager config;
    private PermissionProvider permissionProvider;
    private MessageUtils messageUtils;

    @Override
    public void onEnable() {
        plugin = this;
        this.config = new ConfigManager(getDataFolder());
        Log.init(
                getComponentLogger(),
                () -> config != null && config.getMainConfig() != null && config.getMainConfig().debug);
        if (!reload()) {
            Log.error("Disabling plugin due to critical configuration error.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        boolean hasFloodgate = getServer().getPluginManager().isPluginEnabled("floodgate");
        boolean hasGeyser = getServer().getPluginManager().isPluginEnabled("Geyser-Spigot");
        if (hasFloodgate) {
            Log.info("Hooked into Floodgate for Bedrock player detection.");
        } else if (hasGeyser) {
            Log.info("Hooked into Geyser-Spigot for Bedrock player detection.");
        } else {
            Log.info("Floodgate/Geyser-Spigot not found. Falling back to UUID-based detection.");
        }
        @SuppressWarnings("unused")
        Metrics metrics = new Metrics(this, 28238);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            JunctionCommand junctionCommand = new JunctionCommand(this);
            event.registrar().register(junctionCommand.createCommand("junction"), "Main Junction command");
        });
    }

    public boolean reload() {
        try {
            this.config.load();
            this.messageUtils = new MessageUtils(this.config);
            this.permissionProvider = ProviderManager.initializeProvider(this);
            return true;
        } catch (Exception e) {
            Log.error("Failed to load configuration: " + e.getMessage());
            return false;
        }
    }

    public static Junction getPlugin() {
        return plugin;
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
