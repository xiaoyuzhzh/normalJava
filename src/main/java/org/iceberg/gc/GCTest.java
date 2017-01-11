package org.iceberg.gc;

/**
 * Created by xiaoy on 2016/11/3.
 */
public class GCTest {

    private static final int _1MB = 1<<20;

    /**
     * vm options : -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation1,allocation2,allocation3,allocation4;

        allocation1 = new byte[2*_1MB];
        allocation2 = new byte[2*_1MB];
        allocation3 = new byte[2*_1MB];
        allocation4 = new byte[6*_1MB];

    }


}
