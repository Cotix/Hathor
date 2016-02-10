package org.p2phathor.Player;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import org.p2phathor.util.log.Log;
import org.p2phathor.util.log.LogLevel;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Jasper on 30-1-2016.
 */
public class MP3Player extends Thread implements MediaPlayer {
    AdvancedPlayer thisPlayer;
    InputStream stream;
    boolean paused = false;
    Thread currentThread;

    public void giveMedia(Media media) {
        stream = media.getInputStream();
    }

    @Override
    public boolean canPlay(Media media) {
        if (media instanceof MPEG) return true;
        return true;
    }

    public void play() {
        this.start();
    }


    public void pause() {
        thisPlayer.close();
        try {
            currentThread.join();
        } catch (InterruptedException e) {
            Log.log("InterruptedException while trying to shut the MP3PlayerThread down", LogLevel.ERROR);
        }
    }

    public void stopPlayer() {
        thisPlayer.close();
        try {
            currentThread.join();
        } catch (InterruptedException e) {
            Log.log("InterruptedException while trying to shut the MP3PlayerThread down", LogLevel.ERROR);
        }
    }

    public void run() {
        System.out.println("Thread running");
        currentThread = this;
        try {
            thisPlayer = new AdvancedPlayer(stream);
        } catch (JavaLayerException e) {
            Log.log(e.getMessage(), LogLevel.ERROR);
        }
        System.out.println("playing");                      //Todo: implement the possibility to pause, will have to look into the library further
        try {
            thisPlayer.play();
        } catch (JavaLayerException e) {
            Log.log(e.getMessage(), LogLevel.ERROR);
        }
    }
}
