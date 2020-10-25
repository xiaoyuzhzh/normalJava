package org.iceberg.jvm.inline;

import java.util.Arrays;

/**
 * Created by xiaoyuzhzh on 10/9/2020.
 */
public class TestJitChild2 extends TestJit{

    @Override
    public int doubleValue(int i) {

        return i*3;
    }
}
