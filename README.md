# ModernJobs API

Developer API for the ModernJobs Spigot/Paper plugin.  
Use this to interact with jobs, player data, leaderboards, and GUIs from your own plugin.

---

## Adding the dependency

The API is published to the [Oreo Studios Maven repository](https://maven.oreostudios.fr/releases) and JitPack. Oreo Studios Maven is recommended; the dependency coordinates are `com.github.el211:ModerJobs-API:1.1.0`. Repository credentials are not needed to download the published API.

The API is hosted via [JitPack](https://jitpack.io). You do **not** need the plugin JAR on your classpath — just add the JitPack repository and the dependency below.

### Maven

```xml
<repositories>
    <repository>
        <id>oreostudios-releases</id>
        <url>https://maven.oreostudios.fr/releases</url>
    </repository>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.el211</groupId>
        <artifactId>ModerJobs-API</artifactId>
        <version>1.1.0</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

### Gradle

```groovy
repositories {
    maven { url 'https://maven.oreostudios.fr/releases' }
    maven { url 'https://jitpack.io' }
}

dependencies {
    compileOnly 'com.github.el211:ModerJobs-API:1.1.0'
}
```

> Replace `1.1.0` with the latest release tag, or use `main-SNAPSHOT` to track the latest commit.

---

## Requirements

- Java 17+
- Paper / Spigot 1.21+
- ModernJobs installed on the server (`softdepend` or `depend` on it in your `plugin.yml`)

---

## plugin.yml

Make sure ModernJobs loads before your plugin:

```yaml
softdepend:
  - ModernJobs
```

---

## Getting the API instance

Always retrieve the API through `OJobsProvider` — never instantiate it yourself.

```java
import fr.oreo.modernjobs.api.OJobsApi;
import fr.oreo.modernjobs.api.OJobsProvider;

// Returns Optional.empty() if ModernJobs is not loaded
Optional<OJobsApi> api = OJobsProvider.get();

// Or throw if not available
OJobsApi api = OJobsProvider.getOrThrow();
```

---

## Examples

### Registering a custom perk

Implement `CustomPerkHandler` and override only the Bukkit event callbacks your perk needs. The handler key must be unique.

```java
import fr.oreo.modernjobs.api.CustomPerkHandler;
import fr.oreo.modernjobs.api.OJobsProvider;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;

public final class MyPerk implements CustomPerkHandler {
    @Override public String getKey() { return "my-plugin:example"; }
    @Override public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.DIAMOND_ORE) {
            event.getPlayer().sendMessage("Lucky find!");
        }
    }
}

// In your plugin's onEnable:
OJobsProvider.get().ifPresent(api -> api.registerPerkHandler(new MyPerk()));
```

Handlers also support `onBlockPlace`, `onInteract`, `onMobKill`, `onFish`, `onCraft`, `onFurnaceExtract`, and `onDamage`. Remove one with `unregisterPerkHandler(key)`; inspect active registrations with `getRegisteredPerkHandlers()`.

Callbacks run on the Bukkit event thread. Keep work short and use the scheduler for slow operations.

### Check if a player has a job

```java
OJobsApi api = OJobsProvider.getOrThrow();

if (api.hasJob(player, "miner")) {
    player.sendMessage("You are a Miner!");
}
```

### Join / leave a job

```java
boolean joined = api.joinJob(player, "farmer");
boolean left   = api.leaveJob(player, "farmer");
```

### Give XP

```java
api.giveXp(player, "miner", 50.0);
```

### Set a player's level

```java
api.setLevel(player.getUniqueId(), "miner", 10);
```

### Read player job data

```java
api.getPlayerJobData(player.getUniqueId(), "miner").ifPresent(data -> {
    int level    = data.getLevel();
    double xp    = data.getXp();
    int prestige = data.getPrestige();
});
```

### Prestige

```java
if (api.canPrestige(player, "miner")) {
    api.prestige(player, "miner");
}
```

### Leaderboard

```java
// Per-job leaderboard
List<LeaderboardEntry> top = api.getLeaderboard("miner");

// Global leaderboard (sum of all job levels)
List<LeaderboardEntry> global = api.getGlobalLeaderboard();
```

### Open GUIs

```java
api.openMainMenu(player);
api.openJobMenu(player, "miner");
api.openLeaderboard(player);
```

---

## Full API reference

See [`OJobsApi.java`](src/main/java/fr/oreo/modernjobs/api/OJobsApi.java) for the complete list of available methods.
