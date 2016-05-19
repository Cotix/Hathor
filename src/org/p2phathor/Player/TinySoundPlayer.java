package org.p2phathor.Player;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;

import java.util.ArrayList;

/**
 * Created by Jasper on 29-4-2016.
 */
public class TinySoundPlayer implements MediaPlayer {
    TinySound tinySound;
    Media currentSong;
    Music currentMusic;

    public TinySoundPlayer() {
        tinySound = new TinySound();
        tinySound.init();
    }


    public void play() {
        if (currentSong != null) {
            currentMusic = tinySound.loadMusic(currentSong.getPath());
            currentMusic.play(false);
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

    }
    public void unpause() {
        currentMusic.play(false);
    }
}
