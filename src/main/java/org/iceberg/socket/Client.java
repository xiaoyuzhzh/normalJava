package org.iceberg.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

/**
 * Created by xiaoy on 2017/2/3.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",55324);

        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        OutputStream outputStream = socket.getOutputStream();


        while (true){
            String myline = br.readLine();
            System.out.println(myline);
            outputStream.write(myline.getBytes(Charset.forName("utf-8")));
            outputStream.flush();
        }
    }
}
