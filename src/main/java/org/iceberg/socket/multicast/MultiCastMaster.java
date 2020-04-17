package org.iceberg.socket.multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by xiaoyuzhzh on 2020/4/9.
 */
public class MultiCastMaster {

    public static void main(String[] args) throws Exception {
        InetAddress group = InetAddress.getByName("228.5.6.7");
        MulticastSocket multicastSocket = new MulticastSocket(6789);
        multicastSocket.joinGroup(group);
        Thread thread = new Thread(new Runnable() {
            public void run() {
                byte[] buf = new byte[2048];
                DatagramPacket recv = new DatagramPacket(buf, buf.length);
                while (!multicastSocket.isClosed()) {
                    try {
                        multicastSocket.receive(recv);
                        String msg = new String(recv.getData()).trim();
                        System.out.println("received on msg : " + msg);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "DubboMulticastRegistryReceiver");
        thread.setDaemon(true);
        thread.start();

        thread.join();
    }

}
