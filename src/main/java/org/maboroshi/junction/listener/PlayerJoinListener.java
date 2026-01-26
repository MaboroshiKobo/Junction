package org.maboroshi.junction.listener;

import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.geysermc.floodgate.api.FloodgateApi;
import org.maboroshi.junction.Junction;
import org.maboroshi.junction.config.ConfigManager;
import org.maboroshi.junction.config.settings.MainConfig.CommandEntry;
import org.maboroshi.junction.permission.PermissionProvider;
import org.maboroshi.junction.util.CommandUtils;
import org.maboroshi.junction.util.Logger;
import org.maboroshi.junction.util.MessageUtils;

public class PlayerJoinListener implements Listener {
    private final Junction plugin;

    public PlayerJoinListener(Junction plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Logger log = plugin.getPluginLogger();
        ConfigManager config = plugin.getConfiguration();
        MessageUtils messageUtils = plugin.getMessageUtils();
        Player player = event.getPlayer();
        PermissionProvider permissionProvider = plugin.getPermissionProvider();
        boolean isBedrock = FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId());
        log.debug("Player join event triggered: " + player.getName() + " (Bedrock: " + isBedrock + ")");
        if (permissionProvider != null) {
            String groupName = config.getMainConfig().permissions.group;
            if (isBedrock) {
                permissionProvider.addPlayerToGroup(player, groupName);
                log.debug("Ensured Bedrock player " + player.getName() + " is in group: " + groupName);
            } else {
                permissionProvider.removePlayerFromGroup(player, groupName);
                log.debug("Ensured Java player " + player.getName() + " is not in group: " + groupName);
            }
        }
        if (config.getMainConfig().commands.enabled) handleCommands(player, isBedrock, config, messageUtils);
    }

    private void handleCommands(Player player, boolean isBedrock, ConfigManager config, MessageUtils messageUtils) {
        List<CommandEntry> commands;
        if (isBedrock) commands = config.getMainConfig().commands.bedrock.join;
        else commands = config.getMainConfig().commands.java.join;
        CommandUtils.dispatch(player, commands, messageUtils);
    }
}
