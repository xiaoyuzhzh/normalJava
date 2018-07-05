package org.iceberg.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import io.vavr.control.Try;

/**
 * Created by xiaoyuzhzh on 7/2/2018.
 */
public class DistributedLockUtil {

    private static AtomicInteger successTimes = new AtomicInteger(0);

    private static Synchronizer synchronizer = new Synchronizer() {
        @Override
        public boolean acquire(String key) {
            return MockRedisServer.setNx(key,1);
        }

        @Override
        public void remove(String key) {
            MockRedisServer.delete(key);
        }
    };

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0 ; i< 100 ; i ++){
            executorService.submit(()->{
                Try.of(()-> executeInLock("aaa",doSomeThing(1),1000l,TimeUnit.MILLISECONDS)).onFailure(e -> e.printStackTrace());
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("success: "+ successTimes);
    }

    /**
     * 自动执行可重入锁
     * @param key 加锁关键字
     * @param function 执行函数，入参是 boolean，如果加锁成功，则是true
     * @param <T>
     * @return
     */
    public static <T> T executeInLock(String key, Function<Boolean,T> function) throws Exception {
        try(DistributedLock lock = new DistributedLock("aaa",synchronizer)){
            return function.apply(lock.tryLock());
        }catch (Throwable e){
            throw e;
        }
    }

    /**
     * 自动执行可重入锁
     * @param key 加锁关键字
     * @param function 执行函数，入参是 boolean，如果加锁成功，则是true
     * @param <T>
     * @return
     */
    public static <T> T executeInLock(String key, Function<Boolean,T> function, Long timeout ,TimeUnit timeUnit) throws Exception {
        try(DistributedLock lock = new DistributedLock("aaa",synchronizer)){
            return function.apply(lock.tryLock(timeout,timeUnit));
        }catch (Throwable e){
            throw e;
        }
    }

    private static Function<Boolean,Void> doSomeThing(long milliseconds) {
        return lockSuccess -> {
            if(lockSuccess){
                successTimes.addAndGet(1);
            }else {
                System.out.println("lock failed");
            }
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        };
    }
}
