package org.maboroshi.junction.listener;

import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.geysermc.floodgate.api.FloodgateApi;
import org.maboroshi.junction.Junction;
import org.maboroshi.junction.config.ConfigManager;
import org.maboroshi.junction.util.CommandUtils;
import org.maboroshi.junction.util.Logger;

public class PlayerQuitListener implements Listener {
    private final Junction plugin;

    public PlayerQuitListener(Junction plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        ConfigManager config = plugin.getConfiguration();
        Logger log = plugin.getPluginLogger();

        boolean isBedrock = FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId());

        log.debug("Player quit event triggered: " + player.getName() + " (Bedrock: " + isBedrock + ")");

        if (config.getMainConfig().commands.enabled) handleCommands(player, isBedrock, config, log);
    }

    private void handleCommands(Player player, boolean isBedrock, ConfigManager config, Logger log) {
        List<String> commands;

        if (isBedrock) commands = config.getMainConfig().commands.bedrock.quit;
        else commands = config.getMainConfig().commands.java.quit;

        CommandUtils.dispatch(player, commands);
    }
}
