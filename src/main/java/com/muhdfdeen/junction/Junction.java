package com.muhdfdeen.junction;

import net.kyori.adventure.text.Component;
import net.luckperms.api.LuckPerms;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.muhdfdeen.junction.permission.LuckPermsProvider;
import com.muhdfdeen.junction.permission.PermissionProvider;

public final class Junction extends JavaPlugin {
    private static Junction plugin;
    private PermissionProvider permissionProvider;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        setupPermissionProvider();
        getServer().getPluginManager().registerEvents(new com.muhdfdeen.junction.listener.PlayerJoinListener(this), this);
        getComponentLogger().info(Component.text("Plugin enabled successfully."));
    }

    private void setupPermissionProvider() {
        if (!getConfig().getBoolean("permissions.enabled")) {
            getComponentLogger().info(Component.text("Permission management is disabled."));
            return;
        }

        String providerType = getConfig().getString("permissions.provider", "LuckPerms");
        if (providerType.equalsIgnoreCase("LuckPerms")) {
            if (setupLuckPerms()) {
                getComponentLogger().info(Component.text("LuckPerms permission provider initialized."));
            } else {
                getComponentLogger().warn(Component.text("Failed to initialize LuckPerms permission provider. Is LuckPerms installed?"));
            }
         } else if (providerType.equalsIgnoreCase("Vault")) {
            getComponentLogger().info(Component.text("Vault permission provider is not yet implemented."));
         } else {
            getComponentLogger().warn(Component.text("Unknown permission provider: " + providerType));
         }
    }

    private boolean setupLuckPerms() {
        if (getServer().getPluginManager().getPlugin("LuckPerms") == null) {
            return false;
        }
        RegisteredServiceProvider<LuckPerms> provider = getServer().getServicesManager().getRegistration(LuckPerms.class);
        if (provider == null) {
            return false;
        }
        LuckPerms luckPerms = provider.getProvider();
        this.permissionProvider = new LuckPermsProvider(luckPerms);
        return true;
    }

    public PermissionProvider getPermissionProvider() {
        return permissionProvider;
    }

    public static Junction getPlugin() {
        return plugin;
    }
}
