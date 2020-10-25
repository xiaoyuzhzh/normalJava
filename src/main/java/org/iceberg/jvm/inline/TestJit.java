package org.iceberg.jvm.inline;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 测试jit优化，热点代码直接会编译成机器码运行。
 * -XX:+PrintCompilation 该参数可以显示执行了jit的方法
 * -XX:+PrintInlining 该参数展示方法内联(需要配合参数 -XX:+UnlockDiagnosticVMOptions)
 */
public class TestJit {
    public static final int TIMES = 15000;
    public static final TestJit ch1 = new TestJitChild();
    public static final TestJit ch2 = new TestJitChild2();

    public static TestJit getCh(int i){
        if(i%2==0){
            return ch1;
        }else {
            return ch2;
        }
    }

    public int doubleValue(int i){
        return i * 2;
    }

    public static long calcSum(){
        long sum = 0;
        for (int i = 0; i < 100; i++) {
                sum += ch1.doubleValue(i);
        }
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < TIMES; i++) {
                calcSum();
            }
        }).start();
        TimeUnit.SECONDS.sleep(100000);
    }
}