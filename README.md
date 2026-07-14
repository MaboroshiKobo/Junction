[![Junction Banner](https://raw.githubusercontent.com/MaboroshiKobo/branding/refs/heads/main/projects/junction/banners/junction_2048.png)](https://docs.maboroshi.org/projects/junction)

<div align="center">
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
    <a href="https://docs.maboroshi.org/projects/junction"><img alt="generic" height="56" src="https://raw.githubusercontent.com/MaboroshiKobo/branding/refs/heads/main/socials/128x/domain_icon_bg.png"></a>
    <a href="https://discord.maboroshi.org"><img alt="discord-singular" height="56" src="https://raw.githubusercontent.com/MaboroshiKobo/branding/refs/heads/main/socials/128x/discord_icon_bg.png"></a>
  </p>
</div>

## Platform-specific group routing and command execution

Junction is a lightweight utility plugin that automates player management for servers that are running Geyser and Floodgate. It automatically sorts Bedrock players into configured permission groups on join and runs platform-specific console commands during join and quit events.

## Features

* Automatically assign Bedrock players to specific permission groups upon joining.
* Execute console commands separately for Java and Bedrock players during join and quit events.

## Prerequisites

Junction is compatible with the following plugins:

* [Floodgate](https://geysermc.org/wiki/floodgate) (Required)
* [LuckPerms](https://luckperms.net/) / [Vault](https://www.spigotmc.org/resources/vault.34315/) / [VaultUnlocked](https://modrinth.com/plugin/vaultunlocked) (At least one is required)
* [PlaceholderAPI](https://placeholderapi.com/) (Optional)

## Documentation & Support

For configurations, commands, and permissions, check out our [wiki](https://docs.maboroshi.org/projects/junction). For bugs, questions, or updates, visit our [Discord server](https://discord.maboroshi.org) or open a [GitHub Issue](https://github.com/MaboroshiKobo/Junction/issues).

## Statistics

This plugin utilizes [bStats](https://bstats.org/plugin/bukkit/Junction/28238) to collect anonymous usage metrics.

![bStats Metrics](https://bstats.org/signatures/bukkit/Junction.svg)

## Building

To build the project from source, ensure you have a Java 25 environment configured.

```bash
./gradlew build
```
