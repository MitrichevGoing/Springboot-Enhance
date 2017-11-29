package com.mitri.zk;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * <p>Title: Springboot-Enhance--com.mitri.zk.ZkTest</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:11 </p>
 * <p>Create DateTime: 2017/11/15 下午11:50 </p>
 *
 * @author Potter
 * @version v2
 */
public class ZkTest {

  static String lock_path = "/curator_recipes_lock_path";
  static CuratorFramework client = CuratorFrameworkFactory.builder()
                                                          .connectString("localhost:2181")
                                                          .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
  public static void main(String[] args) throws Exception {
    client.start();
    final InterProcessMutex lock = new InterProcessMutex(client,lock_path);
    final CountDownLatch down = new CountDownLatch(1);
    for(int i = 0; i < 30; i++){
      new Thread(new Runnable() {
        public void run() {
          try {
            down.await();
            lock.acquire();
          } catch ( Exception e ) {}
          SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
          String orderNo = sdf.format(new Date());
          System.out.println("生成的订单号是 : "+orderNo);
          try {
            lock.release();
          } catch ( Exception e ) {}
        }
      }).start();
    }
    down.countDown();
  }
}
