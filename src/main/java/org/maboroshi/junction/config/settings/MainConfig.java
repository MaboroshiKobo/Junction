package org.maboroshi.junction.config.settings;

import de.exlll.configlib.Comment;
import de.exlll.configlib.ConfigLib;
import de.exlll.configlib.Configuration;
import de.exlll.configlib.YamlConfigurationProperties;
import de.exlll.configlib.YamlConfigurations;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class MainConfig {

    public static MainConfiguration load(File dataFolder) {
        YamlConfigurationProperties properties =
                ConfigLib.BUKKIT_DEFAULT_PROPERTIES.toBuilder().build();
        Path configFile = new File(dataFolder, "config.yml").toPath();
        return YamlConfigurations.update(configFile, MainConfiguration.class, properties);
    }

    @Configuration
    public static class PermissionSettings {
        @Comment("Should this module be enabled?")
        public boolean enabled = false;

        @Comment({"Which permission provider should be used?", "Available options: LuckPerms, Vault"})
        public String provider = "LuckPerms";

        @Comment("Which permission group should players be assigned to?")
        public String group = "geyser";
    }

    @Configuration
    public static class CommandSettings {
        @Comment("Should this module be enabled?")
        public boolean enabled = false;

        @Comment("Commands specific to Java Edition players.")
        public EventCommands java =
                new EventCommands(List.of("say Java player {player} joined"), List.of("say Java player {player} left"));

        @Comment("Commands specific to Bedrock Edition players.")
        public EventCommands bedrock = new EventCommands(
                List.of("say Bedrock player {player} joined"), List.of("say Bedrock player {player} left"));
    }

    @Configuration
    public static class EventCommands {
        @Comment("List of console commands to execute when a player joins.")
        public List<String> join = new ArrayList<>();

        @Comment("List of console commands to execute when a player quits.")
        public List<String> quit = new ArrayList<>();

        public EventCommands() {}

        public EventCommands(List<String> join, List<String> quit) {
            this.join = new ArrayList<>(join);
            this.quit = new ArrayList<>(quit);
        }
    }

    @Configuration
    public static class MainConfiguration {
        @Comment("Should debug mode be enabled for detailed logs?")
        public boolean debug = false;

        @Comment("This module automatically assigns Bedrock Edition players to a specific group.")
        public PermissionSettings permissions = new PermissionSettings();

        @Comment({
            "This module automatically executes commands based on Minecraft editions.",
            "Available internal placeholders: {player}, {uuid}",
            "PlaceholderAPI support is enabled if installed."
        })
        public CommandSettings commands = new CommandSettings();
    }
}
