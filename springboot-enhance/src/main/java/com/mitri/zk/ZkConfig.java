package com.mitri.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title: Springboot-Enhance--com.mitri.zk.ZkConfig</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:11 </p>
 * <p>Create DateTime: 2017/11/15 下午11:25 </p>
 *
 * @author Potter
 * @version v2
 */
//@Configuration
public class ZkConfig {

  @Value("${zk.url}")
  private String zkUrl;

  @Bean
  public CuratorFramework getCuratorFramework(){
    RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
    CuratorFramework client = CuratorFrameworkFactory.newClient(zkUrl,retryPolicy);
    client.start();
    return client;
  }

}
