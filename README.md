<div align="center">
  <img src="https://raw.githubusercontent.com/MaboroshiKobo/branding/cfb12f9b3f5816bd7eb8656ebd5115bfec7a381f/projects/junction/junction.avif" width="180" alt="Junction Logo">
  <h1>Junction</h1>
  <p>The modern Geyser & Floodgate management solution for Paper servers</p>

  <p>
    <img alt="paper" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/paper_vector.svg">
    <img alt="purpur" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/purpur_vector.svg">
    <img alt="spigot" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/unsupported/spigot_vector.svg">
  </p>

  <p>
    <a href="https://github.com/MaboroshiKobo/Junction"><img alt="github" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/github_vector.svg"></a>
    <a href="https://hangar.papermc.io/Maboroshi/Junction"><img alt="hangar" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/hangar_vector.svg"></a>
    <a href="https://modrinth.com/plugin/junction"><img alt="modrinth" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg"></a>
  </p>

  <p>
    <a href="https://docs.maboroshi.org/"><img alt="generic" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/documentation/generic_vector.svg"></a>
    <a href="https://discord.maboroshi.org"><img alt="discord-singular" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/social/discord-singular_vector.svg"></a>
  </p>
</div>

**Junction** is a high-performance **Floodgate addon** for **Paper** servers designed to automate **Bedrock Edition** player management. By detecting whether a player is using **Java or Bedrock**, Junction allows administrators to execute platform-specific commands and automate **LuckPerms** or **Vault** group assignments seamlessly.

### Features

Junction handles cross-platform management with a focus on these core functions:

* It provides automated group assignment using [LuckPerms](https://luckperms.net/), [Vault](https://www.spigotmc.org/resources/vault.34315/), or [VaultUnlocked](https://modrinth.com/plugin/vaultunlocked) to put Bedrock players into specific permission groups upon join.
* You can configure edition-specific console commands that run separate logic for Java and Bedrock players during join and quit events with configurable delay per command.
* It includes extensive variable support using internal placeholders like `<player>` and `<uuid>`, with optional **PlaceholderAPI** support.

### Prerequisites

To use this plugin, your server must be running **Paper** or **Folia** on `1.21` or higher. It also requires the [Floodgate](https://geysermc.org/wiki/floodgate) plugin to properly detect player editions.

### Documentation & Support

For a complete guide on features, commands, and configuration, please visit our [Wiki](https://docs.maboroshi.org/). If you have questions or need to report a bug, join our [Discord server](https://discord.maboroshi.org) or open an issue on [GitHub](https://github.com/MaboroshiKobo/Junction/issues).

### Statistics

This plugin utilizes [bStats](https://bstats.org/plugin/bukkit/Junction/28238) to collect anonymous usage metrics.

![bStats Metrics](https://bstats.org/signatures/bukkit/Junction.svg)

## Building

If you wish to build the project from source, ensure you have a Java 21 environment configured.

```bash
./gradlew build
```
