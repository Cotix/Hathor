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

    public void giveMedia(Media media) {
        stream = media.getInputStream();
    }

    public void play() {
        if (paused) {
            paused = !paused;
        } else {
            this.start();
        }
    }


    public void pause() {
        paused = !paused;
    }

    public void stopPlayer() {

    }

    public void run() {
        System.out.println("Thread running");
        try {
            thisPlayer = new AdvancedPlayer(stream);
        } catch (JavaLayerException e) {
            Log.log(e.getMessage(), LogLevel.ERROR);
        }
        System.out.println("playing");                      //Todo: implement the possibility to pause, will have to look into the library further
        /**
        playing:
        try {
            boolean ended;
            int i = 0;
            while (!paused) {
                thisPlayer.play(0+i, 1+i);
                i += 1;
                System.out.println("in while loop of playing");

            }
            System.out.println("Stopped playing");
         **/
        try {
            thisPlayer.play();
        } catch (JavaLayerException e) {
            Log.log(e.getMessage(), LogLevel.ERROR);
        }
    }
}
