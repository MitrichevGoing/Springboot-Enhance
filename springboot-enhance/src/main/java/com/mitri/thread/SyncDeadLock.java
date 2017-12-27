package com.mitri.thread;

/**
 * <p>Title: Springboot-Enhance--com.mitri.thread.SyncDeadLock</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:12 </p>
 * <p>Create DateTime: 2017/12/17 下午3:02 </p>
 *
 * @author Potter
 * @version v2
 */
public class SyncDeadLock {

  private static Object lockA = new Object();
  private static Object lockB = new Object();

  private void lockTest(){
    Thread thread1 = new Thread(() -> {
      synchronized (lockA) {
        try {
          System.out.println(Thread.currentThread().getName() + " get locka ing!");
          Thread.sleep(500);
          System.out.println(Thread.currentThread().getName() + " after sleep 500ms!");
        } catch (Exception e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " need lockb!Just waiting!");
        synchronized (lockB) {
          System.out.println(Thread.currentThread().getName() + " get lockb ing!");
        }
      }
    }, "thread1");

    Thread thread2 = new Thread(() -> {
      synchronized (lockB) {
        try {
          System.out.println(Thread.currentThread().getName() + " get lockb ing!");
          Thread.sleep(500);
          System.out.println(Thread.currentThread().getName() + " after sleep 500ms!");
        } catch (Exception e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " need locka! Just waiting!");
        synchronized (lockA) {
          System.out.println(Thread.currentThread().getName() + " get locka ing!");
        }
      }
    }, "thread2");

    thread1.start();
    thread2.start();



    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    thread1.interrupt();

  }

  public static void main(String[] args) {
    new SyncDeadLock().lockTest();
  }

}
