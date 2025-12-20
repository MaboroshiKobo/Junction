package com.muhdfdeen.junction.util;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;

public class CommandUtils {
    public static void dispatch(Player player, List<String> commands) {
        if (commands == null || commands.isEmpty())
            return;
        boolean papiEnabled = Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");
        for (String command : commands) {
            String parsedCommand = command;
            if (papiEnabled)
                parsedCommand = PlaceholderAPI.setPlaceholders(player, parsedCommand);
            parsedCommand = parsedCommand
                    .replace("{player}", player.getName())
                    .replace("{uuid}", player.getUniqueId().toString());
            if (parsedCommand.startsWith("/"))
                parsedCommand = parsedCommand.substring(1);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), parsedCommand);
        }
    }
}
