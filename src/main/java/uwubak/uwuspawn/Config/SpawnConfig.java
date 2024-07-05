package uwubak.uwuspawn.Config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import uwubak.uwuspawn.Uwu_Spawn;

import java.io.File;
import java.io.IOException;

public class SpawnConfig {

    private final Uwu_Spawn plugin;

    private static File file;

    private static FileConfiguration config;

    public SpawnConfig(Uwu_Spawn plugin) {
        this.plugin = plugin;
    }



    public static void setup() {

        file = new File(Bukkit.getPluginManager().getPlugin("uwu-Spawn").getDataFolder(), "spawn.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();

            }catch (IOException exc) {
                ///
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }


    public static FileConfiguration get() {
        return config;
    }

    public static void save() {

    try {
        config.save(file);
    }catch (IOException exc) {
        ///
    }

    }

    public static void reload() {

        config = YamlConfiguration.loadConfiguration(file);


    }
}
