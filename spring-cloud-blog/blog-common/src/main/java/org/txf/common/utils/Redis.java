package org.txf.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Redis {
    private static final String REDIS_PREFIX_KEY = "default_prefix";
    private static final String REDIS_KEY_SPLIT = ":";
    private StringRedisTemplate redisTemplate;

    public Redis(StringRedisTemplate redisTemplate) {
         this.redisTemplate = redisTemplate;
    }

    public String get(String key) {
        if (key == null) {
            log.error("redis | get -------------> key is null");
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key, String value, long expire) {
        if (key == null || value == null) {
            log.error("redis | set -------------> key or value is null");
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error("Redis 操作失败", e);
            return false;
        }
    }

    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("Redis 操作失败", e);
            return false;
        }
    }

    public static String keyBuilder(String prefix, String... args)  {
        if (prefix == null) {
            prefix = REDIS_PREFIX_KEY;
        }
        StringBuilder key = new StringBuilder(prefix);
        for (String arg : args) {
            key.append(REDIS_KEY_SPLIT).append(arg);
        }
        return key.toString();
    }
}
