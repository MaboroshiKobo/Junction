package org.maboroshi.junction.util;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.maboroshi.junction.Junction;
import org.maboroshi.junction.config.ConfigManager;

public class Logger {
    private final ConfigManager config;

    public Logger(Junction plugin) {
        this.config = plugin.getConfiguration();
    }

    private void log(String colorTag, String message) {
        String prefix;
        if (config != null && config.getMessageConfig() != null) prefix = config.getMessageConfig().messages.prefix;
        else prefix = "<color:#00D4FF><bold>Junction</bold> ➟ </color>";
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize(prefix + colorTag + message));
    }

    public void debug(String message) {
        if (config != null && config.getMainConfig().debug) log("<gray>[DEBUG] </gray>", message);
    }

    public void info(String message) {
        log("", message);
    }

    public void warn(String message) {
        log("<yellow>", message);
    }

    public void error(String message) {
        log("<red>", message);
    }
}
