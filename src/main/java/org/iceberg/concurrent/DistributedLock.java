package org.iceberg.concurrent;

import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现了自动关闭接口的分布式锁
 * Created by xiaoyuzhzh on 7/2/2018.
 */
public class DistributedLock implements AutoCloseable, Lock {

    public static final Random r = new Random();

    private volatile boolean locked = false;

    private String key;

    private Synchronizer synchronizer;

    public DistributedLock(String key,Synchronizer synchronizer){
        Objects.requireNonNull(key);
        Objects.requireNonNull(synchronizer);
        this.key = key;
        this.synchronizer = synchronizer;
    }

    @Override
    public void close() throws Exception {
        if (locked) {
            synchronizer.remove(key);
        }
    }

    @Override
    public void lock() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tryLock() {
        //重复加锁
        if(locked){
            return locked;
        }
        locked = synchronizer.acquire(key);
        return locked;
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        return tryLock() ||
                doAcquireNanos(unit.toNanos(timeout));
    }

    /**
     * 在纳秒数内获取锁
     * @param timeout
     * @return
     */
    private boolean doAcquireNanos(long timeout) {
        long nano = System.nanoTime();
        try {
            while ((System.nanoTime() - nano) < timeout) {
                if(tryLock()){
                    return true;
                }
                // 短暂休眠，nano避免出现活锁
                Thread.sleep(3, r.nextInt(500));
            }
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
