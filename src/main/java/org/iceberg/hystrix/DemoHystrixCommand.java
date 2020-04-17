package org.iceberg.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by xiaoyuzhzh on 2020/3/17.
 */
public class DemoHystrixCommand extends HystrixCommand<String> {

    private final String name;

    public DemoHystrixCommand(String name){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Demo"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("Teset"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000))
        );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        System.out.println(Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            long end = System.currentTimeMillis();
            System.out.println("thread interrupted in "+ (end - start)+ "ms");
        }
        return "Demo:"+name;
    }

    public static void main(String[] args) {
        String first = new DemoHystrixCommand("first").execute();
        System.out.println(first);;

//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        Future<Integer> future = executorService.submit(() -> {
//            try {
//                Thread.sleep(30000);
//            } catch (InterruptedException e) {
//                System.out.println("current interrupted");
//            }
//            return 1;
//        });
//
//        try {
//            future.get(1, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//        future.cancel(true);
    }
}
