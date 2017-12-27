package com.mitri.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>Title: Springboot-Enhance--com.mitri.proxy.ProxyHandler</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:12 </p>
 * <p>Create DateTime: 2017/12/19 上午9:43 </p>
 *
 * @author Potter
 * @version v2
 */
public class ProxyHandler implements InvocationHandler {

  private Object targetObject;
  private int useTimes;

  public ProxyHandler(){}

  public ProxyHandler(Object targetObject){
    this.targetObject = targetObject;
  }

//  public Object bind(Object targetObject){
//    this.targetObject = targetObject;
//    return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
//  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    before();
    Object invoke = method.invoke(proxy, args);
    after();
    return invoke;
  }

  private void before(){
    System.out.println("do something before invoke");
  }

  private void after(){
    useTimes++;
    System.out.printf("已使用%d%n", useTimes);
  }
}
