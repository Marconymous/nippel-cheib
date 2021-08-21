package functions.weapons;

public enum Weapons {
    // 3 Slot Weapons
    BOMB_LANCE(ParentWeapon.BOMB_LANCE, ""),
    CALDWELL_RIVAL(ParentWeapon.CALDWELL_RIVAL, ""),
    CROSSBOW(ParentWeapon.CROSSBOW, ""),
    CROWN_N_KING(ParentWeapon.CROWN_N_KING, ""),
    LEBEL(ParentWeapon.LEBEL, ""),
    LEBEL_MARKSMAN(generateWeaponName(ParentWeapon.LEBEL, Version.MARKSMAN), ""),
    LEBEL_TALON(generateWeaponName(ParentWeapon.LEBEL, Version.TALON), ""),
    MARTINI_HENRY(ParentWeapon.MARTINI_HENRY, ""),
    MARTINI_HENRY_DEADEYE(generateWeaponName(ParentWeapon.MARTINI_HENRY, Version.DEADEYE), ""),
    MARTINI_HENRY_MARKSMAN(generateWeaponName(ParentWeapon.MARTINI_HENRY, Version.MARKSMAN), ""),
    MARTINI_HENRY_RIPOSTE(generateWeaponName(ParentWeapon.MARTINI_HENRY, Version.RIPOSTE), ""),
    MOSIN_NAGANT(ParentWeapon.MOSIN_NAGANT, ""),
    MOSIN_NAGANT_AVTOMAT(generateWeaponName(ParentWeapon.MOSIN_NAGANT, Version.AVTOMAT), ""),
    MOSIN_NAGANT_BAYONET(generateWeaponName(ParentWeapon.MOSIN_NAGANT, Version.BAYONET), ""),
    MOSIN_NAGANT_SNIPER(generateWeaponName(ParentWeapon.MOSIN_NAGANT, Version.SNIPER), ""),
    NAGANT_OFFICER_CARBINE(generateWeaponName(ParentWeapon.NAGANT, Version.OFFICER, Version.CARBINE), ""),
    NAGANT_OFFICER_CARBINE_DEADEYE(generateWeaponName(ParentWeapon.NAGANT, Version.OFFICER, Version.CARBINE, Version.DEADEYE), ""),
    NITRO_EXPRESS_RIFLE(ParentWeapon.NIRTO_EXPRESS, ""),
    ROMERO(ParentWeapon.ROMERO, ""),
    ROMERO_TALON(generateWeaponName(ParentWeapon.ROMERO, Version.TALON), ""),
    SPARKS_LRR(ParentWeapon.SPARKS_LRR, ""),
    SPARKS_LRR_SILENCER(generateWeaponName(ParentWeapon.SPARKS_LRR, Version.SILENCER), "Silenced Sparks"),
    SPARKS_LRR_SNIPER(generateWeaponName(ParentWeapon.SPARKS_LRR, Version.SNIPER), ""),
    SPECTER(ParentWeapon.SPECTER, ""),
    SPECTER_BAYONET(generateWeaponName(ParentWeapon.SPECTER, Version.BAYONET), ""),
    SPRINGFIELD(ParentWeapon.SPRINGFIELD, ""),
    SPRINGFIELD_MARKSMAN(generateWeaponName(ParentWeapon.SPRINGFIELD, Version.MARKSMAN), ""),
    VETTERLI_KARABINER(ParentWeapon.VETTERLI, ""),
    VETTERLI_KARABINER_BAYONET(generateWeaponName(ParentWeapon.VETTERLI, Version.BAYONET), ""),
    VETTERLI_KARABINER_DEADEYE(generateWeaponName(ParentWeapon.VETTERLI, Version.DEADEYE), ""),
    WINFIELD_TERMINUS(ParentWeapon.WINFIELD_TERMINUS, ""),
    WINFIELD(ParentWeapon.WINFIELD, ""),
    WINFIELD_APERTURE(generateWeaponName(ParentWeapon.WINFIELD, Version.APERTURE), ""),
    WINFIELD_MUSKET_BAYONET(generateWeaponName(ParentWeapon.WINFIELD, Version.MUSKET, Version.BAYONET), ""),
    WINFIELD_SWIFT(generateWeaponName(ParentWeapon.WINFIELD, Version.SWIFT), ""),
    WINFIELD_TALON(generateWeaponName(ParentWeapon.WINFIELD, Version.TALON), ""),
    WINFIELD_C(ParentWeapon.WINFIELD_C, ""),
    WINFIELD_C_MARKSMAN(generateWeaponName(ParentWeapon.WINFIELD_C, Version.MARKSMAN), ""),
    WINFIELD_C_SILENCER(generateWeaponName(ParentWeapon.WINFIELD_C, Version.SILENCER), "Silenced Winfield", "Silenced Winnie"),
    WINFIELD_CENTENNIAL(ParentWeapon.WINFIELD_CENTENNIAL, ""),
    WINFIELD_CENTENNIAL_SNIPER(generateWeaponName(ParentWeapon.WINFIELD_CENTENNIAL, Version.SNIPER), ""),

    // 2 Slot Weapons
    BORNHEIM_MATCH(generateWeaponName(ParentWeapon.BORNHEIM, Version.MATCH), ""),
    CALDWELL_RIVAL_HANDCANNON(generateWeaponName(ParentWeapon.CALDWELL_RIVAL, Version.HANDCANNON), ""),
    COMBAT_AXE(ParentWeapon.AXE),
    DOLCH_PRECISION(generateWeaponName(ParentWeapon.DOLCH, Version.PRECISION), ""),
    MOSIN_NAGANT_OBREZ(generateWeaponName(ParentWeapon.MOSIN_NAGANT, Version.OBREZ)),
    MOSIN_NAGANT_OBREZ_DRUM(generateWeaponName(ParentWeapon.MOSIN_NAGANT, Version.OBREZ, Version.DRUM)),
    MOSiN_NAGANT_OBREZ_MACE(generateWeaponName(ParentWeapon.MOSIN_NAGANT, Version.OBREZ, Version.MACE)),
    NAGANT_DEADEYE(generateWeaponName(ParentWeapon.NAGANT, Version.DEADEYE)),
    NAGANT_PRECISION(generateWeaponName(ParentWeapon.NAGANT, Version.PRECISION)),
    ROMERO_HANDCANNON(generateWeaponName(ParentWeapon.ROMERO, Version.HANDCANNON)),
    ROMERO_HATCHET(generateWeaponName(ParentWeapon.ROMERO, Version.HATCHET)),
    SPECTER_COMPACT(generateWeaponName(ParentWeapon.SPECTER, Version.COMPACT)),
    SPRINGFIELD_COMPACT(generateWeaponName(ParentWeapon.SPRINGFIELD, Version.COMPACT)),
    SPRINGFIELD_COMPACT_DEADEYE(generateWeaponName(ParentWeapon.SPRINGFIELD, Version.COMPACT, Version.DEADEYE)),
    SPRINGFIELD_COMPACT_STRIKER(generateWeaponName(ParentWeapon.SPRINGFIELD, Version.COMPACT, Version.STRIKER)),
    WINFIELD_TERMINUS_HANDCANNON(generateWeaponName(ParentWeapon.WINFIELD_TERMINUS, Version.HANDCANNON)),
    WINFIELD_C_VANDAL(generateWeaponName(ParentWeapon.WINFIELD_C, Version.VANDAL)),
    WINFIELD_C_VANDAL_STRIKER(generateWeaponName(ParentWeapon.WINFIELD_C, Version.VANDAL, Version.STRIKER)),
    WINFIELD_C_VANDAL_DEADEYE(generateWeaponName(ParentWeapon.WINFIELD_C, Version.VANDAL, Version.DEADEYE)),

    // 1 Slot Weapons
    BORNHEIM(ParentWeapon.BORNHEIM),
    BORNHEIM_EXTENDED(generateWeaponName(ParentWeapon.BORNHEIM, Version.EXTENDED)),
    CALDWELL_CONVERION_CHAIN_PISTOL(generateWeaponName(ParentWeapon.CALDWELL_CONVERSION, Version.CHAIN_PISTOL)),
    CALDWELL_CONVERION_PISTOL(generateWeaponName(ParentWeapon.CALDWELL_CONVERSION, Version.PISTOL)),
    CALDWELL_CONVERSION_UPPERCUT(generateWeaponName(ParentWeapon.CALDWELL_CONVERSION, Version.UPPERCUT)),
    CALDWELL_PAX(ParentWeapon.CALDWELL_PAX),
    CALDWELL_PAX_CLAW(generateWeaponName(ParentWeapon.CALDWELL_PAX, Version.CLAW)),
    CAVALRY_SABER(ParentWeapon.CAVALRY_SABER),
    DOLCH(ParentWeapon.DOLCH),
    HAND_CROSSBOW(generateWeaponName(Version.HAND, ParentWeapon.CROSSBOW)),
    LEMAT(ParentWeapon.LEMAT),
    MACHETE(ParentWeapon.MACHETE),
    NAGANT(ParentWeapon.NAGANT),
    NAGANT_OFFICER(generateWeaponName(ParentWeapon.NAGANT, Version.OFFICER)),
    NAGANT_OFFICER_BRAWLER(generateWeaponName(ParentWeapon.NAGANT, Version.OFFICER, Version.BRAWLER)),
    NAGANT_SILENCER(generateWeaponName(ParentWeapon.NAGANT, Version.SILENCER), "Silenced Nagant");

    private final String fullName;
    private final String[] aliases;


    Weapons(String fullName, String ... aliases) {
        this.aliases = aliases;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String[] getAliases() {
        return aliases;
    }

    private static String generateWeaponName(String ... strings) {
        return String.join(" ", strings);
    }
}
