package org.iceberg.socket.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by xiaoyuzhzh on 2020/4/9.
 */
public class MulticastFirst {

    public static void main(String[] args) throws IOException {
        // join a Multicast group and send the group salutations
        String msg = "Hello";
        InetAddress group = InetAddress.getByName("228.5.6.7");
        MulticastSocket s = new MulticastSocket(6789);
        s.joinGroup(group);
        DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg.length(),
                group, 6789);
        s.send(hi);
        // get their responses!
        byte[] buf = new byte[1000];
        DatagramPacket recv = new DatagramPacket(buf, buf.length);
        s.receive(recv);
        String msgr = new String(recv.getData()).trim();
        System.out.println("received on msg : " + msgr);
        // OK, I'm done talking - leave the group...
        s.leaveGroup(group);
    }
}
