package org.p2phathor.connection.packet;

/**
 * Created by cotix on 1/18/16.
 */
public enum PacketType {
    PING(0),
    PONG(1);

    private final int value;
    PacketType(final int v) {
        value = v;
    }

    public final int getValue() {
        return value;
    }

    public static PacketType getPacketType(int value) {
        switch (value) {
            case 0:
                return PING;
            case 1:
                return PONG;
        }
        return null;
    }

}
