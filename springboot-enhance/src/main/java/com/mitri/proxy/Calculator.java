package com.mitri.proxy;

/**
 * <p>Title: Springboot-Enhance--com.mitri.proxy.Calculator</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:12 </p>
 * <p>Create DateTime: 2017/12/19 上午9:43 </p>
 *
 * @author Potter
 * @version v2
 */
public class Calculator implements ICalculator {

  @Override
  public int add(int a, int b) {
    return a+b;
  }

  @Override
  public int sub(int a, int b) {
    return a-b;
  }
}
