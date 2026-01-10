<div align="center">
  <h1>Junction</h1>
  <p>The modern Geyser & Floodgate management solution for Paper servers</p>

  [![Version](https://img.shields.io/github/v/release/MaboroshiKobo/Junction?style=flat-square)](https://github.com/MaboroshiKobo/Junction/releases)
  [![License](https://img.shields.io/github/license/MaboroshiKobo/Junction?style=flat-square)](https://github.com/MaboroshiKobo/Junction/blob/main/LICENSE)
  [![Java](https://img.shields.io/badge/Java-21-blue?style=flat-square&logo=java)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
  [![Minecraft](https://img.shields.io/badge/Minecraft-1.21+-red?style=flat-square)](https://papermc.io)
  [![Discord](https://img.shields.io/badge/Discord-Join%20Server-7289da?style=flat-square&logo=discord)](https://discord.maboroshi.org)
</div>

---

**Junction** is a high-performance **Floodgate addon** for **Paper** servers designed to automate **Bedrock Edition** player management. By detecting whether a player is using **Java or Bedrock**, Junction allows administrators to execute platform-specific commands and automate **LuckPerms** or **Vault** group assignments seamlessly.

### Features

Junction handles cross-platform management with a focus on these core functions:

* It provides automated group assignment using [LuckPerms](https://luckperms.net/), [Vault](https://www.spigotmc.org/resources/vault.34315/), or [VaultUnlocked](https://modrinth.com/plugin/vaultunlocked) to put Bedrock players into specific permission groups upon join.
* You can configure edition-specific console commands that run separate logic for Java and Bedrock players during join and quit events.
* It includes extensive variable support using internal placeholders like `{player}` and `{uuid}`, with optional **PlaceholderAPI** support.
* The lightweight implementation is specifically designed to run on **Paper `1.21`+** without adding unnecessary overhead to your server.

### Prerequisites

To use this plugin, your server must be running **Paper**, **Purpur**, or **Folia** on `1.21` or higher. It requires **Java 21** and the [Floodgate](https://geysermc.org/wiki/floodgate) plugin to properly detect player editions.

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
