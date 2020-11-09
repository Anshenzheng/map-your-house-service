package com.an.maphouse.redis_sentinel;

import com.an.maphouse.MapHouseApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Annan An
 * @date 2020/8/20 14:25
 */
@Slf4j
class RedisSentinelTest  extends MapHouseApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSentinel() throws InterruptedException {
        int counter = 0;
        while (true){
            counter++;
            int number = (new Random()).nextInt(1000);
            String key = "key-" + number;
            String value = "value-" + number;
            stringRedisTemplate.opsForValue().set(key, value);
            if(counter % 100 == 0){
                log.info("{} value is {}", key, stringRedisTemplate.opsForValue().get(key));
            }
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }
}
