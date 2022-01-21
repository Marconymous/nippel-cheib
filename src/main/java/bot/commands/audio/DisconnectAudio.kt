package bot.commands.audio

import bot.Bot
import bot.commands.Command
import lavalink.client.io.jda.JdaLavalink
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class DisconnectAudio(bot: Bot, private val lavalink: JdaLavalink) : Command(bot, false, "dc", "Disconnects the Bot from vc!") {
    override fun handle(event: MessageReceivedEvent) {
        if (event.guild.selfMember.voiceState?.inVoiceChannel() == true) {
            val link = lavalink.getLink(event.guild)
            link.player.clearTrack()
            event.guild.audioManager.closeAudioConnection()
        }
    }
}