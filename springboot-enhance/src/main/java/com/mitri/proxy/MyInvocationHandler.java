package com.mitri.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>Title: Springboot-Enhance--com.mitri.proxy.MyInvocationHandler</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:12 </p>
 * <p>Create DateTime: 2017/12/19 上午10:34 </p>
 *
 * @author Potter
 * @version v2
 */
public class MyInvocationHandler implements InvocationHandler {

  private Object object;

  public MyInvocationHandler(Object object){
    this.object = object;
  }

  public MyInvocationHandler(){
    super();
  }

  public Object bind(Object object){
    this.object = object;
    return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(), this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("before...");
    Object invoke = method.invoke(object, args);
    System.out.println("after...");
    return invoke;
  }
}
