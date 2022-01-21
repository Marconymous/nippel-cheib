package bot.commands.audio

import bot.Bot
import bot.commands.Command
import lavalink.client.io.FunctionalResultHandler
import lavalink.client.io.jda.JdaLavalink
import lavalink.client.player.event.PlayerEvent
import lavalink.client.player.event.TrackExceptionEvent
import lavalink.client.player.event.TrackStartEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class PlaySong(bot: Bot, private val lavalink: JdaLavalink) : Command(bot, false, "play", "Plays audio from youtube i guess!") {
    override fun handle(event: MessageReceivedEvent) {
        val args = event.message.contentRaw.split("\\s".toRegex(), 2)

        playSong(event, args[1])
    }

    private fun playSong(event: MessageReceivedEvent, args: String) {
        connectToVoiceChannel(event)

        val link = lavalink.getLink(event.guild)

        link.player.addListener {  }

        link.restClient.loadItem(args, FunctionalResultHandler({
                event.channel.sendMessage("Currently playing: ${it.info.title}").queue()
                link.player.playTrack(it)
            },
            null, null, {
                event.channel.sendMessage("Song not found!").queue()
            }, {
                event.channel.sendMessage("Error: ${it.message}").queue()
                it.printStackTrace()
            }
        ))
    }

    private fun connectToVoiceChannel(event: MessageReceivedEvent) {
        val self = event.guild.selfMember.voiceState!!
        val senderVS = event.member!!.voiceState!!

        if (self.inVoiceChannel()) return
        if (!senderVS.inVoiceChannel()) return

        event.guild.audioManager.openAudioConnection(senderVS.channel!!)

        lavalink.getLink(event.guild).player.addListener(::handlePlayerEvent)
    }

    private fun handlePlayerEvent(event: PlayerEvent) {
        when (event) {
            is TrackStartEvent -> {
                println("${event.track.info.title} started playing")
            }
            is TrackExceptionEvent -> {
                event.exception.printStackTrace()
            }
        }
    }
}