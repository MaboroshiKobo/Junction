package org.maboroshi.junction.util;

import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.maboroshi.junction.Junction;
import org.maboroshi.junction.config.settings.MainConfig.CommandEntry;

public class CommandUtils {
    public static void dispatch(Player player, List<CommandEntry> commands, MessageUtils messageUtils) {
        if (commands == null || commands.isEmpty()) return;
        long totalDelay = 0;
        for (CommandEntry entry : commands) {
            Component parsedComponent =
                    messageUtils.parse(player, entry.command, messageUtils.tag("uuid", player.getUniqueId()));
            String finalCommand = LegacyComponentSerializer.legacySection().serialize(parsedComponent);
            if (finalCommand.startsWith("/")) finalCommand = finalCommand.substring(1);
            final String commandToRun = finalCommand;
            final long delayTicks = totalDelay + entry.delay;
            if (Junction.isFolia()) {
                Bukkit.getGlobalRegionScheduler()
                        .runDelayed(
                                Junction.getPlugin(),
                                (task) -> {
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandToRun);
                                    debug(commandToRun, delayTicks);
                                },
                                delayTicks);
            } else {
                Bukkit.getScheduler()
                        .runTaskLater(
                                Junction.getPlugin(),
                                () -> {
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandToRun);
                                    debug(commandToRun, delayTicks);
                                },
                                delayTicks);
            }
            totalDelay += entry.delay;
        }
    }

    private static void debug(String cmd, long delay) {
        Junction.getPlugin().getPluginLogger().debug("Executed command: " + cmd + " (Total delay: " + delay + ")");
    }
}
