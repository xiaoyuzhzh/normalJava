package org.iceberg.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by xiaoy on 2017/6/26.
 */
public class Client {

    public static void main(String[] args) throws Exception {
        InetSocketAddress address = new InetSocketAddress(8695);
        SocketChannel socketChannel = SocketChannel.open();
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

        socketChannel.connect(address);

        while (!socketChannel.finishConnect()){
            System.out.println("check finish connection");
        }

        String ss = "Hi from client";
        ByteBuffer byteBuffer = ByteBuffer.wrap(ss.getBytes("UTF-8"));
        while(byteBuffer.hasRemaining()){
            socketChannel.write(byteBuffer);
        }

        int select = selector.select();
        if(select>0){
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()){
                SelectionKey sk = iterator.next();
                if(sk.isReadable()){
                    SocketChannel socketChannel1 = (SocketChannel) sk.channel();
                    ByteBuffer allocate = ByteBuffer.allocate(8);
                    while (socketChannel1.read(allocate)>0){
                        allocate.flip();
                        System.out.println(new String(allocate.array(),"UTF-8")+"from server");
                        byteBuffer.clear();
                    }
                }
            }
        }
    }
}
