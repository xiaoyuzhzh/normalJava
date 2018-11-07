package org.iceberg.java.io.stream;

import java.io.PrintStream;
import java.util.logging.Logger;

/**
 * Created by xiaoyuzhzh on 6/29/2018.
 */
public class Test {

    static final Logger logger = Logger.getLogger("test");

    static final PrintStream systemOut = System.out;



    public static void main(String[] args) {
        System.out.println(systemOut);
    }
}
