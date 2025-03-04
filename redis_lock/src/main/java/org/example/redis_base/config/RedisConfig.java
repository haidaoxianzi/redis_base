package org.example.redis_base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:application.properties")
public class RedisConfig {
    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.timeout}")
    private int timeout;

    @Value("${redis.maxIdle}")
    private int maxIdle;

    @Value("${redis.maxWaitMillis}")
    private int maxWaitMillis;

    @Value("${redis.blockWhenExhausted}")
    private Boolean blockWhenExhausted;

    @Value("${redis.JmxEnabled}")
    private Boolean JmxEnabled;

    @Bean
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置Jedis连接池中最大的空闲连接数。超过这个数量的空闲连接将会被释放。
        jedisPoolConfig.setMaxIdle(maxIdle);

        /**
         * 设置Jedis连接池中获取连接时的最大等待时间（以毫秒为单位）。
         * 当连接池中的连接已满，且没有空闲连接可用时，新请求会尝试获取连接，并在这个设置的最大等待时间内进行阻塞等待。
         * 如果在这个时间内还没有获得连接，则会抛出JedisConnectionException异常。*/
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        // 连接耗尽时是否阻塞, false报异常,true阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);


         /*开启或关闭JMX（Java Management Extensions，Java管理扩展）监控功能。当设置为true时，表示开启JMX监控，
         这可以实时监控Jedis连接池的状态和性能，有助于进行性能调优和问题排查。*/
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(JmxEnabled);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
        return jedisPool;
    }
}
