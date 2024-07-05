package uwubak.uwuspawn;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import uwubak.uwuspawn.Commands.spawnCommand;
import uwubak.uwuspawn.Config.SpawnConfig;
import uwubak.uwuspawn.Events.MoveEvent;
import uwubak.uwuspawn.Utils.ConfigBuilder;

import java.util.HashMap;
import java.util.UUID;

public final class Uwu_Spawn extends JavaPlugin {

    private static Uwu_Spawn plugin;

    public final HashMap<UUID, Integer> cooldown = new HashMap<>();



    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("[uwu-Spawn] Plugin sie wlacza..");

        SpawnConfig.setup();
        SpawnConfig.get().options().copyDefaults();
        SpawnConfig.get().createSection("spawn");
        SpawnConfig.reload();

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("spawn").setExecutor(new spawnCommand(this));
        getServer().getPluginManager().registerEvents(new MoveEvent(this), this);

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[uwu-Spawn] Plugin zostal wylaczony!");
    }

    public static Uwu_Spawn getPlugin() {
        return plugin;
    }
}
