package org.p2phathor.Player;

import com.sun.deploy.util.StringUtils;
import org.p2phathor.util.log.Log;
import org.p2phathor.util.log.LogLevel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jasper on 30-1-2016.
 */
public class Console {

    public MediaLibrary mediaLib;
    public List<MediaPlayer> allPlayers = new ArrayList<>();
    public MediaPlayer activePlayer;


    public void executionLoop() {
        initialise();
        Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("Please give input:");
            input = in.nextLine();
            String[] splitted = input.split(" ");
            System.out.println(splitted[0]);
            switch (splitted[0]) {
                case "play":
                    System.out.println("play");
                    if (splitted.length > 1) {
                        commandPlay(splitted);
                    } else {
                        Log.log("Play was entered without entering a song name", LogLevel.VERBOSE);
                        commandHelp();
                    }
                    break;
                case "display":
                    System.out.println("display");
                    commandDisplay();
                    break;
                case "pause":
                    System.out.println("pause");
                    commandPause();
                    break;
                case "quit":
                    System.out.println("quit");
                    commandQuit();
                    break;
                case "setPath":
                    System.out.println("setPath");
                    if (splitted.length == 2) {
                        commandSetPath(splitted[1]);
                    } else {
                        Log.log("setPath was called with an incorrect amount of arguments", LogLevel.VERBOSE);
                        commandHelp();
                    }
                    break;
                default:
                    System.out.println("default");
                    commandHelp();
            }
        }
    }

    public void commandPlay(String[] splitted) {
        if (activePlayer != null) {
            activePlayer.stop();
        }
        String songName = "";
        Media songToPlay = null;
        for (int i = 1; i < splitted.length; i++) {
            songName += splitted[i];
            if (i < splitted.length -1) {
                songName += " ";
            }
        }
        System.out.println("Songname: "+ songName);
        List<Media> allMedia = mediaLib.getAllMedia();
        medialoop:
        for (Media media : allMedia) {
            System.out.println("Medianame: "+ media.getName());
            if (songName.equals(media.getName())) {
                System.out.println("Medianame is the same as Songname!");
                songToPlay = media;
                break medialoop;
            }
        }
        mediaplayerloop:
        for (MediaPlayer mp : allPlayers) {
            if (mp instanceof MP3Player) {
                activePlayer = mp;
                break mediaplayerloop;
            }
        }
        if (songToPlay != null) {
            activePlayer.giveMedia(songToPlay);
        } else {
            System.out.println("That is not a song... Please try again!");
            Log.log("An unavailable song was tried to play", LogLevel.VERBOSE);
            return;
        }
        activePlayer.play();
    }

    public void commandDisplay() {
        for (Media media : mediaLib.getAllMedia()) {
            System.out.println(media.getName());
        }
    }

    public void commandPause() {
        activePlayer.pause();
    }

    public void commandHelp() {
        System.out.println("help provided");
    }
    public void commandQuit() {
        Log.log("System is going down!", LogLevel.WARNING);
        System.exit(0);
    }
    public void commandSetPath(String path) {
        mediaLib.addPath(path);
        Log.log("Path " + path + " set", LogLevel.VERBOSE);
        System.out.println("Path " + path + " set!");
    }

    public void initialise() {
        mediaLib = new MediaLibrary();
        allPlayers.add(new MP3Player());
    }

    public static void main(String[] args) {
        Console console = new Console();
        console.executionLoop();
    }

}

