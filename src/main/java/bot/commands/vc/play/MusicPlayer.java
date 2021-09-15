package bot.commands.vc.play;

import bot.commands.Command;
import bot.utils.EventUtils;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.playback.NonAllocatingAudioFrameBuffer;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.utils.WidgetUtil;

import javax.sound.midi.Track;
import java.util.Objects;

public class MusicPlayer extends Command {
    public MusicPlayer() {
        super(false, "play", "Plays music etc..", "p");
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        GuildVoiceState state = Objects.requireNonNull(event.getMember()).getVoiceState();
        if (state == null) return;

        VoiceChannel vc = state.getChannel();
        if (vc == null) return;

        if (!event.getGuild().getSelfMember().hasPermission(vc, Permission.VOICE_CONNECT)) return;

        AudioManager audioManager = event.getGuild().getAudioManager();

        if (audioManager.isConnected()) {
            EventUtils.sendMessageToChannel(event, "Bot is already connected to a voice channel!");
            return;
        }

        audioManager.openAudioConnection(vc);
        EventUtils.sendMessageToChannel(event, "Connected to channel!");

        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        AudioPlayer player = playerManager.createPlayer();

        TrackScheduler trackScheduler = new TrackScheduler(player);

        playerManager.loadItem("player", trackScheduler);

        
    }
}
