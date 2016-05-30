package org.p2phathor.Player;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;

import java.io.File;
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
        if (!TinySound.isInitialized()) {
            TinySound.init();
        }
        if (currentSong != null) {
            File file = new File(currentSong.getPath());
            currentMusic = TinySound.loadMusic(file);
            currentMusic.play(false);
        }
    }
    public void stop() {
            currentMusic.stop();
    }
    public boolean canPlay(Media media) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // do nothing
        }
        return (media.getName().endsWith(".wav"));
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
    public void quit() {
        TinySound.shutdown();
    }
}
