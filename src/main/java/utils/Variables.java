package utils;

import functions.weapons.Weapon;

import java.util.List;

public class Variables {
    private static List<Weapon> weapons;

    public static List<Weapon> getWeapons() {
        return weapons;
    }

    public static void setWeapons(List<Weapon> weapons) {
        Variables.weapons = weapons;
    }
}
