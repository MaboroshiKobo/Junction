package org.maboroshi.junction.permission;

import org.bukkit.entity.Player;

public interface PermissionProvider {
    boolean addPlayerToGroup(Player player, String group);

    boolean removePlayerFromGroup(Player player, String group);

    String getName();
}