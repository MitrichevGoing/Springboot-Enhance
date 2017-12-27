package com.mitri.thread;

import org.hibernate.annotations.Synchronize;

/**
 * <p>Title: Springboot-Enhance--com.mitri.thread.SyncTest</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:12 </p>
 * <p>Create DateTime: 2017/12/17 下午4:33 </p>
 *
 * @author Potter
 * @version v2
 */
public class SyncTest {

  public static void main(String[] args) {
    synchronized (SyncTest.class){

    }

    m();
  }

  private static synchronized void m() {
  }

}
