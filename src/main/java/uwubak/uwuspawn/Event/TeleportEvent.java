package uwubak.uwuspawn.Event;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import uwubak.uwuspawn.Uwu_Spawn;

public class TeleportEvent implements Listener {

    private final Uwu_Spawn plugin;

    public TeleportEvent(Uwu_Spawn plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        if (!plugin.isTeleporting.containsKey(event.getPlayer())) {
            return;
        }

        if (plugin.isTeleporting.get(event.getPlayer()).getX() != event.getPlayer().getLocation().getX() || plugin.isTeleporting.get(event.getPlayer()).getZ() != event.getPlayer().getLocation().getZ() || plugin.isTeleporting.get(event.getPlayer()).getY() != event.getPlayer().getLocation().getY()) {
            plugin.isTeleporting.remove(event.getPlayer());
            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin.config.cancelTeleport)));
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (plugin.isTeleporting.containsKey(event.getPlayer())) {
            plugin.isTeleporting.remove(event.getPlayer());
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (plugin.isTeleporting.containsKey(event.getEntity())) {
            plugin.isTeleporting.remove(event.getEntity());
        }
    }
}
