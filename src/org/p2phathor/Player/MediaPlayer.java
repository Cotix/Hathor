package org.p2phathor.Player;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jasper on 27-1-2016.
 */
public class MediaPlayer {
    List<Media> playList = new ArrayList<Media>();
    AdvancedPlayer currentPlayer;

    public void addToPlayList(Media m) {
        playList.add(m);
    }

    public void play() {
        if (playList.size() > 0) {
            try {
                System.out.println("player being made");
                currentPlayer = new AdvancedPlayer(playList.get(0).getInputStream());
                System.out.println("player made");
                currentPlayer.play();
                //System.out.println(currentPlayer.isComplete());
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        MediaPlayer mp = new MediaPlayer();
        MPEG mpeg = new MPEG("C:/Users/Jasper/Downloads/Major Lazer - Light It Up (feat. Nyla & Fuse ODG) [Remix].mp3");
        mp.addToPlayList(mpeg);
        mp.play();
    }
}
