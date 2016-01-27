package org.p2phathor.Player;

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

            System.out.println(file.getName());

            String name = file.getName();

            System.out.println(name);

            if (name.contains("mp3")) {                                         //Todo: fix this, split(".") did not seem to work for me
                result.add(new MPEG(file.getName(), this));
                System.out.println("this one is gathered!");
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
