package org.iceberg.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by xiaoyuzhzh on 7/2/2018.
 */
public class DistributedLockUtil {


    private static final ThreadLocal<DistributedLock> lockMap = new ThreadLocal<>();

    private static final ThreadLocal<AtomicInteger> lockLevel = new ThreadLocal<>();

    private Synchronizer synchronizer;

    private DistributedLockUtil(Synchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }

    /**
     * 锁内执行代码，自动释放
     *
     * @param key      加锁关键字
     * @param function 执行函数，入参是 boolean，如果加锁成功，则是true
     */
    public <T> T executeInLock(String key, Function<Boolean, T> function) {
        try (DistributedLock lock = getLock(key)) {
            return function.apply(lock.tryLock());
        }  finally {
            releaseLock();
        }
    }

    private DistributedLock getLock(String key) {
        DistributedLock distributedLock = lockMap.get();
        if (distributedLock == null) {
            distributedLock = new DistributedLock(key, synchronizer);
            lockMap.set(distributedLock);
            lockLevel.set(new AtomicInteger(1));
        } else {
            lockLevel.get().getAndIncrement();//记录一次重复获取
        }
        return distributedLock;
    }

    private DistributedLock releaseLock() {
        DistributedLock distributedLock = lockMap.get();
        if (distributedLock != null && lockLevel.get().decrementAndGet() < 1) {
            lockMap.remove();//移除线程上下文的锁
        }
        return distributedLock;
    }

    /**
     * 锁内执行代码，自动释放
     *
     * @param key      加锁关键字
     * @param supplier 如果加锁成功，则执行函数
     */
    public <T> T executeInLockSuccess(String key, Supplier<T> supplier) {
        try (DistributedLock lock = getLock(key)) {
            if (lock.tryLock()) {
                return supplier.get();
            } else {
                throw new RuntimeException("lock failed");
            }
        }finally {
            releaseLock();
        }
    }

    /**
     * 锁内执行代码，自动释放
     *
     * @param key      加锁关键字
     * @param runnable 如果加锁成功，则执行函数
     */
    public void runInLockSuccess(String key, Runnable runnable) {
        try (DistributedLock lock = getLock(key)) {
            if (lock.tryLock()) {
                runnable.run();
            } else {
                throw new RuntimeException("lock failed");
            }
        }finally {
            releaseLock();
        }
    }

    /**
     * 锁内执行代码，自动释放
     *
     * @param key      加锁关键字
     * @param consumer 执行函数，入参是 boolean，如果加锁成功，则是true
     */
    public void executeInLock(String key, Consumer<Boolean> consumer) {
        try (DistributedLock lock = getLock(key)) {
            consumer.accept(lock.tryLock());
        }finally {
            releaseLock();
        }
    }

    /**
     * 自动执行可重入锁
     *
     * @param key      加锁关键字
     * @param function 执行函数，入参是 boolean，如果加锁成功，则是true
     * @param timeout  加锁超时时间
     * @param timeUnit 超时时间单位
     */
    public <T> T executeInLock(String key, Function<Boolean, T> function, Long timeout, TimeUnit timeUnit) {
        try (DistributedLock lock = getLock(key)) {
            return function.apply(lock.tryLock(timeout, timeUnit));
        } catch (InterruptedException e) {
            return function.apply(false);
        }finally {
            releaseLock();
        }
    }

}
