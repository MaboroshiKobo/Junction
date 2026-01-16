package org.maboroshi.junction.listener;

import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.geysermc.floodgate.api.FloodgateApi;
import org.maboroshi.junction.Junction;
import org.maboroshi.junction.config.ConfigManager;
import org.maboroshi.junction.config.settings.MainConfig.CommandEntry;
import org.maboroshi.junction.util.CommandUtils;
import org.maboroshi.junction.util.Logger;
import org.maboroshi.junction.util.MessageUtils;

public class PlayerQuitListener implements Listener {
    private final Junction plugin;

    public PlayerQuitListener(Junction plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Logger log = plugin.getPluginLogger();
        ConfigManager config = plugin.getConfiguration();
        MessageUtils messageUtils = plugin.getMessageUtils();
        Player player = event.getPlayer();
        boolean isBedrock = FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId());
        log.debug("Player quit event triggered: " + player.getName() + " (Bedrock: " + isBedrock + ")");
        if (config.getMainConfig().commands.enabled) handleCommands(player, isBedrock, config, messageUtils);
    }

    private void handleCommands(Player player, boolean isBedrock, ConfigManager config, MessageUtils messageUtils) {
        List<CommandEntry> commands;
        if (isBedrock) commands = config.getMainConfig().commands.bedrock.quit;
        else commands = config.getMainConfig().commands.java.quit;
        CommandUtils.dispatch(player, commands, messageUtils);
    }
}
