package org.p2phathor.Player;

import org.p2phathor.Player.zoomplayer.ZoomMP3Player;
import org.p2phathor.util.log.Log;
import org.p2phathor.util.log.LogLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cotix on 10-2-16.
 */
public class HathorPlayer {
    private MediaLibrary mediaLibrary;
    private List<MediaPlayer> allPlayers = setAllPlayers();
    private MediaPlayer activePlayer;

    public HathorPlayer() {
        mediaLibrary = new MediaLibrary();
    }

    public void play() {
        activePlayer.play();
    }

    public boolean play(Media media) {
        if (activePlayer != null) {
            activePlayer.quit();
        }

        activePlayer = null;
        for (MediaPlayer mp : allPlayers) {
            if (mp.canPlay(media)) {
                activePlayer = mp;
                break;
            }
        }
        if (activePlayer == null) {
            Log.log("Can not find a suitable player for this media type!", LogLevel.ERROR);
            return false;
        }
        if (media != null) {
            activePlayer.giveMedia(media);
        } else {
            System.out.println("That is not a song... Please try again!");
            Log.log("An unavailable song was tried to play", LogLevel.VERBOSE);
            return false;
        }
        activePlayer.play();
        return true;
    }

    public void pause() {
        activePlayer.pause();
    }
    public void stop() {
        activePlayer.stop();
    }

    public void unpause() {
        activePlayer.unpause();
    }

    public void addPath(String path) {
        mediaLibrary.addPath(path);
    }

    public List<Media> getAllMedia() {
        return mediaLibrary.getAllMedia();
    }

    public ArrayList<MediaPlayer>setAllPlayers() {
        ArrayList<MediaPlayer> result = new ArrayList<>();
        result.add(new TinySoundPlayer());
        return result;
    }
    public void quit() {
        activePlayer.quit();
    }

}
