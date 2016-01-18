package org.p2phathor.connection;

import org.p2phathor.connection.packet.Packet;

/**
 * Created by cotix on 1/18/16.
 */
public interface Connection extends Runnable {
    boolean send(Packet packet);
    Packet recv();
    void disconnect();
    boolean isConnected();
}
