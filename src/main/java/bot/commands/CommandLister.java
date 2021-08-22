package bot.commands;

import bot.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public final class CommandLister extends Command{
    public CommandLister() {
        super(false, "Commands", "Lists all available Commands!", "cmds", "help", "?");
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        EmbedBuilder builder = new EmbedBuilder();

        builder.setTitle("Commands", null);
        builder.setColor(Color.BLACK);
        builder.setDescription("All Commands");
        for (Command cmd : BotCommands.cmds()) {
            if (!cmd.isHidden()) {
                builder.addField(Bot.PREFIX + cmd.getName(), cmd.getDescription(), true);
                builder.addField("Aliases", String.join(", ", cmd.getAliases()), true);
                builder.addBlankField(true);
            }
        }

        event.getChannel().sendMessage(builder.build()).queue(msg -> msg.addReaction("U+2139").queue());
    }
}
