package org.p2phathor.connection.packet;

import java.nio.ByteBuffer;

/**
 * Created by cotix on 1/18/16.
 */
public class PingPacket extends RawPacket {
    public final static PacketType TYPE = PacketType.PING;

    public PingPacket(byte[] raw) {
        super(raw);
    }

    public PingPacket(long time) {
        this(ByteBuffer.allocate(4).putInt(TYPE.getValue()).putLong(time).array());
    }

    public PingPacket() {
        this(System.currentTimeMillis());
    }

}
