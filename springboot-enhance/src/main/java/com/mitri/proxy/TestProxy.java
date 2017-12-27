package com.mitri.proxy;

import java.lang.reflect.Proxy;
import org.springframework.cglib.proxy.Enhancer;

/**
 * <p>Title: Springboot-Enhance--com.mitri.proxy.TestProxy</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:12 </p>
 * <p>Create DateTime: 2017/12/19 上午9:54 </p>
 *
 * @author Potter
 * @version v2
 */
public class TestProxy {

  public static void main(String[] args) {
//    Student student = (Student) Proxy.newProxyInstance(TestProxy.class.getClassLoader(),
//        new Class<?>[]{Student.class},
//        new MyInvocationHandler(new StudentImpl()));


//    StudentImpl student = new StudentImpl();
//    MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
//    Student student1 = (Student) myInvocationHandler.bind(student);
//    student1.sayHello();

//    CglibProxy cglibProxy = new CglibProxy();
//
//    Enhancer enhancer = new Enhancer();
//    enhancer.setSuperclass(StudentImpl.class);
//    enhancer.setCallback(cglibProxy);
//    Student student = (Student) enhancer.create();
//    student.sayHello();

    StudentImpl studentImpl = new StudentImpl();
    CglibProxy cglibProxy = new CglibProxy();
    Student instance = (Student) cglibProxy.getInstance(studentImpl);
    instance.sayHello();
  }

}
