package org.iceberg.gc;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;


/**
 * vmOption -XX:+PrintGCDetails -Xms30m -Xmx30m -XX:NewRatio=1 -XX:HeapDumpPath=d:/temp -XX:SurvivorRatio=1  -XX:-UseAdaptiveSizePolicy
 * vmOption中 {-XX:-UseAdaptiveSizePolicy} 这个参数如果不加， 年轻代的比例参数{-XX:SurvivorRatio=1}将不会有效果，还是会被默认8:1:1覆盖
 * 本测试类的执行逻辑是循环创建可回收对象 GCTarget ，有一个序号id
 * 将 GCTarget 放到弱引用对象 GCTargetWeakReference中，该弱引用对象与弱引用队列关联 REFERENCE_QUEUE
 * 调用方法 monitorReference 监测弱引用对象中的值何时被置为空
 *
 * 结果：弱引用对象在发生任何gc（不管是ygc或者full gc）,都可以被回收。强制回收的过程就是先将弱引用的referent置为null，
 * 使GCTarget失去引用，然后就跟普通回收过程一致。
 * 符合weekReference回收逻辑:再任意gc过程回收，但是如果是G1分区收集，应该可能扫描不到
 *
 * 如果让 GCTargetWeakReference 继承自 SoftReference,可以发现，GCTargetWeakReference的值(referent)只在full gc的之后才会被置为null
 * 符合SoftReference的回收逻辑：仅在内存不够的时候回收。
 *
 * REFERENCE_QUEUE可以和所有虚引用，弱引用，软引用关联，一旦这些引用中的referent被置为null的时候，引用的壳对象就会被放到队列中，方便用户监测和移除
 * 因为这个壳对象本身不会被回收。参考ThreadLocal的Entry。
 * ThreadLocal的Entry不使用REFERENCE_QUEUE来主动回收，而是依赖操作回收，难道是因为担心浪费线程资源？
 */
public class WeakReferenceTest {
    // 弱引用队列
    private final static ReferenceQueue<GCTarget> REFERENCE_QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) throws InterruptedException {
        LinkedList<GCTargetWeakReference> gcTargetList = new LinkedList<>();

        // 创建弱引用的对象，依次加入链表中
        GCTarget gcTarget;
        for (int i = 0; i < 30; i++) {
            gcTarget = new GCTarget(String.valueOf(i));
            TimeUnit.SECONDS.sleep(1);
            GCTargetWeakReference weakReference = new GCTargetWeakReference(gcTarget,
                REFERENCE_QUEUE);

//            gcTargetList.add(weakReference);

            monitorReference(weakReference);

            Thread s = new Thread(()->{
                Reference<? extends GCTarget> reference;
                while(true) {
                    reference = REFERENCE_QUEUE.poll();
                    if(reference!=null && reference instanceof GCTargetWeakReference) {
                        System.out.println("In queue, id is: " +
                                ((GCTargetWeakReference) (reference)).id + " and it's value is " + reference.get());

                    }
                }
            });
            s.setDaemon(true);
            s.start();


//            System.out.println("gcTargetList size = " + gcTargetList.size());
        }

//        System.gc();
//        printList(gcTargetList);

        // 检查关联的引用队列是否为空
        Reference<? extends GCTarget> reference;
        while((reference = REFERENCE_QUEUE.poll()) != null) {
            if(reference instanceof GCTargetWeakReference) {
                System.out.println("In queue, id is: " +
                        ((GCTargetWeakReference) (reference)).id);
            }
        }

    }

    private static void monitorReference(GCTargetWeakReference weakReference) {
        Thread s = new Thread(()->{
            while (weakReference.get()!=null){
                System.out.println(weakReference.get().id + " still alive");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(weakReference.id + " collected");
        });
        s.setDaemon(true);
        s.start();
    }

    private static void printList(LinkedList<GCTargetWeakReference> gcTargetList) {
        gcTargetList.stream().forEach(gcTargetWeakReference -> {
            System.out.println("gcTargetWeakReference = " + gcTargetWeakReference);
            if(gcTargetWeakReference!=null){
                System.out.println(gcTargetWeakReference.id + "=================" +gcTargetWeakReference.get());
            }
        });
    }

    private static void gcAndPrint() {

        System.out.println("start system gc");

        // 通知GC进行垃圾回收
        System.gc();

        try {
            // 休息几分钟，等待上面的垃圾回收线程运行完成
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 检查关联的引用队列是否为空
        Reference<? extends GCTarget> reference;
        while((reference = REFERENCE_QUEUE.poll()) != null) {
            if(reference instanceof GCTargetWeakReference) {
                System.out.println("In queue, id is: " +
                    ((GCTargetWeakReference) (reference)).id);
            }
        }
    }

}
