package org.maboroshi.junction.util;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.geyser.api.GeyserApi;

public final class BedrockUtils {
    private BedrockUtils() {}

    public static boolean isBedrock(Player player) {
        return isBedrock(player.getUniqueId());
    }

    public static boolean isBedrock(UUID uuid) {
        if (Bukkit.getPluginManager().isPluginEnabled("floodgate")) {
            if (FloodgateHook.isBedrock(uuid)) return true;
        }
        if (Bukkit.getPluginManager().isPluginEnabled("Geyser-Spigot")) {
            if (GeyserHook.isBedrock(uuid)) return true;
        }
        return uuid.getMostSignificantBits() == 0;
    }

    private static final class FloodgateHook {
        private static boolean isBedrock(UUID uuid) {
            return FloodgateApi.getInstance().isFloodgatePlayer(uuid);
        }
    }

    private static final class GeyserHook {
        private static boolean isBedrock(UUID uuid) {
            return GeyserApi.api().isBedrockPlayer(uuid);
        }
    }
}
