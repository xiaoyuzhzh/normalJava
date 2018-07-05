package org.iceberg.concurrent;

import java.util.HashMap;

import io.vavr.control.Option;

/**
 * Created by xiaoyuzhzh on 6/29/2018.
 */
public class MockRedisServer {
    private static volatile HashMap<String,Object> keys = new HashMap<>();

    public static boolean setNx(String key,Object value){
        synchronized (keys){
            if(keys.get(key)!=null){
                return false;
            }else {
                keys.put(key,value);
                return true;
            }
        }
    }

    public static boolean delete(String key){
        synchronized (keys){
            if(keys.get(key)!=null){
                keys.remove(key);
            }
            return true;
        }
    }
}
