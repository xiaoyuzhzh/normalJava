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

    public static <T,V extends T> boolean c(T t, V v){
        return t == v;
    }

    public static void main(String[] args) throws IOException {
//        String str = "中";
//        System.out.println(Arrays.toString(str.getBytes()));
//        System.out.println(Arrays.toString(str.getBytes("UTF-8")));
//        System.out.println(Arrays.toString(str.getBytes("GBK")));
//
//
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        BufferedReader br = new BufferedReader(inputStreamReader);
//        String strIn = br.readLine();
//        System.out.println(Arrays.toString(strIn.getBytes()));
        Double i = 0.0;
        String j = "0";
        System.out.println(c(i,j));
        System.out.println(c(j,i));
    }
}
