package org.p2phathor.Player;

/**
 * Created by Jasper on 30-1-2016.
 */
public interface MediaPlayer {
    void play();
    void pause();
    void stop();
    void giveMedia(Media media);
}
