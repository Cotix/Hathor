package org.p2phathor.Player.MediaExtensions;

import org.p2phathor.Player.Media;
import org.p2phathor.Player.Path;
import org.p2phathor.util.log.Log;
import org.p2phathor.util.log.LogLevel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Jasper on 27-1-2016.
 */
public class MPEG implements Media {
    private String name;
    private Path path;

    public MPEG(String name, Path path) {
        this.name = name;
        this.path = path;
    }


    public InputStream getInputStream() {
        try {
            InputStream stream = new FileInputStream(path+"/"+name);
            Log.log(stream.toString(), LogLevel.VERBOSE);
            return stream;
        } catch (FileNotFoundException e) {
            Log.log("That is not a valid path!", LogLevel.ERROR);
        }
        return null;
    }
    public String getName() {
        return name;
    }
    public String getPath() {
        return path.toString();
    }
}
