package com.mitri.proxy;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * <p>Title: Springboot-Enhance--com.mitri.proxy.CglibProxy</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:12 </p>
 * <p>Create DateTime: 2017/12/19 上午11:03 </p>
 *
 * @author Potter
 * @version v2
 */
public class CglibProxy implements MethodInterceptor {

  private Object target;

  public Object getInstance(Object target){
    this.target = target;
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(this.target.getClass());
    enhancer.setCallback(this);
    return enhancer.create();
  }

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
      throws Throwable {
    System.out.println("before....");
    Object invoke = methodProxy.invokeSuper(o, objects);
    System.out.println("after....");
    return invoke;
  }
}
