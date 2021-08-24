package bot.commands.weapon;

import bot.commands.Command;
import bot.utils.EventUtils;
import bot.utils.StringCompare;
import bot.utils.Tuple;
import bot.utils.Utils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class WeaponCommand extends Command {
    public WeaponCommand() {
        super(false, "weapon", "Displays information about a Weapon from Hunt: Showdown!", "!");
        try {
            fetchWeaponDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkWeapons();
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        String cmd = EventUtils.spitRawContent(event)[0];
        String weapon = EventUtils.rawContent(event).replace(cmd + " ", "");
        System.out.println(weapon);

        Tuple<Weapons, Float> best = new Tuple<>(null, (float) 0);
        for (Weapons w : Weapons.values()) {
            float val = StringCompare.levenshtein(weapon, w.getFullName());
            for (String s : w.getAliases()) {
                val = Math.max(StringCompare.levenshtein(weapon, s), val);
            }
            if (val > best.getY()) best = new Tuple<>(w, val);
            if (val == 1) break;
        }

        Weapon finalWeapon = null;
        for (Weapon w : Utils.getWeapons()) {
            if (w.getEnumConstant() == best.getX()){
                finalWeapon = w;
                break;
            }
        }

        assert finalWeapon != null;
        event.getChannel().sendMessage(WeaponUtils.buildWeaponEmbed(finalWeapon)).queue();
    }

    private void checkWeapons() {
        for (Weapon w : Utils.getWeapons()) {
            if (w.getEnumConstant() == null) {
                System.err.println(w.getName() + " has no EnumConstant");
            }
        }
    }

    private void fetchWeaponDetails() throws IOException {
        Document weaponsPage = Jsoup.connect("https://huntshowdown.fandom.com/wiki/Weapons").get();
        Elements tables = weaponsPage.body().select("tbody");

        Element bigTable = tables.get(0);
        Element middleTable = tables.get(1);
        Element smallTable = tables.get(2);

        List<Element> weaponsTable = new ArrayList<>();
        weaponsTable.addAll(bigTable.children());
        weaponsTable.addAll(middleTable.children());
        weaponsTable.addAll(smallTable.children());

        List<Weapon> weapons = new ArrayList<>();
        for (int i = 0; i < weaponsTable.size(); i++) {
            Element tr = weaponsTable.get(i);
            Element rofHandling = (i + 1 < weaponsTable.size()) ? weaponsTable.get(i + 1) : null;
            Element rSpeedVelocity = (i + 2 < weaponsTable.size()) ? weaponsTable.get(i + 2) : null;
            Element meleeTr = (i + 3 < weaponsTable.size()) ? weaponsTable.get(i + 3) : null;
            if (rofHandling == null || rSpeedVelocity == null || meleeTr == null) continue;

            if (tr.child(0).attr("rowspan").equals("4")) {
                String name = tr.child(1).child(0).text();
                String damage = tr.child(7).text();
                String range = tr.child(9).text();
                String rateOfFire = rofHandling.child(1).text();
                String handling = rofHandling.child(3).text();
                String reloadSpeed = rSpeedVelocity.child(1).text();
                String muzzleVelocity = rSpeedVelocity.child(3).text();
                String melee = meleeTr.child(1).text();
                String heavyMelee = meleeTr.child(3).text();
                String price = tr.child(2).text();
                String capacity = tr.child(5).text();
                String ammo = tr.child(4).text();

                String imageUrl = (
                        tr.child(0).child(0).child(0).child(0).attr("data-src").equals("")
                )
                        ? tr.child(0).child(0).child(0).child(0).attr("src")
                        : tr.child(0).child(0).child(0).child(0).attr("data-src");

                System.out.println(imageUrl);

                weapons.add(new Weapon(name, damage, range, rateOfFire, handling, reloadSpeed, muzzleVelocity, melee, heavyMelee, Weapons.selectByFullName(name), imageUrl, price, capacity, ammo));
            }
        }

        Utils.setWeapons(weapons);
    }
}
