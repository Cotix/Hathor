package org.p2phathor.Player.zoomplayer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import org.p2phathor.Player.MediaExtensions.MPEG;
import org.p2phathor.Player.Media;
import org.p2phathor.Player.MediaPlayer;
import org.p2phathor.util.log.Log;
import org.p2phathor.util.log.LogLevel;

/**
 * Created by Jasper on 30-1-2016.
 */
public class ZoomMP3Player extends Thread implements MediaPlayer {
    private AdvancedPlayer player;
    private Media media;
    private boolean paused = false;
    private static int pausedOnFrame;
    public void giveMedia(Media media) {
        this.media = media;
    }

    @Override
    public boolean canPlay(Media media) {
        if (media instanceof MPEG) return true;
        return false;
    }

    public void play() {
        pausedOnFrame = 0;
        this.start();
    }


    public void pause() {
        paused = true;
        player.stop();
    }

    public void unpause() {
        paused = false;
    }

    public void run() {
        while (true) {
            if (paused == false) {
                try {
                    player = new AdvancedPlayer(media.getInputStream());
                    player.setPlayBackListener(new PlaybackListener() {
                        @Override
                        public void playbackFinished(PlaybackEvent event) {
                            pausedOnFrame = event.getFrame();
                            System.out.println(event.getFrame());
                        }
                    });
                } catch (JavaLayerException e) {
                    Log.log(e.getMessage(), LogLevel.ERROR);
                }

                try {
                    player.play(pausedOnFrame, Integer.MAX_VALUE);
                } catch (JavaLayerException e) {
                    Log.log(e.getMessage(), LogLevel.ERROR);
                }
            } else {
                try {
                    this.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void quit() {
        return;
    }
}
