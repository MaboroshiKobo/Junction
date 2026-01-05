package org.maboroshi.junction.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;
import org.maboroshi.junction.Junction;
import org.maboroshi.junction.config.ConfigManager;
import org.maboroshi.junction.util.Logger;

public class JunctionCommand {
    private final Junction plugin;
    private final Logger log;

    public JunctionCommand(Junction plugin) {
        this.plugin = plugin;
        this.log = plugin.getPluginLogger();
    }

    public LiteralCommandNode<CommandSourceStack> createCommand(final String commandName) {
        ConfigManager config = plugin.getConfiguration();
        return Commands.literal(commandName)
                .executes(ctx -> {
                    CommandSender sender = ctx.getSource().getSender();
                    sender.sendRichMessage(config.getMessageConfig().messages.prefix() + "Plugin version: "
                            + plugin.getPluginMeta().getVersion());
                    sender.sendRichMessage(
                            "<green>🛈</green> <gray>Type <white>/junction reload</white> to reload the configuration.</gray>");
                    return Command.SINGLE_SUCCESS;
                })
                .then(Commands.literal("reload")
                        .requires(sender -> sender.getSender().hasPermission("junction.reload"))
                        .executes(ctx -> {
                            CommandSender sender = ctx.getSource().getSender();
                            if (plugin.reload()) {
                                log.info("Configuration reloaded by " + sender.getName());
                                sender.sendRichMessage(config.getMessageConfig()
                                                .messages
                                                .prefix()
                                        + config.getMessageConfig().messages.reloadSuccess());
                            } else {
                                log.warn("Failed to reload configuration by " + sender.getName());
                                sender.sendRichMessage(config.getMessageConfig()
                                                .messages
                                                .prefix()
                                        + config.getMessageConfig().messages.reloadFail());
                            }
                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }
}
