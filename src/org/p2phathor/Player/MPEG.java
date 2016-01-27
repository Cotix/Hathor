package org.p2phathor.Player;

import org.p2phathor.util.log.Log;
import org.p2phathor.util.log.LogLevel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Jasper on 27-1-2016.
 */
public class MPEG implements Media {
    private String path;

    public MPEG(String path) {
        this.path = path;
    }


    public InputStream getInputStream() {
        try {
            InputStream stream = new FileInputStream(path);
            Log.log(stream.toString(), LogLevel.VERBOSE);
            return stream;
        } catch (FileNotFoundException e) {
            Log.log("That is not a valid path!", LogLevel.ERROR);
        }
        return null;
    }
}
