package org.p2phathor.Player;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;

import java.util.ArrayList;

/**
 * Created by Jasper on 29-4-2016.
 */
public class TinySoundPlayer implements MediaPlayer {
    private Media currentSong;
    private Music currentMusic;

    public TinySoundPlayer() {
        TinySound.init();
    }


    public void play() {
        if (currentSong != null) {
            currentMusic = TinySound.loadMusic(currentSong.getPath() + "/" + currentSong.getName());
            currentMusic.play(true);
        }
    }
    public void stop() {
            currentMusic.stop();
    }
    public boolean canPlay(Media media) {
        return (media.getName().endsWith(".mp3"));
    }
    public void pause() {
        currentMusic.pause();
    }
    public void giveMedia(Media media) {
        currentSong = media;
    }
    public void unpause() {
        currentMusic.play(false);
    }
}
