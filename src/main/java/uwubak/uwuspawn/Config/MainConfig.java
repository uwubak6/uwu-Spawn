package uwubak.uwuspawn.Config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Header;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@Header({"#############################"})
@Header({"#                           #"})
@Header({"#         uwu-Spawn         #"})
@Header({"#         ver: 1.0          #"})
@Header({"#                           #"})
@Header({"#############################"})

public class MainConfig extends OkaeriConfig {

    @Comment("Ile czasu ma trwać teleportacja?")
    public int time = 5;

    @Comment("Lokalizacja w która gracz ma byc przeteleportowany")
    public Location location = new Location(Bukkit.getWorld("world"), 0, 0, 0);

    @Comment("Permisja do ustawiania spawnu")
    public String permission = "test.admin";

    @Comment("Wiadomosc gdy gracz nie posiada permisji")
    public String noPermission = "&cNie posiadasz wystarczajacych permisji";

    @Comment("Wiadomosc po ustawieniu nowej lokalizacji")
    public String setLocation = "&aPomyslnie ustawiono lokalizacje spawna";


    @Comment("Wiadomość podczas teleportacji")
    public String teleportMessage = "&7Zostaniesz przeteleportowany za &f{TIME}";

    @Comment("Wiadomosc gdy teleportacja jest anulowana")
    public String cancelTeleport = "&cTeleportacja anulowana!";

    @Comment("Wiadomosc po teleportacji")
    public String teleportSuccess = "&aZostales pomyslnie przeteleportowany!";

}
