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
     * @param key
     */
    void release(String key);
}
