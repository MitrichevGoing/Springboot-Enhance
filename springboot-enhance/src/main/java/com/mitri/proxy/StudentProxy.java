package com.mitri.proxy;

/**
 * <p>Title: Springboot-Enhance--com.mitri.proxy.StudentProxy</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:12 </p>
 * <p>Create DateTime: 2017/12/21 上午12:16 </p>
 *
 * @author Potter
 * @version v2
 */
public class StudentProxy implements Student {

  private Student student;

  public StudentProxy(){
    this.student = new StudentImpl();
  }

  @Override
  public void sayHello() {
    System.out.println("before.....");
    student.sayHello();
    System.out.println("after....");
  }
}
