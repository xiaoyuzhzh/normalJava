package org.iceberg.test;

/**
 * Created by xiaoy on 2016/10/23.
 */
public class ReferenceAndValueTest {

    public static void printValue(Object b){
        System.out.println(b);
    }



    public static void main(String[] args) {
        Object a = new Object();
        System.out.println(a);
        printValue(a);
    }
}
