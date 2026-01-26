package org.maboroshi.junction.permission;

import net.milkbowl.vault2.helper.subject.Subject;
import net.milkbowl.vault2.permission.PermissionUnlocked;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.maboroshi.junction.Junction;
import org.maboroshi.junction.util.Logger;

public class VaultUnlockedProvider implements PermissionProvider {
    private final PermissionUnlocked permission;

    public VaultUnlockedProvider(PermissionUnlocked permission) {
        this.permission = permission;
    }

    public static VaultUnlockedProvider setupProvider() {
        Logger log = Junction.getPlugin().getPluginLogger();

        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            log.warn("Vault plugin not found!");
            return null;
        }

        RegisteredServiceProvider<PermissionUnlocked> provider =
                Bukkit.getServicesManager().getRegistration(PermissionUnlocked.class);

        if (provider == null) {
            log.warn("Vault registration failed!");
            return null;
        }

        return new VaultUnlockedProvider(provider.getProvider());
    }

    private Subject getSubject(Player player) {
        return Subject.player(player.getUniqueId(), player.getName());
    }

    @Override
    public boolean addPlayerToGroup(Player player, String group) {
        return permission.addGroup(null, getSubject(player), group);
    }

    @Override
    public boolean removePlayerFromGroup(Player player, String group) {
        return permission.removeGroup(null, getSubject(player), group);
    }

    @Override
    public String getName() {
        return "Vault";
    }
}