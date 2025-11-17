package com.muhdfdeen.junction.permission;

import org.bukkit.entity.Player;

public class VaultProvider implements PermissionProvider {
    @Override
    public boolean addPlayerToGroup(Player player, String group) {
        return false;
    }

    @Override
    public boolean isPlayerInGroup(Player player, String group) {
        return false;
    }

    @Override
    public String getName() {
        return "Vault";
    }
}
