import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.Objects;

public class Bot extends ListenerAdapter {
    private final static String prefix = "--";


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
                if (inputCmd.equalsIgnoreCase(prefix + alias)) {
                    cmd.handle(event);
                    break commandLoop;
                }
            }
        }
    }

    private boolean isBotMessage(MessageReceivedEvent e) {
        return Objects.requireNonNull(e.getMember()).getUser().isBot();
    }
}
