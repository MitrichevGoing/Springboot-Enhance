package com.mitri.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title: cloud-server--com.jike.assist.redis.helper.RedisProperties</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:12 </p>
 * <p>Create DateTime: 2017/12/26 上午9:50 </p>
 *
 * @author Potter
 * @version v2
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "jike.redis")
public class RedisProperties {
  /** 默认超时时间 **/
  private int timeout = 2000;
  /** 最大连接总数**/
  private int maxTotal = 8;
  /** 最大空闲数 **/
  private int maxIdle = 8;
  /** 最小空闲数 **/
  private int minIdle = 0;
  /**池没有对象返回时，最大等待时间单位为毫秒 **/
  private int maxWaitMillis = -1;
  /** 在borrow一个jedis实例时，是否提前进行validate操作**/
  private boolean testOnBorrow = true;
  /** 默认数据库**/
  private int database = 0;
  /**默认地址**/
  private String host = "localhost";
  /**默认端口**/
  private int port = 6379;

  private String password = null;

}
