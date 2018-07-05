package org.iceberg.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.function.Function;

/**
 * Created by xiaoyuzhzh on 6/29/2018.
 */
public class AutoCloseableLock implements AutoCloseable,Lock {

    private volatile boolean locked = false;

    private String key;

    private static AtomicInteger successTimes = new AtomicInteger(0);

    /**
     * 自动执行可重入锁
     * @param key 加锁关键字
     * @param function 执行函数，入参是 boolean，如果加锁成功，则是true
     * @param <T>
     * @return
     */
    public static <T> T executeInLock(String key, Function<Boolean,T> function) throws RuntimeException{
        try(AutoCloseableLock lock = new AutoCloseableLock("key")){
            return function.apply(lock.tryLock());
        }catch (Throwable e){
            throw e;
        }
    }

    public AutoCloseableLock(String key){
        this.key = key;
    }

    @Override
    public void close() throws RuntimeException {
        if(locked){
            MockRedisServer.delete(key);
            System.out.println("释放锁");
        }
    }


//    private static Function<Boolean,Void> doSomeThing(long milliseconds) {
//        return lockSuccess -> {
//            if(lockSuccess){
//                successTimes.addAndGet(1);
//                throw new RuntimeException("执行错误");
//            }
//            try {
//                Thread.sleep(milliseconds);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return null;
//        };
//    }


    @Override
    public void lock() {
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        //重复加锁
        if(locked){
            return locked;
        }
        locked = MockRedisServer.setNx(key,new Date());
        if(locked){
            System.out.println("加锁成功");
        }else {
            System.out.println("加锁失败");
        }
        return locked;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }


    public static void main(String[] args) throws Exception {

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for (int i = 0 ; i< 1 ; i ++){
//            executorService.submit(()->{
//                Try.of(()-> AutoCloseableLock.executeInLock("aaa",doSomeThing(1))).onFailure(e -> e.printStackTrace());
//            });
//        }
//        executorService.shutdown();
//        executorService.awaitTermination(2,TimeUnit.SECONDS);
//        System.out.println("success: "+ successTimes);
    }
}
