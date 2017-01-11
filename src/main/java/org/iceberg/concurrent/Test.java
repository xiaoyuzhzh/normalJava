package org.iceberg.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by xiaoy on 2016/12/8.
 */
public class Test {



    static ExecutorService executorService = Executors.newSingleThreadExecutor();
    public static void main(String[] args) {

        for(int i =0 ; i< 20 ; i++){
            execute(i);
        }
    }

    public static void execute(final int i){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                if(i==5){
                    throw new RuntimeException();
                }
                System.out.println(i);
            }
        });
    }
}
