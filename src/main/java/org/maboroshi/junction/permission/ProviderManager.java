package org.maboroshi.junction.permission;

import org.maboroshi.junction.Junction;
import org.maboroshi.junction.config.ConfigManager;
import org.maboroshi.junction.util.Log;

public class ProviderManager {
    public static PermissionProvider initializeProvider(Junction plugin) {
        ConfigManager config = plugin.getConfiguration();
        PermissionProvider provider = null;

        if (!config.getMainConfig().permissions.enabled) {
            Log.info("Permission management disabled.");
            return null;
        }

        String providerType = config.getMainConfig().permissions.provider;

        if (providerType.equalsIgnoreCase("LuckPerms")) {
            provider = LuckPermsProvider.setupProvider();
        } else if (providerType.equalsIgnoreCase("Vault")) {
            if (isVaultUnlocked()) {
                Log.info("Detected VaultUnlocked. Using VaultUnlocked as permission provider.");
                provider = VaultUnlockedProvider.setupProvider();
            } else {
                provider = VaultProvider.setupProvider();
            }
        } else {
            Log.warn("Unknown permission provider in config: " + providerType);
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
