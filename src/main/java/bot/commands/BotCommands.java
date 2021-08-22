package bot.commands;

import java.util.ArrayList;

public final class BotCommands {
    private static final ArrayList<Command> commands = new ArrayList<>();

    public static ArrayList<Command> cmds() {
        return commands;
    }

    public static boolean register(Command command) {
        return commands.add(command);
    }
}
