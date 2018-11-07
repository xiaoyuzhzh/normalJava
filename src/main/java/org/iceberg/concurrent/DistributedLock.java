package org.iceberg.concurrent;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现了自动关闭接口的分布式锁
 * Created by xiaoyuzhzh on 7/2/2018.
 */
public class DistributedLock implements AutoCloseable, Lock {

    public static final Random r = new Random();

    private final AtomicInteger lockTime = new AtomicInteger(0);

    private volatile boolean locked = false;

    private String key;

    private Synchronizer synchronizer;

    public DistributedLock(String key, Synchronizer synchronizer) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(synchronizer);
        this.key = key;
        this.synchronizer = synchronizer;
    }

    @Override
    public void close() {
        unlock();
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
        if (!locked) {
            locked = synchronizer.acquire(key);
        }
        if (locked) {//加锁成功记录一次
            lockTime.incrementAndGet();
        }
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
     */
    private boolean doAcquireNanos(long timeout) {
        long nano = System.nanoTime();
        try {
            while ((System.nanoTime() - nano) < timeout) {
                if (tryLock()) {
                    return true;
                }
                // 短暂休眠，nano避免出现活锁
                Thread.sleep(3, r.nextInt(500));
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 释放锁的实例必须是持有锁的实例
     */
    @Override
    public void unlock() {
        if (locked) {
            int lockTime = this.lockTime.decrementAndGet();
            if (lockTime < 1) {
                synchronizer.release(key);
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
