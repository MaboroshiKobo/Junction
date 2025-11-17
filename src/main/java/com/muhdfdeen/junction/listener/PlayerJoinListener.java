package com.muhdfdeen.junction.listener;

import com.muhdfdeen.junction.Junction;
import com.muhdfdeen.junction.permission.PermissionProvider;

import net.kyori.adventure.text.Component;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.geysermc.floodgate.api.FloodgateApi;

public class PlayerJoinListener implements Listener {
    private final Junction plugin;

    public PlayerJoinListener(Junction plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        boolean debug = plugin.getConfig().getBoolean("debug", false);
        
        if (debug) {
            plugin.getComponentLogger().info(Component.text(
                "Player join event triggered: " + player.getName()
            ));
        }
        
        if (!plugin.getConfig().getBoolean("permissions.enabled")) {
            if (debug) {
                plugin.getComponentLogger().info(Component.text(
                    "Permissions disabled - skipping " + player.getName()
                ));
            }
            return;
        }

        boolean isBedrockPlayer = FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId());
        
        if (debug) {
            plugin.getComponentLogger().info(Component.text(
                player.getName() + " detected as " + (isBedrockPlayer ? "BEDROCK" : "JAVA") + " Edition"
            ));
        }

        if (!isBedrockPlayer) {
            if (debug) {
                plugin.getComponentLogger().info(Component.text(
                    "Skipping Java player: " + player.getName()
                ));
            }
            return;
        }
        
        if (debug) {
            plugin.getComponentLogger().info(Component.text(
                "Processing Bedrock player: " + player.getName()
            ));
        }

        PermissionProvider permissionProvider = plugin.getPermissionProvider();

        if (permissionProvider == null) {
            plugin.getComponentLogger().warn(Component.text("Failed to assign group to " + player.getName() + " as no permission provider was found."));
            return;
        }
        
        if (debug) {
            plugin.getComponentLogger().info(Component.text(
                "Permission provider: " + permissionProvider.getName()
            ));
        }

        String groupName = plugin.getConfig().getString("permissions.group");
        if (groupName == null || groupName.isEmpty()) {
            plugin.getComponentLogger().error(Component.text("Bedrock group name not configured! Please check your configuration file."));
            return;
        }
        
        if (debug) {
            plugin.getComponentLogger().info(Component.text(
                "Attempting to add " + player.getName() + " to group: " + groupName
            ));
        }

        boolean success = permissionProvider.addPlayerToGroup(player, groupName);
        if (success) {
            plugin.getComponentLogger().info(Component.text("Assigned Bedrock player " + player.getName() + " to group '" + groupName + "'."));
        } else {
            plugin.getComponentLogger().warn(Component.text("Failed to assign Bedrock player " + player.getName() + " to group '" + groupName + "'."));
        }
    }
}
