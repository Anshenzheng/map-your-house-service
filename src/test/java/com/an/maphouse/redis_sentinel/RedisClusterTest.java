package com.an.maphouse.redis_sentinel;

import com.an.maphouse.MapHouseApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;

/**
 * @author Annan An
 * @date 2020/8/21 16:31
 */
@Slf4j
class RedisClusterTest  extends MapHouseApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testCluster(){
        stringRedisTemplate.opsForValue().set("user", "xuqibin");
        String user = stringRedisTemplate.opsForValue().get("user");
        Assert.isTrue(StringUtils.equals("xuqibin", user), "获取结果不匹配");
    }
}