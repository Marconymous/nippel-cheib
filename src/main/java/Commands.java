import functions.weapons.Weapons;
import handlers.CommandHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.requests.Route;
import utils.EventUtils;
import utils.StringCompare;
import utils.Tuple;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public enum Commands {
    PING(
            event -> {
                event.getChannel().sendMessage("--pong").queue();
            },
            false, "Ping the Bot", "ping"
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
            }, false, "Fetch the Content of a Website!", "fetch"
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
            }, false, "Get Infos about a Weapon from Hunt: Showdown", "weapon"
    ),

    COMMANDS(
            event -> {
                EmbedBuilder builder = new EmbedBuilder();

                builder.setTitle("Commands", null);
                builder.setColor(Color.BLACK);
                builder.setDescription("All Commands");
                for (Commands cmd : Commands.list()) {
                    if (!cmd.isHidden) {
                        builder.addField(Bot.PREFIX + cmd.toString().toLowerCase(), cmd.description, true);
                        builder.addField("Aliases", String.join(", ", cmd.aliases), true);
                        builder.addBlankField(false);
                    }
                }

                event.getChannel().sendMessage(builder.build()).queue(msg -> msg.addReaction("U+2139").queue());
            },
            false,
            "Lists all available Commands", "cmds", "commands", "help"
    );

    private final String[] aliases;
    private final CommandHandler handler;
    private final boolean isHidden;
    private final String description;

    Commands(CommandHandler handler, boolean isHidden, String description, String... aliases) {
        this.isHidden = isHidden;
        this.description = description;
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

    public boolean isHidden() {
        return isHidden;
    }

    public String getDescription() {
        return description;
    }
}
