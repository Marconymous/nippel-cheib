package bot.commands.weapon

import bot.Bot
import bot.commands.Command
import bot.utils.EventUtils
import bot.utils.StringCompare
import bot.utils.Tuple
import bot.utils.Utils
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.IOException

class WeaponCommand(bot: Bot) :
    Command(bot, false, "weapon", "Displays information about a Weapon from Hunt: Showdown!", "!") {
    override fun handle(event: MessageReceivedEvent) {
        val cmd = EventUtils.spitRawContent(event)[0]
        val weapon = EventUtils.rawContent(event).replace("$cmd ", "")
        println(weapon)
        var best = Tuple<Weapons?, Float>(null, 0.toFloat())
        for (w in Weapons.values()) {
            var `val` = StringCompare.levenshtein(weapon, w.fullName)
            for (s in w.aliases) {
                `val` = Math.max(StringCompare.levenshtein(weapon, s), `val`)
            }
            if (`val` > best.y) best = Tuple(w, `val`)
            if (`val` == 1f) break
        }
        var finalWeapon: Weapon? = null
        for (w in Utils.weapons) {
            if (w.enumConstant == best.x) {
                finalWeapon = w
                break
            }
        }

        event.channel.sendMessage(WeaponUtils.buildWeaponEmbed(finalWeapon!!)).queue()
    }

    @Throws(IllegalAccessException::class)
    private fun checkWeapons() {
        for (w in Utils.weapons) {
            for (f in Weapon::class.java.declaredFields) {
                f.isAccessible = true
                if (f[w] == null) {
                    System.err.println(f.name + " is null on -> " + w.name)
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun fetchWeaponDetails() {
        val weaponsPage = Jsoup.connect("https://huntshowdown.fandom.com/wiki/Weapons").get()
        val tables = weaponsPage.body().select("tbody")
        val bigTable = tables[0]
        val middleTable = tables[1]
        val smallTable = tables[2]
        val weaponsTable: MutableList<Element> = ArrayList()
        weaponsTable.addAll(bigTable.children())
        weaponsTable.addAll(middleTable.children())
        weaponsTable.addAll(smallTable.children())
        val weapons: MutableList<Weapon> = ArrayList()
        for (i in weaponsTable.indices) {
            val tr = weaponsTable[i]
            val rofHandling = if (i + 1 < weaponsTable.size) weaponsTable[i + 1] else null
            val rSpeedVelocity = if (i + 2 < weaponsTable.size) weaponsTable[i + 2] else null
            val meleeTr = if (i + 3 < weaponsTable.size) weaponsTable[i + 3] else null
            if (rofHandling == null || rSpeedVelocity == null || meleeTr == null) continue
            if (tr.child(0).attr("rowspan") == "4") {
                val name = tr.child(1).child(0).text()
                val damage = tr.child(7).text()
                val range = tr.child(9).text()
                val rateOfFire = rofHandling.child(1).text()
                val handling = rofHandling.child(3).text()
                val reloadSpeed = rSpeedVelocity.child(1).text()
                val muzzleVelocity = rSpeedVelocity.child(3).text()
                val melee = meleeTr.child(1).text()
                val heavyMelee = meleeTr.child(3).text()
                val price = tr.child(2).text()
                val capacity = tr.child(5).text()
                val ammo = tr.child(4).text()
                val imageUrl =
                    if (tr.child(0).child(0).child(0).child(0).attr("data-src") == "") tr.child(0).child(0).child(0)
                        .child(0).attr("src") else tr.child(0).child(0).child(0).child(0).attr("data-src")
                weapons.add(
                    Weapon(
                        name,
                        damage,
                        range,
                        rateOfFire,
                        handling,
                        reloadSpeed,
                        muzzleVelocity,
                        melee,
                        heavyMelee,
                        Weapons.selectByFullName(name),
                        imageUrl,
                        price,
                        capacity,
                        ammo,
                        "-"
                    )
                )
            }
        }
        Utils.weapons = weapons as ArrayList<Weapon>
    }

    init {
        try {
            fetchWeaponDetails()
            Utils.weapons.add(
                Weapon(
                    "TestWeapon",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    Weapons.NONE,
                    "",
                    "",
                    "",
                    "",
                    ""
                )
            )
            checkWeapons()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
}