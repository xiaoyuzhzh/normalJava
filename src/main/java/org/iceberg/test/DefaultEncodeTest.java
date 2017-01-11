package org.iceberg.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Created by xiaoy on 2016/11/15.
 */
public class DefaultEncodeTest {

    public static void main(String[] args) throws IOException {
        String str = "中";
        System.out.println(Arrays.toString(str.getBytes()));
        System.out.println(Arrays.toString(str.getBytes("UTF-8")));
        System.out.println(Arrays.toString(str.getBytes("GBK")));


        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(inputStreamReader);

        br.readLine();
    }
}
