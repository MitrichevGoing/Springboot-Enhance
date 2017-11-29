package com.mitri;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: Springboot-Enhance--com.mitri.CountDownLatchTest</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:11 </p>
 * <p>Create DateTime: 2017/11/3 下午1:21 </p>
 *
 * @author Potter
 * @version v2
 */
public class CountDownLatchTest {

  public static void main(String[] args) {
    final CountDownLatch latch = new CountDownLatch(2);

    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
        .setNameFormat("demo-pool-%d").build();
    ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    singleThreadPool.execute(()->{
      try {
        System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
        Thread.sleep(3000);
        System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
        latch.countDown();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    singleThreadPool.execute(() -> {
      try {
        System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
        Thread.sleep(3000);
        System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
        latch.countDown();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });




    try {
      System.out.println("等待2个子线程执行完毕...");
      latch.await();
      System.out.println("2个子线程已经执行完毕");
      System.out.println("继续执行主线程");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    singleThreadPool.shutdown();
  }

}
