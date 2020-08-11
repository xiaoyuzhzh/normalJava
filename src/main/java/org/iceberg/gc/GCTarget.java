package org.iceberg.gc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class GCTarget {
    // 对象的ID
    public String id;

    private static final int _1MB = 1<<20;

    // 占用内存空间
    byte[] buffer = new byte[_1MB];

    public GCTarget(String id) {
        System.out.println("create one gcTarget id is " + id);
        this.id = id;
    }

//    protected void finalize() throws Throwable {
//         执行垃圾回收时打印显示对象ID
//        System.out.println("Finalizing GCTarget, id is : " + id);
//    }
}