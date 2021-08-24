package bot.commands.weapon;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.Color;

public final class WeaponUtils {
    public static MessageEmbed buildWeaponEmbed(Weapon w) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(w.getName(), null);
        builder.setColor(new Color(0x751010));
        builder.setDescription("Weapon Stats: " + w.getName());

        builder.addField("Damage", w.getDamage(), true);
        builder.addField("Price", w.getPrice(), true);
        builder.addField("Ammo", w.getAmmo(), true);

        builder.addField("Range", w.getRange(), true);
        builder.addField("Rate of Fire", w.getRateOfFire(), true);
        builder.addField("Handling", w.getHandling(), true);

        builder.addField("Reload Speed", w.getReloadSpeed(), true);
        builder.addField("Muzzle Velocity", w.getMuzzleVelocity(), true);
        builder.addField("Melee Damage", w.getMeleeDamage(), true);

        builder.addField("Heavy Melee Damage", w.getHeavyMeleeDamage(), true);
        builder.addField("Capacity", w.getCapacity(), true);
        builder.addBlankField(true);

        builder.setImage(w.getImageUrl());

        builder.setFooter("Source: https://huntshowdown.fandom.com/wiki/Weapons", "https://cdn.discordapp.com/avatars/878302687035293716/d6e35d90be85072c0ca25e211778b3ee.png?size=128");

        return builder.build();
    }
}
