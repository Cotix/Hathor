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
    private List<Media> playList = new ArrayList<Media>();
    private AdvancedPlayer currentPlayer;
    private List<Path> pathList = new ArrayList<Path>();
    private List<Media> allMedia = new ArrayList<Media>();

    public void addToPlayList(Media m) {
        playList.add(m);
    }

    public List<Media> getAllMedia() {
        return allMedia;
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
}
