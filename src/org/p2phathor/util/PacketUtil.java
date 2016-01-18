package org.p2phathor.util;

import org.p2phathor.connection.packet.Packet;
import org.p2phathor.connection.packet.PacketType;
import org.p2phathor.connection.packet.PingPacket;
import org.p2phathor.connection.packet.PongPacket;

/**
 * Created by cotix on 1/18/16.
 */
public class PacketUtil {
    public static Packet createPacket(byte[] data) {
        PacketType type = PacketType.getPacketType(data[0]);
        if (type == null) return null;
        switch (type) {
            case PING:
                return new PingPacket(data);
            case PONG:
                return new PongPacket(data);
        }
        return null;
    }
}
