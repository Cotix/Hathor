package org.p2phathor.Player;

import org.p2phathor.Player.MediaExtensions.MPEG;
import org.p2phathor.Player.MediaExtensions.WAV;
import org.p2phathor.util.log.Log;
import org.p2phathor.util.log.LogLevel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jasper on 27-1-2016.
 */
public class LocalPath implements Path {
    private String path;
    private List<Media> mediaList = new ArrayList<Media>();


    public LocalPath(String path) {
        this.path = path;
    }

    public ArrayList<Media> gatherMedia() {
        ArrayList<Media> result = new ArrayList<Media>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            String name = file.getName();
            String[] splitted = name.split("\\.");
            if (splitted.length > 0 && splitted[splitted.length-1].equals("mp3")) {
                result.add(new MPEG(file.getName(), this));
                Log.log("Added " + file.getName(), LogLevel.INFO);
            }
            if (splitted.length > 0 && splitted[splitted.length-1].equals("wav")) {
                result.add(new WAV(file.getName(), file.getPath()));
                Log.log("Added " + file.getName(), LogLevel.INFO);
            }
        }
        return result;
    }

    public Media getMedia(String name) {
        for (Media media : mediaList) {
            if (media.getName() == name) {
                return media;
            }
        }
        return null;
    }
    public String toString() {
        return path;
    }
}
