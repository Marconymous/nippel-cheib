package bot.commands.weapon

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import java.awt.Color

object WeaponUtils {
    fun buildWeaponEmbed(w: Weapon): MessageEmbed {
        val builder = EmbedBuilder()
        builder.setTitle(w.name, null)
        builder.setColor(Color(0x751010))
        builder.setDescription("Weapon Stats: " + w.name)
        builder.addField("Damage", w.damage, true)
        builder.addField("Price", w.price, true)
        builder.addField("Ammo", w.ammo, true)
        builder.addField("Range", w.range, true)
        builder.addField("Rate of Fire", w.rateOfFire, true)
        builder.addField("Handling", w.handling, true)
        builder.addField("Reload Speed", w.reloadSpeed, true)
        builder.addField("Muzzle Velocity", w.muzzleVelocity, true)
        builder.addField("Melee Damage", w.meleeDamage, true)
        builder.addField("Heavy Melee Damage", w.heavyMeleeDamage, true)
        builder.addField("Capacity", w.capacity, true)
        builder.addBlankField(true)
        builder.setImage(w.imageUrl)
        builder.setFooter(
            "Source: https://huntshowdown.fandom.com/wiki/Weapons",
            "https://cdn.discordapp.com/avatars/878302687035293716/d6e35d90be85072c0ca25e211778b3ee.png?size=128"
        )
        return builder.build()
    }

    fun generateWeaponName(vararg strings: String): String {
        return java.lang.String.join(" ", *strings)
    }
}