package org.maboroshi.junction.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;
import org.maboroshi.junction.Junction;
import org.maboroshi.junction.config.ConfigManager;
import org.maboroshi.junction.handler.MessageHandler;
import org.maboroshi.junction.util.Logger;

public class JunctionCommand {
    private final Junction plugin;
    private final Logger log;
    private final MessageHandler messageHandler;

    public JunctionCommand(Junction plugin) {
        this.plugin = plugin;
        this.log = plugin.getPluginLogger();
        this.messageHandler = plugin.getMessageHandler();
    }

    public LiteralCommandNode<CommandSourceStack> createCommand(final String commandName) {
        ConfigManager config = plugin.getConfiguration();
        return Commands.literal(commandName)
                .executes(ctx -> {
                    CommandSender sender = ctx.getSource().getSender();

                    messageHandler.send(
                            sender,
                            "<prefix>Plugin version: <green><version></green>",
                            messageHandler.tag("version", plugin.getPluginMeta().getVersion()));

                    messageHandler.send(
                            sender,
                            "<green>🛈</green> <gray>Type <white>/junction reload</white> to reload the configuration.</gray>");
                    return Command.SINGLE_SUCCESS;
                })
                .then(Commands.literal("reload")
                        .requires(sender -> sender.getSender().hasPermission("junction.reload"))
                        .executes(ctx -> {
                            CommandSender sender = ctx.getSource().getSender();
                            if (plugin.reload()) {
                                log.info("Configuration reloaded by " + sender.getName());
                                messageHandler.send(
                                        sender, "<prefix>" + config.getMessageConfig().messages.reloadSuccess);
                            } else {
                                log.warn("Failed to reload configuration by " + sender.getName());
                                messageHandler.send(sender, "<prefix>" + config.getMessageConfig().messages.reloadFail);
                            }
                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }
}
