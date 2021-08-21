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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Bot extends ListenerAdapter {
    public final static String PREFIX = "--";


    public static void main(String[] args) throws LoginException {
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

    private static void fetchWeaponDetails() throws IOException {
        Document weaponsPage = Jsoup.connect("https://huntshowdown.fandom.com/wiki/Weapons").get();
        Elements tables = weaponsPage.body().select("tbody");

        Element bigTable = tables.get(0);
        Element middleTable = tables.get(1);
        Element smallTable = tables.get(2);

        List<Element> weaponsTable = Arrays.asList(bigTable, middleTable, smallTable);
        
        for (Element tr : weaponsTable) {
            if (tr.child(0).attr("rowspan").equals("4")) {
                System.out.println(tr.child(1).child(0).text() + " | " + tr.child(7).text() + " | " + tr.child(9).text());
            }
        }

    }

    private boolean isBotMessage(MessageReceivedEvent e) {
        return Objects.requireNonNull(e.getMember()).getUser().isBot();
    }
}
