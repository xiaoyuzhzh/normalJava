package org.iceberg.socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by xiaoy on 2017/2/3.
 */
public class Server {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(55324);

        while (true){
            Socket socket = serverSocket.accept();
            if(socket!=null){
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("getSocket1"+socket);
                        try {
                            InputStream inputStream = socket.getInputStream();//客户端输入流
                            byte[] buffer = new byte[1024];//缓冲字节数组
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                            StringBuffer stringBuffer = new StringBuffer();
                            while (bufferedInputStream.read(buffer)>0){
                                stringBuffer.append(new String(buffer, Charset.forName("utf-8")));
                                while (bufferedInputStream.available()>0){
                                    if(bufferedInputStream.available()<buffer.length){
                                        buffer = new byte[buffer.length];
                                    }
                                    bufferedInputStream.read(buffer);
                                    stringBuffer.append(new String(buffer, Charset.forName("utf-8")));
                                }
                                System.out.println(stringBuffer.toString());
                                stringBuffer.delete(0,stringBuffer.length());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
            }
        }

    }
}
