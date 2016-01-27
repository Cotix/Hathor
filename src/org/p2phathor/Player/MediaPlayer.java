package org.p2phathor.Player;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jasper on 27-1-2016.
 */
public class MediaPlayer {
    List<Media> playList = new ArrayList<Media>();
    AdvancedPlayer currentPlayer;
    List<Path> pathList = new ArrayList<Path>();
    List<Media> allMedia = new ArrayList<Media>();

    public void addToPlayList(Media m) {
        playList.add(m);
    }

    public void play() {
        if (playList.size() > 0) {
            try {
                currentPlayer = new AdvancedPlayer(playList.get(0).getInputStream());
                currentPlayer.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }

    public void addPath(String path) {
        pathList.add(new LocalPath(path));
        allMedia = gatherAllMedia();
    }

    public ArrayList<Media> gatherAllMedia() {
        ArrayList<Media> result = new ArrayList<Media>();
        for (Path path : pathList) {
            for (Media media : path.gatherMedia()) {
                result.add(media);
            }
        }
        return result;
    }
    public String displayMedia() {
        String result = "";
        for (Media media : allMedia) {
            result += media.getName();
            result += "\n";
        }
        return result;
    }


    public static void main(String[] args) {
        MediaPlayer mp = new MediaPlayer();
        String input = "";
        Scanner in = new Scanner(System.in);
        while (input != "quit") {
            System.out.println("Please give some input:");
            input = in.nextLine();
            String[] splitted = input.split(" ");
            if (splitted[0].equals("setPath")) {
                mp.addPath(splitted[1]);
                System.out.println("Path " + splitted[1] + " set!");
            }
            if (splitted[0].equals("display")) {
                System.out.println(mp.displayMedia());
            }
            if (splitted[0].equals("play")) {
                String mediaName = input.substring(5, input.length());
                for (Media media : mp.allMedia) {
                    if (media.getName().equals(mediaName)) {
                        mp.addToPlayList(media);
                        mp.play();
                        break;
                    }
                }
            }
        }
    }
}
