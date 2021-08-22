package functions.weapons;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;

public class Weapon {
    private final String name;
    private final String damage;
    private final String range;
    private final String rateOfFire;
    private final String handling;
    private final String reloadSpeed;
    private final String muzzleVelocity;
    private final String meleeDamage;
    private final String heavyMeleeDamage;
    private final Weapons enumConstant;
    private final String imageUrl;

    public Weapon(String name, String damage, String range, String rateOfFire, String handling, String reloadSpeed, String muzzleVelocity, String meleeDamage, String heavyMeleeDamage, Weapons enumConstant, String imageUrl) {
        this.name = name;
        this.damage = damage;
        this.range = range;
        this.rateOfFire = rateOfFire;
        this.handling = handling;
        this.reloadSpeed = reloadSpeed;
        this.muzzleVelocity = muzzleVelocity;
        this.meleeDamage = meleeDamage;
        this.heavyMeleeDamage = heavyMeleeDamage;
        this.enumConstant = enumConstant;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDamage() {
        return damage;
    }

    public String getRange() {
        return range;
    }

    public String getRateOfFire() {
        return rateOfFire;
    }

    public String getHandling() {
        return handling;
    }

    public String getReloadSpeed() {
        return reloadSpeed;
    }

    public String getMuzzleVelocity() {
        return muzzleVelocity;
    }

    public String getMeleeDamage() {
        return meleeDamage;
    }

    public String getHeavyMeleeDamage() {
        return heavyMeleeDamage;
    }

    public Weapons getEnumConstant() {
        return enumConstant;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MessageEmbed buildEmbed() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(name, null);
        builder.setColor(new Color(0x751010));
        builder.setDescription("Weapon Stats: " + name);

        builder.addField("Damage", damage, true);
        builder.addBlankField(true);
        builder.addField("Range", range, true);

        builder.addField("Rate of Fire", rateOfFire, true);
        builder.addBlankField(true);
        builder.addField("Handling", handling, true);

        builder.addField("Reload Speed", reloadSpeed, true);
        builder.addBlankField(true);
        builder.addField("Muzzle Velocity", muzzleVelocity, true);

        builder.addField("Melee Damage", meleeDamage, true);
        builder.addBlankField(true);
        builder.addField("Heavy Melee Damage", heavyMeleeDamage, true);

        builder.setImage(imageUrl);

        return builder.build();
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", damage='" + damage + '\'' +
                ", range='" + range + '\'' +
                ", rateOfFire='" + rateOfFire + '\'' +
                ", handling='" + handling + '\'' +
                ", reloadSpeed='" + reloadSpeed + '\'' +
                ", muzzleVelocity='" + muzzleVelocity + '\'' +
                ", meleeDamage='" + meleeDamage + '\'' +
                ", heavyMeleeDamage='" + heavyMeleeDamage + '\'' +
                ", enumConstant=" + enumConstant +
                '}';
    }
}
