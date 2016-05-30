package org.p2phathor.Player.MediaExtensions;

import org.p2phathor.Player.Media;
import org.p2phathor.util.log.Log;
import org.p2phathor.util.log.LogLevel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Jasper on 30-5-2016.
 */
public class WAV implements Media {
    String path;
    String name;

    public WAV(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public InputStream getInputStream() {
        try {
            InputStream stream = new FileInputStream(path + "/" + name);
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
