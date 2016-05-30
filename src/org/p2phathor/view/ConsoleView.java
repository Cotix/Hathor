package org.p2phathor.view;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.p2phathor.Player.*;
import org.p2phathor.util.log.Log;
import org.p2phathor.util.log.LogLevel;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Jasper on 30-1-2016.
 */
public class ConsoleView implements  PlayerView{
    private HathorPlayer player;

    public ConsoleView(HathorPlayer p) {
        player = p;
    }

    public void commandPlaySong(String[] splitted) {
        String songName = "";
        Media songToPlay = null;
        for (int i = 1; i < splitted.length; i++) {
            songName += splitted[i];
            if (i < splitted.length -1) {
                songName += " ";
            }
        }
        List<Media> allMedia = player.getAllMedia();
        for (Media media : allMedia) {
            if (songName.equals(media.getName())) {
                songToPlay = media;
                break;
            }
        }
        player.play(songToPlay);
    }

    public void commandPlay() {
        player.play();
    }
    public void commandStop() {
        player.stop();
    }

    public void commandDisplay() {
        for (Media media : player.getAllMedia()) {
            System.out.println(media.getName());
        }
    }

    public void commandPause() {
        player.pause();
    }

    public void commandUnpause() {
        player.unpause();
    }

    public void commandHelp() {
        System.out.println("help provided");
    }
    public void commandQuit() {
        Log.log("System is going down!", LogLevel.WARNING);
        player.quit();
        System.exit(0);
    }
    public void commandSetPath(String path) {
        player.addPath(path);
        Log.log("Path " + path + " set", LogLevel.VERBOSE);
    }

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("Please give input:");
            input = in.nextLine();
            String[] splitted = input.split(" ");
            switch (splitted[0]) {
                case "play":
                    if (splitted.length > 1) {
                        commandPlaySong(splitted);
                    } else if (splitted.length == 1) {
                        commandPlay();
                    } else {
                        Log.log("Play was entered without entering a song name", LogLevel.VERBOSE);
                        commandHelp();
                    }
                    break;
                case "display":
                    commandDisplay();
                    break;
                case "pause":
                    commandPause();
                    break;
                case "stop":
                    commandStop();
                    break;
                case "quit":
                    commandQuit();
                    break;
                case "unpause":
                    commandUnpause();
                    break;
                case "setPath":
                    if (splitted.length == 2) {
                        commandSetPath(splitted[1]);
                    } else {
                        Log.log("setPath was called with an incorrect amount of arguments", LogLevel.VERBOSE);
                        commandHelp();
                    }
                    break;
                default:
                    commandHelp();
            }
        }
    }
}