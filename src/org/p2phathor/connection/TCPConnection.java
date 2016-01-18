package org.p2phathor.connection;

import org.p2phathor.connection.packet.Packet;
import org.p2phathor.util.PacketUtil;
import org.p2phathor.util.log.Log;
import org.p2phathor.util.log.LogLevel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by cotix on 1/18/16.
 */
public class TCPConnection implements Connection {
    private String ipAddress;
    private short port;
    private Socket sock;
    private boolean isConnected;
    private DataOutputStream out;
    private DataInputStream in;
    private Queue<Packet> packetQueue;

    public TCPConnection(String ip, short port) {
        this.port = port;
        ipAddress = ip;
        packetQueue = new ConcurrentLinkedQueue<Packet>();
        isConnected = false;
        try {
            sock = new Socket(ipAddress, port);
            isConnected = true;
            Log.log("TCPConnection to " + ipAddress + " on port " + port + " succeeded.", LogLevel.VERBOSE);
            new Thread(this).start();
        } catch (IOException e) {
            Log.log("Could not connect to " + ip + ":" + port + ". Reason: " + e.getLocalizedMessage(), LogLevel.ERROR);
            disconnect();
        }
    }

    @Override
    public boolean send(Packet packet) {
        if (!isConnected) {
            return false;
        }
        byte[] raw = packet.getRaw();
        try {
            out.writeInt(raw.length);
            out.write(raw);
            out.flush();
        } catch (IOException e) {
            Log.log("Could not send packet to " + ipAddress + ":" + port + ". Reason: " + e.getLocalizedMessage(), LogLevel.VERBOSE);
            disconnect();
            return false;
        }
        return true;
    }

    @Override
    public Packet recv() {
        return packetQueue.poll();
    }

    @Override
    public void disconnect() {
        if (isConnected) {
            isConnected = false;
            Log.log("TCPConnection with " + ipAddress + " on port " + port + " got closed.", LogLevel.VERBOSE);
            try {
                sock.close();
            } catch (IOException e) {
                //Closing failed.. oh well, it is gone regardless.
            }
        }
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public void run() {
        while (isConnected) {
            try {
                int length = in.readInt();
                byte[] buffer = new byte[length];
                int atByte = 0;
                while (atByte < length) {
                    int recvLen = in.read(buffer, atByte, length-atByte);
                    atByte += recvLen;
                }
                if (atByte != length) {
                    Log.log("Major error in packet reading! atByte != length.", LogLevel.ODD);
                } else {
                    packetQueue.add(PacketUtil.createPacket(buffer));
                }
            } catch (IOException e) {
                disconnect();
            }

        }
    }
}
