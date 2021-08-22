package bot.utils;

import bot.commands.weapon.Weapon;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class Utils {
    private static List<Weapon> weapons;

    public static File getFile(String name) {
        try {
            return new File(Objects.requireNonNull(Utils.class.getClassLoader().getResource(name)).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String covertToCode(String s) {
        return "```" + s + "```";
    }

    public static List<Weapon> getWeapons() {
        return weapons;
    }

    public static void setWeapons(List<Weapon> weapons) {
        Utils.weapons = weapons;
    }
}
