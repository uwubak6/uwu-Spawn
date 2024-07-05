package uwubak.uwuspawn.Events;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import uwubak.uwuspawn.Utils.ConfigBuilder;
import uwubak.uwuspawn.Uwu_Spawn;

public class MoveEvent implements Listener {

    private final Uwu_Spawn plugin;

    public MoveEvent(Uwu_Spawn plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void MoveEvent(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if (plugin.cooldown.containsKey(player.getUniqueId())) {

            plugin.cooldown.remove(player.getUniqueId());
            String teleport_cancel_type = plugin.getConfig().getString("teleport-cancel-type");
            String teleport_cancel_message = plugin.getConfig().getString("teleport-cancel-message");
            ConfigBuilder.SendMessage(teleport_cancel_message, teleport_cancel_type, player);

            String sound_cancel_active = plugin.getConfig().getString("teleport-cancel-sound-active");
            String sound_cancel = plugin.getConfig().getString("teleport-cancel-sound");

            if (sound_cancel_active.equalsIgnoreCase("true")) {
                player.playSound(player.getLocation(), Sound.valueOf(sound_cancel), 1, 1);
            }

        }
    }
}
