package org.iceberg.jvm.inline;

/**
 * Created by xiaoyuzhzh on 10/9/2020.
 */
public class TestJitChild extends TestJit{

    @Override
    public int doubleValue(int i) {
        return i*i;
    }
}
