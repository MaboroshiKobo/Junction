package org.maboroshi.junction.permission;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.maboroshi.junction.Junction;
import org.maboroshi.junction.util.Logger;

public class LuckPermsProvider implements PermissionProvider {
    private final LuckPerms luckPerms;

    private LuckPermsProvider(LuckPerms luckPerms) {
        this.luckPerms = luckPerms;
    }

    public static LuckPermsProvider setupProvider() {
        Logger log = Junction.getPlugin().getPluginLogger();

        if (Bukkit.getPluginManager().getPlugin("LuckPerms") == null) {
            log.warn("LuckPerms plugin not found!");
            return null;
        }

        RegisteredServiceProvider<LuckPerms> provider =
                Bukkit.getServicesManager().getRegistration(LuckPerms.class);

        if (provider == null) {
            log.warn("LuckPerms registration failed!");
            return null;
        }

        log.info("LuckPerms provider initialized.");
        return new LuckPermsProvider(provider.getProvider());
    }

    @Override
    public boolean addPlayerToGroup(Player player, String group) {
        this.luckPerms.getUserManager().modifyUser(player.getUniqueId(), user -> {
            Node node = Node.builder("group." + group).build();
            user.data().add(node);
        });
        return true;
    }

    @Override
    public boolean removePlayerFromGroup(Player player, String group) {
        this.luckPerms.getUserManager().modifyUser(player.getUniqueId(), user -> {
            Node node = Node.builder("group." + group).build();
            user.data().remove(node);
        });
        return true;
    }

    @Override
    public String getName() {
        return "LuckPerms";
    }
}
