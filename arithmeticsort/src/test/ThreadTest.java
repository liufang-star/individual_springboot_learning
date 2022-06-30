package test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {

    //线程数
    protected static final int total = 50;

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();

        /**
         * corePoolSize：核心线程数
         * maximumPoolSize：最大线程数
         * keepAliveTime：线程空闲时长。如果超过该时长，非核心线程就会被回收
         * unit：指定keepAliveTime的时间单位。常用的有：TimeUnit.MILLISECONDS(毫秒)、TimeUnit.SECONDS(秒)、TimeUnit.MINUTES(分)
         * workQueue：任务队列。通过线程池的execute()方法提交的Runnable将对象存储在该队列中
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(30));
        System.out.println("总任务数：" + total);
        long start = System.currentTimeMillis();
        //模拟任务提交
        for (int i = 0; i < total; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("已执行" + integer.addAndGet(1) + "个任务");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            try {
                //注意这里使用了try抛出异常，默认拒绝策略会报错
                threadPoolExecutor.execute(thread);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        long end = 0;
        while (threadPoolExecutor.getCompletedTaskCount() < 50) {
            end = System.currentTimeMillis();
        }

        System.out.println("总耗时：" + (end - start));
    }

}
