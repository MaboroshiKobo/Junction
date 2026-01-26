package org.maboroshi.junction.permission;

import org.maboroshi.junction.Junction;
import org.maboroshi.junction.config.ConfigManager;
import org.maboroshi.junction.util.Logger;

public class ProviderManager {
    public static PermissionProvider initializeProvider(Junction plugin) {
        ConfigManager config = plugin.getConfiguration();
        Logger log = plugin.getPluginLogger();
        PermissionProvider provider = null;

        if (!config.getMainConfig().permissions.enabled) {
            log.info("Permission management disabled.");
            return null;
        }

        String providerType = config.getMainConfig().permissions.provider;

        if (providerType.equalsIgnoreCase("LuckPerms")) {
            provider = LuckPermsProvider.setupProvider();
        } else if (providerType.equalsIgnoreCase("Vault")) {
            if (isVaultUnlocked()) {
                log.info("Detected VaultUnlocked. Using VaultUnlocked as permission provider.");
                provider = VaultUnlockedProvider.setupProvider();
            } else {
                provider = VaultProvider.setupProvider();
            }
        } else {
            log.warn("Unknown permission provider in config: " + providerType);
            return null;
        }

        return provider;
    }

    private static boolean isVaultUnlocked() {
        try {
            Class.forName("net.milkbowl.vault2.permission.PermissionUnlocked");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
