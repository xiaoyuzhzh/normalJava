package org.iceberg.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiaoy on 2017/8/8.
 */
public class SchedualedExecutorTest {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        ScheduledFuture<String> task1 = executorService.schedule(new Task("task1"), 2, TimeUnit.SECONDS);




//        try {
//            if(!task1.isCancelled()){
//                System.out.println(task1.get());
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (CancellationException e){
//            e.printStackTrace();
//        }
//
//        SleepUtils.second(3);
//        task1.cancel(true);
//        System.out.println("cancel task1");

        ScheduledFuture<String> task2 = executorService.schedule(new Task("task2"), 2, TimeUnit.SECONDS);

//        // 获取Java线程管理MXBean
//        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
//        // 不需要获取同步的monitor和synchronizer信息,仅获取线程和线程堆栈信息
//        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
//        // 遍历线程信息,仅打印线程ID和线程名称信息
//        for (ThreadInfo threadInfo : threadInfos) {
//            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.
//                    getThreadName());
//        }


        executorService.shutdown();

    }

    private static ScheduledFuture<String> getTask(ScheduledExecutorService executorService, String taskName) {
        return executorService.schedule(new Task(taskName), 2, TimeUnit.SECONDS);
    }

    private static class Task implements Callable<String>{

        private String taskName;

        public Task(String taskName){
            this.taskName = taskName;
        }

        @Override
        public String call() throws Exception {
            System.out.println(taskName+" started");
            SleepUtils.second(10);

            System.out.println(taskName + " finished");
            return taskName;
        }
    }
}
