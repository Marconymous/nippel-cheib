package bot.utils

import bot.commands.weapon.Weapon
import java.io.File
import java.util.Objects
import java.net.URISyntaxException

object Utils {
    lateinit var weapons: ArrayList<Weapon>
    fun getFileFromSystem(name: String?): File? {
        try {
            return File(Objects.requireNonNull(Utils::class.java.classLoader.getResource(name)).toURI())
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
        return null
    }

    fun convertToCode(s: String?): String? {
        return if (s == null || s == "") null else "```$s```"
    }
}