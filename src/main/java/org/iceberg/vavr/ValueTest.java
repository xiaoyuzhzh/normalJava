package org.iceberg.vavr;

import io.vavr.Lazy;

/**
 * Created by xiaoyuzhzh on 1/18/2018.
 */
public class ValueTest {

    public static void main(String[] args) {
        CharSequence chars = Lazy.val(() -> "Yay!", CharSequence.class);
        System.out.println(chars);
        System.out.println(chars.length());

        Value value = Lazy.val(()->{
            System.out.println("加载实例");
            return new ValueInt(2);
        },Value.class);
        System.out.println("调用前");
        System.out.println(value.getValue());
        System.out.println("再调用一次"+value.getValue());

    }

    public interface Value{
        int getValue() ;
    }

    public static class ValueInt implements Value{

        int value;

        public ValueInt(int value){
            this.value = value;
        }

        @Override
        public int getValue() {
            return value;
        }
    }
}
