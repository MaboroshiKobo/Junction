package org.maboroshi.junction.config.settings;

import de.exlll.configlib.Comment;
import de.exlll.configlib.ConfigLib;
import de.exlll.configlib.Configuration;
import de.exlll.configlib.YamlConfigurationProperties;
import de.exlll.configlib.YamlConfigurations;
import java.io.File;
import java.nio.file.Path;

public final class MessageConfig {
    public static MessageConfiguration load(File dataFolder) {
        YamlConfigurationProperties properties =
                ConfigLib.BUKKIT_DEFAULT_PROPERTIES.toBuilder().build();
        Path configFile = new File(dataFolder, "messages.yml").toPath();
        return YamlConfigurations.update(configFile, MessageConfiguration.class, properties);
    }

    @Configuration
    public static class MessageSettings {
        @Comment("Prefix for all messages sent by the plugin.")
        public String prefix = "<color:#00D4FF><bold>Junction</bold> ➟ </color>";

        @Comment("Message displayed when the plugin is reloaded.")
        public String reloadSuccess = "Plugin configuration has been reloaded successfully.";

        @Comment("Message displayed when the plugin fails to reload.")
        public String reloadFail = "<red>Failed to reload plugin configuration! Check console for errors.</red>";

        @Comment("Message displayed when a new version of the plugin is available.")
        public String updateAvailable =
                "A new version is available! <gray>(Current: <red>{current_version}</red> | Latest: <green>{latest_version}</green>)</gray>";

        public MessageSettings() {}

        public MessageSettings(String prefix, String reloadSuccess, String reloadFail, String updateAvailable) {
            this.prefix = prefix;
            this.reloadSuccess = reloadSuccess;
            this.reloadFail = reloadFail;
            this.updateAvailable = updateAvailable;
        }
    }

    @Configuration
    public static class MessageConfiguration {
        @Comment("Settings related to messages sent by the plugin.")
        public MessageSettings messages = new MessageSettings();
    }
}
