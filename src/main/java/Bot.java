import functions.weapons.Weapon;
import functions.weapons.Weapons;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bot extends ListenerAdapter {
    public final static String PREFIX = "--";
    public List<Weapon> weapons;

    public static void main(String[] args) throws LoginException {
        new Bot().run(args);
    }

    public void run(String[] args) throws LoginException {
        if (args.length < 1) {
            System.err.println("Please enter the API Key as an arg!");
            return;
        }

        JDABuilder builder = JDABuilder.createDefault(args[0]);
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.NONE);
        builder.setActivity(Activity.competing("a 1v1 against your mom"));

        builder.addEventListeners(new Bot());

        try {
            fetchWeaponDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkWeapons();

        JDA jda = builder.build();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (isBotMessage(event)) return;

        String inputCmd = event.getMessage().getContentRaw().split("\\s")[0];
        System.out.println("inputCmd = " + inputCmd);

        commandLoop:
        for (Commands cmd : Commands.list()) {
            for (String alias : cmd.getAliases()) {
                if (inputCmd.equalsIgnoreCase(PREFIX + alias)) {
                    cmd.handle(event);
                    break commandLoop;
                }
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

                weapons.add(new Weapon(name, damage, range, rateOfFire, handling, reloadSpeed, muzzleVelocity, melee, heavyMelee, Weapons.selectByFullName(name)));
            }
        }
        this.weapons = weapons;

        System.out.println(this.weapons);
    }

    private void checkWeapons() {
        for (Weapon w : this.weapons) {
            if (w.getEnumConstant() == null) {
                System.err.println(w.getName() + " has no EnumConstant");
            }
        }
    }

    private boolean isBotMessage(MessageReceivedEvent e) {
        return Objects.requireNonNull(e.getMember()).getUser().isBot();
    }
}
