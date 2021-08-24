package bot.utils;

import bot.commands.weapon.Weapon;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class Utils {
    private static List<Weapon> weapons;

    public static File getFileFromSystem(String name) {
        try {
            return new File(Objects.requireNonNull(Utils.class.getClassLoader().getResource(name)).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertToCode(String s) {
        return (s == null || s.equals("")) ? null : "```" + s + "```";
    }

    public static List<Weapon> getWeapons() {
        return weapons;
    }

    public static void setWeapons(List<Weapon> weapons) {
        Utils.weapons = weapons;
    }
}
