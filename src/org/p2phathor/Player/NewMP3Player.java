package org.p2phathor.Player;

import org.p2phathor.util.log.Log;
import org.p2phathor.util.log.LogLevel;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Jasper on 23-2-2016.
 */
public class NewMP3Player implements MediaPlayer {

    private Media currentSong;
    private AudioStream currentAudioStream;

    public void play() {
        InputStream is = (currentSong.getInputStream());
        ContinuousAudioDataStream cas = null;
        try {
            AudioStream as = new AudioStream(is);
            AudioData ad = as.getData();
            cas = new ContinuousAudioDataStream(ad);
        } catch (IOException e) {
            Log.log("Could not build an audiostream or AudioData in MP3Player", LogLevel.ERROR);
        }
        AudioPlayer.player.start(cas);
        /*
        AudioStream as = null;
        try {
            as = new AudioStream(currentSong.getInputStream());
            currentAudioStream = as;
        } catch (IOException e) {
            Log.log("IOException while trying to build an audiostream in MP3Player", LogLevel.ERROR);
        }
        AudioPlayer.player.start(as);
        */
    }

    public void stop() {
        AudioPlayer.player.stop(currentAudioStream);
    }
    public boolean canPlay(Media media) {
        return true;
        /*
        String[] splitted = media.getName().split("\\.");
        if ((splitted[splitted.length-1] == ".mp3") || (splitted[splitted.length-1] == ".wav")) {
            return true;

        }
        return false;
        */
    }
    public void giveMedia(Media media) {
        if (canPlay(media)) {
            currentSong = media;
        } else {
            Log.log("A media type that cannot be played is given to the MP3Player", LogLevel.ERROR);
        }
    }
    public void pause() {

    }
    public void unpause() {

    }
}
