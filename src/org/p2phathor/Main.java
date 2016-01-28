package org.p2phathor;

import org.p2phathor.Player.Media;
import org.p2phathor.Player.MediaPlayer;
import org.p2phathor.util.log.Log;
import org.p2phathor.util.log.LogLevel;

import java.util.Scanner;

/**
 * Created by cotix on 28-1-16.
 */
public class Main {
    public static void main(String[] args) {
        Log.enableAllLevels();
        MediaPlayer mp = new MediaPlayer();
        String input = "";
        Scanner in = new Scanner(System.in);
        while (input != "quit") {
            Log.log("Please give some input:", LogLevel.HIGHEST);
            input = in.nextLine();
            String[] splitted = input.split(" ");
            if (splitted[0].equals("setPath")) {
                mp.addPath(splitted[1]);
                Log.log("Path " + splitted[1] + " set!", LogLevel.HIGHEST);
            }
            if (splitted[0].equals("display")) {
                Log.log(mp.displayMedia(), LogLevel.HIGHEST);
            }
            if (splitted[0].equals("play")) {
                String mediaName = input.substring(5, input.length());
                for (Media media : mp.getAllMedia()) {
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
