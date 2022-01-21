package bot

import bot.commands.BotCommands
import bot.commands.CommandLister
import bot.commands.audio.PlaySong
import bot.commands.audio.DisconnectAudio
import bot.commands.ping.PingCommand
import bot.commands.weapon.WeaponCommand
import bot.utils.EventUtils
import lavalink.client.io.jda.JdaLavalink
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.Compression
import net.dv8tion.jda.api.utils.cache.CacheFlag
import java.net.URI
import java.util.*
import javax.security.auth.login.LoginException

class Bot : ListenerAdapter() {
    private lateinit var jda: JDA
    private var lavalink: JdaLavalink? = null

    @Throws(LoginException::class)
    fun run(args: Array<String>) {
        if (args.isEmpty()) {
            System.err.println("Please enter the API Key as an arg!")
            return
        }

        lavalink = JdaLavalink(args[0].extractId(), 1) { jda }

        lavalink!!.addNode(URI.create("ws://127.0.0.1:2333"), "youshallnotpass")

        val builder = JDABuilder.createDefault(args[0])

        builder.setCompression(Compression.NONE)
        builder.setActivity(Activity.competing("a 1v1 against your mom"))
        builder.addEventListeners(lavalink, Bot())
        builder.enableIntents(GatewayIntent.GUILD_VOICE_STATES)
        builder.enableCache(CacheFlag.VOICE_STATE)
        builder.setVoiceDispatchInterceptor(lavalink!!.voiceInterceptor)

        registerCommands()
        jda = builder.build()

    }

    private fun registerCommands() {
        BotCommands.register(WeaponCommand(this))
        BotCommands.register(PingCommand(this))
        BotCommands.register(CommandLister(this))
        BotCommands.register(PlaySong(this, lavalink!!))
        BotCommands.register(DisconnectAudio(this, lavalink!!))
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (isBotMessage(event)) return
        if (!EventUtils.rawContent(event).substring(0, PREFIX.length).equals(PREFIX, ignoreCase = true)) return
        val inputCmd = EventUtils.spitRawContent(event)[0]
        println("inputCmd = $inputCmd")
        commandLoop@ for (cmd in BotCommands.cmds()) {
            if (inputCmd.equals(PREFIX + cmd.name, ignoreCase = true)) {
                cmd.handle(event)
                break
            }
            for (alias in cmd.aliases) {
                if (inputCmd.equals(PREFIX + alias, ignoreCase = true)) {
                    cmd.handle(event)
                    break@commandLoop
                }
            }
        }
    }

    private fun isBotMessage(e: MessageReceivedEvent): Boolean {
        return Objects.requireNonNull(e.member)!!.user.isBot
    }

    companion object {
        const val PREFIX = "--"

        @Throws(LoginException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            Bot().run(args)
        }
    }
}

fun String.extractId() = String(Base64.getDecoder().decode(this.split(".")[0]))