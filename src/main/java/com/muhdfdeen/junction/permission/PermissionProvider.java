package com.muhdfdeen.junction.permission;

import org.bukkit.entity.Player;

public interface PermissionProvider {
    boolean addPlayerToGroup(Player player, String group);

    boolean isPlayerInGroup(Player player, String group);

    String getName();
}
