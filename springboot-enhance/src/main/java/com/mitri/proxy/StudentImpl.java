package com.mitri.proxy;

/**
 * <p>Title: Springboot-Enhance--com.mitri.proxy.StudentImpl</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:12 </p>
 * <p>Create DateTime: 2017/12/19 上午10:33 </p>
 *
 * @author Potter
 * @version v2
 */
public class StudentImpl implements Student {

  @Override
  public void sayHello() {
    System.out.println("say hello~~~");
  }
}
