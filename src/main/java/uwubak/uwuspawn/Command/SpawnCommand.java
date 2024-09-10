package uwubak.uwuspawn.Command;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import uwubak.uwuspawn.Uwu_Spawn;

import java.util.Arrays;
import java.util.List;

public class SpawnCommand implements CommandExecutor, TabCompleter {

    private final Uwu_Spawn plugin;

    public SpawnCommand(Uwu_Spawn plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player player = (Player) sender;

        if (player == null) {
            return true;
        }

        if (args.length == 1) {

            if (args[0].equalsIgnoreCase("set")) {

                if (player.hasPermission(plugin.config.permission)) {

                    plugin.config.location = player.getLocation();
                    plugin.config.save();
                    plugin.config.update();

                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.config.setLocation)));
                    return true;
                } else {

                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.config.noPermission)));

                }

            }
        }

        if (!plugin.isTeleporting.containsKey(player)) {
            plugin.isTeleporting.put(player, player.getLocation());
        }

        new BukkitRunnable() {
            int time = plugin.config.time;
            @Override
            public void run() {

                if (!plugin.isTeleporting.containsKey(player)) {
                    cancel();
                    return;
                }

                if (time <= 0) {
                    plugin.isTeleporting.remove(player);
                    player.teleport(plugin.config.location);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.config.teleportSuccess)));

                    cancel();
                    return;
                }

                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.config.teleportMessage.replace("{TIME}", String.valueOf(time)))));

                time--;

            }
        }.runTaskTimer(plugin, 20, 20);


        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {

        if (args.length == 1 && sender.hasPermission(plugin.config.permission)) {
            return Arrays.asList("set");
        }


        return null;
    }
}
