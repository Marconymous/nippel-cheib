import functions.weapons.Weapons;
import handlers.CommandHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import utils.EventUtils;
import utils.StringCompare;
import utils.Tuple;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public enum Commands {
    PING(
            event -> {
                event.getChannel().sendMessage("--pong").queue();
            },
            "ping"
    ),

    FETCH(
            event -> {
                StringBuilder message = new StringBuilder();
                try {
                    URL url = new URL(event.getMessage().getContentRaw().split("\\s")[1]);
                    InputStream is = url.openStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;

                    while ((line = br.readLine()) != null) {
                        message.append(line).append("\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    event.getChannel().sendMessage("._. No!").queue();
                }

                event.getChannel().sendMessage(message).queue();
            }, "fetch"
    ),

    WEAPON(
            event -> {
                String cmd = EventUtils.spitRawContent(event)[0];
                String weapon = EventUtils.rawContent(event).replace(cmd + " ", "");
                System.out.println(weapon);

                Tuple<Weapons, Float> best = new Tuple<>(null, (float) 0);
                for (Weapons w : Weapons.values()) {
                    float val = StringCompare.levenshtein(weapon, w.getFullName());
                    for (String s : w.getAliases()) {
                        val = Math.max(StringCompare.levenshtein(weapon, s), val);
                    }
                    System.out.println(val);
                    if (val > best.getVal2()) best = new Tuple<>(w, val);
                    if (val == 1) break;
                }

                event.getChannel().sendMessage(best.getVal1().getFullName()).queue();
            }, "weapon"
    );

    private final String[] aliases;
    private final CommandHandler handler;

    Commands(CommandHandler handler, String... aliases) {
        this.aliases = aliases;
        this.handler = handler;
    }

    public String[] getAliases() {
        return aliases;
    }

    public void handle(MessageReceivedEvent event) {
        handler.handle(event);
    }

    private static Commands[] commands;

    public static Commands[] list() {
        if (commands == null) {
            commands = Commands.values();
        }
        return commands;
    }
}
