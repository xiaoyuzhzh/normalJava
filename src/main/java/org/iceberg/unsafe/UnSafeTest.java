package org.iceberg.unsafe;

import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by xiaoy on 2017/1/19.
 */
public class UnSafeTest {

    public static void main(String[] args) {
//        long intClassAddress = normalize(getUnsafe().getInt(new Integer(0), 4L));
//        long strClassAddress = normalize(getUnsafe().getInt("", 4L));
//        getUnsafe().putAddress(intClassAddress + 36, strClassAddress);

//        System.out.println((String) (Object) (new Integer(666)));

        getUnsafe().throwException(new IOException("111"));//可以抛出checked异常而不用try catch或者方法加异常标记

    }



    private static long normalize(int value) {
        if(value >= 0) return value;
        return (~0L >>> 32) & value;
    }

    public static Unsafe getUnsafe(){
        Unsafe unsafe = null;
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return unsafe;
    }


}
