package uwubak.uwuspawn;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import uwubak.uwuspawn.Command.SpawnCommand;
import uwubak.uwuspawn.Config.MainConfig;
import uwubak.uwuspawn.Event.TeleportEvent;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Uwu_Spawn extends JavaPlugin {

    private static Uwu_Spawn plugin;

    public MainConfig config;

    public Map<Player, Location> isTeleporting = new HashMap<>();

    @Override
    public void onEnable() {

        config = ConfigManager.create(MainConfig.class, cfg -> {
            cfg.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
            cfg.withBindFile(new File(getDataFolder(), "config.yml"));
            cfg.saveDefaults();
            cfg.load(true);
        });

        getCommand("spawn").setExecutor(new SpawnCommand(this));

        Arrays.asList(
                new TeleportEvent(this)
        ).forEach(each -> {
            getServer().getPluginManager().registerEvents(each, this);
        });


    }

    @Override
    public void onDisable() {

    }

    public static Uwu_Spawn getPlugin() {
        return plugin;
    }
}
