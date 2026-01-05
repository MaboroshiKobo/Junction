**Junction** is a [Floodgate](https://geysermc.org/wiki/floodgate/) addon for Paper servers that automates administrative tasks for Bedrock Edition players. It requires Floodgate to detect player editions and allows you to manage platform differences by triggering specific actions based on how a player connects.

### Features

Junction handles cross-platform management with a focus on these core functions:
* **Automated group assignment** using [LuckPerms](https://luckperms.net/), [Vault](https://www.spigotmc.org/resources/vault.34315/), or VaultUnlocked to put Bedrock players into specific permission groups upon join.
* **Edition-specific console commands** that run separate logic for Java and Bedrock players during join and quit events.
* **Variable support** using internal placeholders like `{player}` and `{uuid}`, with optional PlaceholderAPI integration.
* **Lightweight implementation** designed to run on Paper 1.21+ without adding unnecessary overhead.

### Documentation

For a complete guide on features, commands, permissions, and configuration, please visit our [Wiki](https://docs.maboroshi.org/).

### Support

If you have questions, want to report a bug, or need help, please join our [Discord server](https://discord.maboroshi.org). Alternatively, you may open an issue on [GitHub](https://github.com/MaboroshiKobo/Junction/issues).

### Statistics

This plugin utilizes [bStats](https://bstats.org/plugin/bukkit/Junction/28238) to collect anonymous usage metrics.

![bStats Metrics](https://bstats.org/signatures/bukkit/Junction.svg)

## Building

```bash
./gradlew build
```

The compiled JAR will be in `build/libs/`.
