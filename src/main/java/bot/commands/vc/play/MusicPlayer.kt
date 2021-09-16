package bot.commands.vc.play

import bot.Bot
import bot.commands.Command
import bot.utils.EventUtils
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.util.*

class MusicPlayer(val bot: Bot) : Command(bot, false, "play", "Plays music etc..", "p") {
    override fun handle(event: MessageReceivedEvent) {
        println("Handle Music Play")
        if (event.author.isBot) return

        println("Before Voice State")
        val state = Objects.requireNonNull(event.member)!!.voiceState ?: return

        println("Before Channel Check")
        val vc = state.channel ?: return

        val link = bot.lavalink.getLink(event.guild)
        link.connect(vc)

        val track = EventUtils.spitRawContent(event)[1]

        bot.playerManager.loadItem(track, object : AudioLoadResultHandler {
            override fun loadFailed(e: FriendlyException) {
                event.channel.sendMessage(e.message ?: e.toString()).queue()
            }

            override fun trackLoaded(track: AudioTrack) {
                event.channel.sendMessage("Playing `${track.info.title}`").queue()
                link.player.playTrack(track)
            }

            override fun noMatches() {
                event.channel.sendMessage("No matches").queue()
            }

            override fun playlistLoaded(playlist: AudioPlaylist) {
                val loaded = playlist.selectedTrack ?: playlist.tracks.firstOrNull()
                if (loaded == null) {
                    event.channel.sendMessage("Empty playlist").queue()
                    return
                }
                event.channel.sendMessage("Playing `${loaded.info.title}` from list `${playlist.name}`").queue()
                link.player.playTrack(loaded)
            }
        })
    }
}