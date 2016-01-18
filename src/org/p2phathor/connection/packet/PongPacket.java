package org.p2phathor.connection.packet;

import java.nio.ByteBuffer;

/**
 * Created by cotix on 1/18/16.
 */
public class PongPacket extends RawPacket {
    public final static PacketType TYPE = PacketType.PING;

    public PongPacket(byte[] raw) {
        super(raw);
    }

    public PongPacket(long time) {
        this(ByteBuffer.allocate(4).putInt(TYPE.getValue()).putLong(time).array());
    }

    public PongPacket() {
        this(System.currentTimeMillis());
    }

}
