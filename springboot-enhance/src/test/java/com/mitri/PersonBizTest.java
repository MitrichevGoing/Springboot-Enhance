package com.mitri;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Potter on 2017/11/10.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonBizTest {

  @Resource
  private PersonBiz personBiz;

  @Test
  public void test1() throws Exception {
    personBiz.test();
  }

}
