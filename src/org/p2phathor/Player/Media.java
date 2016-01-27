package org.p2phathor.Player;

import java.io.InputStream;

/**
 * Created by Jasper on 27-1-2016.
 */
public interface Media {
    public InputStream getInputStream();
    public String getName();
    public String getPath();

}
