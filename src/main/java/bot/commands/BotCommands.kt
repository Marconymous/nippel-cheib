package bot.commands

import java.util.ArrayList

object BotCommands {
    private val commands = ArrayList<Command>()
    fun cmds(): ArrayList<Command> {
        return commands
    }

    fun register(command: Command) {
        commands.add(command)
    }
}