package org.p2phathor.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Jasper on 27-1-2016.
 */
public class MPEG implements Media {
    String path;

    public MPEG(String path) {
        this.path = path;
    }


    public InputStream getInputStream() {
        try {
            InputStream stream = new FileInputStream(path);
            System.out.println(stream.toString());
            return stream;
        } catch (FileNotFoundException e) {
            System.err.println("That is not a valid path!");
        }
        return null;
    }
}
