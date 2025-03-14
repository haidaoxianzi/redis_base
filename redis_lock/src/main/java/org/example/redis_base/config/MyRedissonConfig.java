package org.example.redis_base.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRedissonConfig {
    /**
     * 所有对Redisson的使用都是通过RedissonClient
     */
    @Bean(destroyMethod="shutdown")
    public RedissonClient redisson(){
        //1、创建配置
        Config config = new Config();
        //todo wq123 config.use后面接的方法，这几个都需要调研下
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
      //  config.setLockWatchdogTimeout(1);//默认情况下，看门狗的检查锁的超时时间是30秒
        //2、根据Config 创建出 RedissonClient实例
        RedissonClient redisson = Redisson.create(config);

        return redisson;
    }
}
