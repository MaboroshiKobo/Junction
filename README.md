<div align="center">
  <img src="https://raw.githubusercontent.com/MaboroshiKobo/branding/cfb12f9b3f5816bd7eb8656ebd5115bfec7a381f/projects/junction/junction.avif" width="180" alt="Junction Logo">
  <h1>Junction</h1>
  <p>Bridge the gap between Java and Bedrock crossplay. Automate permissions and execute edition-specific commands seamlessly via Geyser and Floodgate.</p>

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
    <a href="https://docs.maboroshi.org"><img alt="generic" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/documentation/generic_vector.svg"></a>
    <a href="https://discord.maboroshi.org"><img alt="discord-singular" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/social/discord-singular_vector.svg"></a>
  </p>
</div>

### Features

* Automatically sort your Bedrock players into specific permission groups as they join the server.
* Run separate console commands depending on the platform. You can trigger completely different logic for Java and Bedrock players during join and quit events.
* Keep your setup clean with built-in variables like `{player}` and `{uuid}`, plus full PlaceholderAPI support if you need to pull in extra data from your other plugins.

### Prerequisites

This plugin is designed and officially tested for **Paper** `26.1`+ using **Java 25**. While it might technically run on slightly older Minecraft or Java versions, those aren't officially supported; so if something breaks, you're on your own!

#### Compatibility

Junction supports integration with the following plugins to enhance functionality:

* [Floodgate](https://geysermc.org/wiki/floodgate) (Required)
* [LuckPerms](https://luckperms.net/) / [Vault](https://www.spigotmc.org/resources/vault.34315/) / [VaultUnlocked](https://modrinth.com/plugin/vaultunlocked) (At least one is required)
* [PlaceholderAPI](https://placeholderapi.com/) (Optional)

### Documentation & Support

For a complete guide on features, commands, and configuration, please visit our [wiki](https://docs.maboroshi.org). If you have questions or need to report a bug, join our [Discord server](https://discord.maboroshi.org) or open an issue on [GitHub](https://github.com/MaboroshiKobo/Junction/issues).

### Statistics

This plugin utilizes [bStats](https://bstats.org/plugin/bukkit/Junction/28238) to collect anonymous usage metrics.

![bStats Metrics](https://bstats.org/signatures/bukkit/Junction.svg)

## Building

If you wish to build the project from source, ensure you have a Java 25 environment configured.

```bash
./gradlew build
```
