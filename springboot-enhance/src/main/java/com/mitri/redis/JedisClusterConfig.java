package com.mitri.redis;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

//@Configuration
@Data
//@ConfigurationProperties(prefix = "jedis.cluster")
public class JedisClusterConfig {
    private String nodesString;
    private Boolean testWhileIdle;
    private Integer connectionTimeout;
    private Integer soTimeout;
    private Integer maxAttempts;
    private String password;

    @Bean
    public JedisCluster jedisCluster() {

        String[] nodes = nodesString.split(",");
        Set<HostAndPort> nodeSet = new HashSet<HostAndPort>(nodes.length);

        for (String node : nodes) {
            String[] nodeAttrs = node.trim().split(":");
            HostAndPort hap = new HostAndPort(nodeAttrs[0], Integer.valueOf(nodeAttrs[1]));
            nodeSet.add(hap);
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        JedisCluster jedisCluster = new JedisCluster(nodeSet, connectionTimeout, soTimeout, maxAttempts, poolConfig);
        return jedisCluster;
    }

}
