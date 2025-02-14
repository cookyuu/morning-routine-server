package com.cookyuu.morning_routine.global.utils;

import io.lettuce.core.RedisException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisUtils {

    private final StringRedisTemplate redisTemplate;

    // key를 통해 Value 리턴
    public String getData(String key) {
        try {
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            return valueOperations.get(key);
        } catch (RedisException e) {
            log.error("[Redis::Error] Get data process, Exception : ", e);
            throw e;
        }
    }

    // 유효시간 동안 key,value 저장
    public void setDataExpire(String key, Object value, long durationSec) {
        try {
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            Duration expiredDuration = Duration.ofMinutes(durationSec);
            valueOperations.set(key, String.valueOf(value), expiredDuration);
        } catch (RedisException e) {
            log.error("[Redis::Error] Save data process, Exception : ", e);
            throw e;
        }
    }

    public void setData(String key, Object value) {
        try {
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key, String.valueOf(value));
        } catch (RedisException e) {
            log.error("[Redis::Error] Save data process, Exception : ", e);
            throw e;
        }
    }

    public void setHashCountData(String key) {
        try {
            HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
            String hashKey = "count";
            hashOperations.put(key, hashKey, "1");
        } catch (RedisException e) {
            log.error("[Redis::Error] Save hash count data process, Exception : ", e);
            throw e;
        }
    }

    // 삭제
    public void deleteData(String key) {
        try {
            redisTemplate.delete(key);
        } catch (RedisException e) {
            log.error("[Redis::Error] Delete data process , Exception : ", e);
            throw e;
        }
    }

    public Boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (RedisException e) {
            log.error("[Redis::Error] Check exists of key process, Exception :  ", e);
            throw e;
        }
    }

    public Long increaseCount(String key) {
        try {
            return redisTemplate.opsForValue().increment(key);
        } catch (RedisException e) {
            log.error("[Redis::Error] Increase count of Key, Exception : ", e);
        }
        return null;
    }

    public Map<String, Integer> getHashCountDataAndDelete(String keyPattern) {
        try {
            HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
            Cursor<byte[]> cursor = redisTemplate.getConnectionFactory().getConnection().scan(
                    ScanOptions.scanOptions().match("*" + keyPattern + "*").build()
            );

            Map<String, Integer> countDataMap = new HashMap<>();

            while (cursor.hasNext()) {
                String key = new String(cursor.next(), StandardCharsets.UTF_8);
                Map<String, String> entries = hashOperations.entries(key);
                countDataMap.put(key, Integer.valueOf(entries.get("count")));
                hashOperations.delete(key, "count");
            }
            return countDataMap;
        } catch (RedisException e) {
            log.error("[Redis::Error] Count Data and Delete Process, Exception : ", e);
            throw e;
        }

    }

    public void setExpire(String key, long expireTime) {
        redisTemplate.expire(key, Duration.ofSeconds(expireTime));
    }

    public Long decrement(String key) {
        return redisTemplate.opsForValue().getOperations().opsForValue().decrement(key);
    }
}
