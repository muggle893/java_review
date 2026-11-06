package org.txf.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.txf.common.utils.Redis;

@Configuration
// 如果配置了redis相关的配置才引入bean
@ConditionalOnProperty(
        prefix = "spring.data.redis",
        name = "host")
public class RedisConfig {

    @Bean
    public Redis redis(StringRedisTemplate redisTemplate) {
        return new Redis(redisTemplate);
    }

}
