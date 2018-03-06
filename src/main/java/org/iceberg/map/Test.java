package org.iceberg.map;

/**
 * Created by xiaoy on 2017/2/6.
 */
public class Test {

    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 1024) ? 1024 : n + 1;
    }

    public static void main(String[] args) {
        Integer a = 5;
        a = a>>>1;
        System.out.println(Integer.toBinaryString(a));
        tableSizeFor(6);
    }

}
