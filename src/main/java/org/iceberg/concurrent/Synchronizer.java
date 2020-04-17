package org.iceberg.concurrent;

/**
 * 分布式锁同步器
 * Created by xiaoyuzhzh on 7/2/2018.
 */
public interface Synchronizer {

    /**
     * 同步获取锁
     * @param key
     * @return
     */
    boolean acquire(String key);

    /**
     * 同步移除锁
     * 使用redis实现本同步器的时候需要注意不能让一个获取成功锁的线程去释放别的线程的锁
     * 比如获取锁的同时，加上一个value，来保障解锁不会越界
     * @param key
     */
    void release(String key);
}
