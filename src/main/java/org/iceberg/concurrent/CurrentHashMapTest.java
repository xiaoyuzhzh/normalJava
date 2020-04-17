package org.iceberg.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiaoyuzhzh on 2020/3/26.
 */
public class CurrentHashMapTest {

    public static void main(String[] args) {

        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

        ExecutorService executorService = Executors.newFixedThreadPool(100);


    }
}
