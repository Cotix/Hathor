package org.p2phathor.Player;

import javazoom.jl.player.advanced.AdvancedPlayer;
import org.p2phathor.util.log.Log;
import org.p2phathor.util.log.LogLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jasper on 27-1-2016.
 */
public class MediaLibrary {
    private AdvancedPlayer currentPlayer;
    private List<Path> pathList = new ArrayList<Path>();
    private List<Media> allMedia = new ArrayList<Media>();


    public List<Media> getAllMedia() {
        return allMedia;
    }

    public void addPath(String path) {
        LocalPath newPath = new LocalPath(path);
        pathList.add(newPath);
        allMedia.addAll(newPath.gatherMedia());
    }

    public ArrayList<Media> gatherAllMedia() {
        ArrayList<Media> result = new ArrayList<Media>();
        for (Path path : pathList) {
            result.addAll(path.gatherMedia());
        }
        return result;
    }
}
