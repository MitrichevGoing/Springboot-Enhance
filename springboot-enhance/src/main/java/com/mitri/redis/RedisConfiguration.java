package com.mitri.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <p>Title: cloud-server--com.jike.assist.redis.config.RedisConfiguration</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:12 </p>
 * <p>Create DateTime: 2017/12/26 上午9:49 </p>
 *
 * @author Potter
 * @version v2
 */
@Configuration
@EnableCaching
public class RedisConfiguration {

  @Autowired
  private RedisProperties redisProperties;

  private static final int DEFAULT_EXPIRE_TIME = 3600 * 24;

  @Bean(name = "jedisSecondPool")
  @Qualifier("jedisSecondPool")
  @Primary
  @ConditionalOnExpression("${spring.redis.redis-first.enabled:true}")
  public JedisPool convertSecondJedisPool(
      @Value("${spring.redis.redis-second.host}") String hostName,
      @Value("${spring.redis.redis-second.port}") int port,
      @Value("${spring.redis.redis-second.password}") String password,
      @Value("${spring.redis.pool.max-idle}") int maxIdle,
      @Value("${spring.redis.pool.max-active}") int maxTotal,
      @Value("${spring.redis.redis-second.timeout}") int timeout,
      @Value("${spring.redis.pool.max-wait}") long maxWaitMillis) {
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    jedisPoolConfig.setMaxTotal( maxTotal);
    jedisPoolConfig.setMaxIdle(maxIdle);
    jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
    jedisPoolConfig.setTestOnBorrow(false);
    JedisPool pool =  new JedisPool(jedisPoolConfig, hostName, port, timeout,password);
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
    jedisConnectionFactory.setHostName(hostName);
    jedisConnectionFactory.setPassword(password);
    jedisConnectionFactory.setPort(port);
    jedisConnectionFactory.setDatabase(0);
    return  pool;
  }

  /**
   * jedisPoolConfig config
   */
//  @Bean
//  public JedisPoolConfig setJedisPool(){
//    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//    jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
//    jedisPoolConfig.setMinIdle(redisProperties.getMinIdle());
//    jedisPoolConfig.setTestOnBorrow(redisProperties.isTestOnBorrow());
//    return jedisPoolConfig;
//  }
//
//  /**
//   * JedisConnectionFactory
//   */
//  @Bean
//  public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
//    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//    jedisConnectionFactory.setHostName(redisProperties.getHost());
//    jedisConnectionFactory.setPort(redisProperties.getPort());
//    jedisConnectionFactory.setPassword(redisProperties.getPassword());
//    jedisConnectionFactory.setTimeout(redisProperties.getTimeout());
//    jedisConnectionFactory.setUsePool(true);
//    jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
//    return jedisConnectionFactory;
//  }

  @Bean
  public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<?, ?> redisTemplate = new RedisTemplate();
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    redisTemplate.setDefaultSerializer(new StringRedisSerializer());
    //设置普通value序列化方式
    redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
    redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
    return redisTemplate;
  }

  @Bean
  public CacheManager cacheManager(RedisTemplate redisTemplate) {
    RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
    redisCacheManager.setDefaultExpiration(DEFAULT_EXPIRE_TIME);
    return redisCacheManager;
  }

}
