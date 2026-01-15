package org.maboroshi.junction.util;

import java.util.List;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.maboroshi.junction.Junction;

public class CommandUtils {
    public static void dispatch(Player player, List<String> commands) {
        if (commands == null || commands.isEmpty()) return;
        boolean papiEnabled = Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");
        for (String command : commands) {
            String parsedCommand = command;
            if (papiEnabled) parsedCommand = PlaceholderAPI.setPlaceholders(player, parsedCommand);
            parsedCommand = parsedCommand
                    .replace("{player}", player.getName())
                    .replace("{uuid}", player.getUniqueId().toString());
            if (parsedCommand.startsWith("/")) parsedCommand = parsedCommand.substring(1);
            final String finalCommand = parsedCommand;
            if (Junction.isFolia()) {
                Bukkit.getGlobalRegionScheduler().execute(Junction.getPlugin(), () -> {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finalCommand);
                });
            } else {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), parsedCommand);
            }
        }
    }
}
