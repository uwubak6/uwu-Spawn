package uwubak.uwuspawn.Utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import uwubak.uwuspawn.Uwu_Spawn;

public class ConfigBuilder {

    private static Uwu_Spawn plugin;

    public ConfigBuilder(Uwu_Spawn plugin) {
        this.plugin = plugin;
    }


    public static String SendMessage(String message, String type, Player player) {


        if (type.equalsIgnoreCase("CHAT")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }

        if (type.equalsIgnoreCase("TITLE")) {
            player.sendTitle(ChatColor.translateAlternateColorCodes('&', message), "", 20, 20, 20);
        }

        if (type.equalsIgnoreCase("SUBTITLE")) {
            player.sendTitle("", ChatColor.translateAlternateColorCodes('&', message), 20, 20, 20);
        }

        if (type.equalsIgnoreCase("SUBTITLE_ACTIONBAR")) {
            player.sendTitle("", ChatColor.translateAlternateColorCodes('&', message), 20, 20, 20);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
        }

        if (type.equalsIgnoreCase("TITLE_SUBTITLE")) {
            String[] split;

            split = message.split(linesep());
            if (split.length == 0) {
                throw new RuntimeException("Notice with TITLE_SUBTITLE need have " + linesep() + "to include title with subtitle.");
            }

            String title = ChatColor.translateAlternateColorCodes('&', split[0]);
            String subtitle = ChatColor.translateAlternateColorCodes('&', split[1]);

            player.sendTitle(title, subtitle, 20, 20, 20);
        }

        if (type.equalsIgnoreCase("ACTION_BAR")) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
        }


        return null;
    }

    public static String linesep() {
        return "%NEWLINE%";
    }

}
