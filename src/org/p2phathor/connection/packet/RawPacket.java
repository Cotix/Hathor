package org.p2phathor.connection.packet;


/**
 * Created by cotix on 1/18/16.
 */
public abstract class RawPacket implements Packet {
    private byte[] data;

    public RawPacket(byte[] d) {
        data = d;
    }

    public RawPacket(String d) {
        this(d.getBytes());
    }

    @Override
    public byte[] getRaw() {
        return data;
    }
}
