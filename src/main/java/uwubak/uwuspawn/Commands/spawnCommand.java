package uwubak.uwuspawn.Commands;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import uwubak.uwuspawn.Config.SpawnConfig;
import uwubak.uwuspawn.Utils.ConfigBuilder;
import uwubak.uwuspawn.Uwu_Spawn;

import java.util.HashMap;
import java.util.UUID;

public class spawnCommand implements CommandExecutor {

    private final Uwu_Spawn plugin;

    public spawnCommand(Uwu_Spawn plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

       if (sender instanceof Player) {
           Player player = (Player) sender;

            if (args.length == 0) {

                Location locset = SpawnConfig.get().getLocation("spawn.location");

                if (SpawnConfig.get().getConfigurationSection("spawn") == null) {

                    String spawn_not_set_type = plugin.getConfig().getString("spawn-not-set-type");
                    String spawn_not_set_message = plugin.getConfig().getString("spawn-not-set-message");

                    ConfigBuilder.SendMessage(spawn_not_set_message, spawn_not_set_type, player);
                    return true;

                }

                if (SpawnConfig.get().get("spawn").equals("{}")) {
                    SpawnConfig.get().set("spawn", "");
                }

                Integer time = plugin.getConfig().getInt("cooldown");

                if (plugin.cooldown.containsKey(player.getUniqueId())) {
                    String player_in_teleport_type = plugin.getConfig().getString("teleport-exist-type");
                    String player_in_teleport_message = plugin.getConfig().getString("teleport-exist-message");
                    ConfigBuilder.SendMessage(player_in_teleport_message, player_in_teleport_type, player);
                    return true;
                }

                String teleport_success_message = plugin.getConfig().getString("teleport-success-message");
                String teleport_success_type = plugin.getConfig().getString("teleport-success-type");

                plugin.cooldown.put(player.getUniqueId(), time);

                new BukkitRunnable() {
                    int cooldowns = plugin.cooldown.get(player.getUniqueId());

                    @Override
                    public void run() {

                        if (!plugin.cooldown.containsKey(player.getUniqueId())) {
                            cancel();
                            return;
                        }

                        if (plugin.cooldown.containsKey(player.getUniqueId())) {

                            if (cooldowns <= 0) {
                                player.teleport(locset);
                                ConfigBuilder.SendMessage(teleport_success_message, teleport_success_type, player);

                                String sound_success_active = plugin.getConfig().getString("teleport-success-sound-active");
                                String sound_success = plugin.getConfig().getString("teleport-success-sound");


                                if (sound_success_active.equalsIgnoreCase("true")) {
                                    player.playSound(player.getLocation(), Sound.valueOf(sound_success), 1, 1);
                                }


                                plugin.cooldown.remove(player.getUniqueId());
                                cancel();
                                return;
                            }
                            String teleport_in_message = plugin.getConfig().getString("teleport-in-message");

                            teleport_in_message = teleport_in_message.replace("{TIME}", String.valueOf(cooldowns));
                            String teleport_in_type = plugin.getConfig().getString("teleport-in-type");

                            ConfigBuilder.SendMessage(teleport_in_message, teleport_in_type, player);

                            String sound_active = plugin.getConfig().getString("teleport-sound-active");
                            String sound_success = plugin.getConfig().getString("teleport-sound");

                            if (sound_active.equalsIgnoreCase("true")) {
                                player.playSound(player.getLocation(), Sound.valueOf(sound_success), 1, 1);
                            }

                            cooldowns--;

                        }



                    }
                }.runTaskTimer(plugin,0L, 20L);










            }

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("set")) {
                    if (player.hasPermission("spawn.set")) {

                        Location location = player.getLocation();
                        SpawnConfig.get().set("spawn.location", location);

                        String spawn_set_type = plugin.getConfig().getString("spawn-set-type");
                        String spawn_set_message = plugin.getConfig().getString("spawn-set-message");

                        ConfigBuilder.SendMessage(spawn_set_message, spawn_set_type, player);
                        SpawnConfig.save();
                        SpawnConfig.reload();

                    }
                }
            }

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("delete")) {
                    if (player.hasPermission("spawn.delete")) {

                        if (SpawnConfig.get().getConfigurationSection("spawn") == null) {

                            String spawn_not_set_type = plugin.getConfig().getString("spawn-not-set-type");
                            String spawn_not_set_message = plugin.getConfig().getString("spawn-not-set-message");

                            ConfigBuilder.SendMessage(spawn_not_set_message, spawn_not_set_type, player);
                        } else {
                            SpawnConfig.get().set("spawn.location", null);

                            String spawn_deleted_type = plugin.getConfig().getString("spawn-deleted-type");
                            String spawn_deleted_message = plugin.getConfig().getString("spawn-deleted-message");

                            ConfigBuilder.SendMessage(spawn_deleted_message, spawn_deleted_type, player);
                            SpawnConfig.get().set("spawn", "");
                            SpawnConfig.save();
                            SpawnConfig.reload();
                        }
                    }
                }
            }

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (player.hasPermission("spawn.reload")) {

                        String reload_success_type = plugin.getConfig().getString("reload-success-type");
                        String reload_success_message = plugin.getConfig().getString("reload-success-message");

                        ConfigBuilder.SendMessage(reload_success_message, reload_success_type, player);


                        SpawnConfig.reload();
                        plugin.reloadConfig();

                    }
                }
            }


       }




        return true;
    }
}
