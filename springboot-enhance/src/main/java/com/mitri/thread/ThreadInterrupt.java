package com.mitri.thread;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: Springboot-Enhance--com.mitri.thread.ThreadInterrupt</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:12 </p>
 * <p>Create DateTime: 2017/12/17 下午4:04 </p>
 *
 * @author Potter
 * @version v2
 */
public class ThreadInterrupt {

  public static void main(String[] args) throws InterruptedException {
    Runner first = new Runner();
    Thread thread1 = new Thread(first, "Thread1");
    thread1.start();
    TimeUnit.SECONDS.sleep(2);
    thread1.interrupt();

    Runner second = new Runner();
    Thread thread2 = new Thread(second, "Thread2");
    thread2.start();
    TimeUnit.SECONDS.sleep(1);
    second.cancle();
  }



  private static class Runner implements Runnable{

    private long i;

    private volatile boolean on = true;

    @Override
    public void run() {

      while (on && !Thread.currentThread().isInterrupted()){
        i++;
      }

      System.out.println("count is now :" + i);

    }

    private void cancle(){
      this.on = false;
    }
  }

}


