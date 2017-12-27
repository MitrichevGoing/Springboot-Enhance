package com.mitri.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import redis.clients.jedis.Protocol;


@Data
//@ConfigurationProperties(prefix = JedisPoolProperties.JEDIS_POOL_PREFIX)
public class JedisPoolProperties {

    public static final String JEDIS_POOL_PREFIX = "jedis.pool";

    private String masterName = "mymaster";

    private String sentinelNodes;

    private String password = null;

    private int database = Protocol.DEFAULT_DATABASE;

    /** 对象池最大数 */
    private int maxTotal = 200;

    /** idle对列最大数 */
    private int maxIdle = 100;

    /** idle队列最小数 */
    private int minIdle = 10;

    /** 获取连接超时时间 */
    private int maxWaitMillis = 10000;

    private boolean testOnBorrow = true;

    private boolean testOnReturn = true;

    /** Idle时进行连接扫描 */
    private boolean testWhileIdle = true;

    /** 表示idle object evitor两次扫描之间要sleep的毫秒数 */
    private long timeBetweenEvictionRunsMillis = 30000;

    /** 表示idle object evitor每次扫描的最多的对象数 */
    private int numTestsPerEvictionRun = 10;

    /** 表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；
     * 这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义 */
    private int minEvictableIdleTimeMillis = 60000;

}
