package org.p2phathor;

import org.p2phathor.Player.HathorPlayer;
import org.p2phathor.util.log.Log;
import org.p2phathor.view.ConsoleView;
import org.p2phathor.view.PlayerView;


/**
 * Created by cotix on 28-1-16.
 */

public class Main {
    public static void main(String[] args) {
        Log.enableAllLevels();
        HathorPlayer player = new HathorPlayer();
        PlayerView view = new ConsoleView(player);
        view.run();
    }
}
