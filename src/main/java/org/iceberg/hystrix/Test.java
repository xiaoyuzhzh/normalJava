package org.iceberg.hystrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiaoyuzhzh on 2020/3/17.
 */
public class Test {

    public static volatile int index = 1;

    public static void main(String[] args) {
        print(10);
    }

    public static void print(int n){
        if(n<1){
            return;
        }
        for(int i = 1 ; i<=n ; i ++){
            new Thread(new Task(i)).start();;
        }
    }

    public static class Task implements Runnable{

        private int id;

        public Task(int id){
            this.id = id;
        }

        @Override
        public void run() {
            while ( index!= id){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(id == 1){
                System.out.print(id);
            }else{
                System.out.print(","+id);
            }
            index = id+1;
        }
    }
}
