package org.maboroshi.junction.permission;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.maboroshi.junction.util.Log;

public class VaultProvider implements PermissionProvider {
    private final Permission permission;

    public VaultProvider(Permission permission) {
        this.permission = permission;
    }

    public static VaultProvider setupProvider() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            Log.warn("Vault plugin not found!");
            return null;
        }

        RegisteredServiceProvider<Permission> provider =
                Bukkit.getServicesManager().getRegistration(Permission.class);

        if (provider == null) {
            Log.warn("Vault permission service not found!");
            return null;
        }

        return new VaultProvider(provider.getProvider());
    }

    @Override
    public boolean addPlayerToGroup(Player player, String group) {
        return permission.playerAddGroup(null, (OfflinePlayer) player, group);
    }

    @Override
    public boolean removePlayerFromGroup(Player player, String group) {
        return permission.playerRemoveGroup(null, (OfflinePlayer) player, group);
    }

    @Override
    public String getName() {
        return "Vault";
    }
}
