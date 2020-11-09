package com.an.maphouse.util;

import com.an.maphouse.MapHouseApplicationTests;
import com.an.maphouse.entity.User;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * @author Annan An
 * @date 2020/5/11 11:22
 */
class RedisUtilTest extends MapHouseApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void set() {
        User user = new User();
        user.setName("Annan");
        user.setEmail("496900123@qq.com");
        user.setPhoneNumber("13912345678");
        boolean result = redisUtil.set("hello", user);
        Assert.isTrue(result, "redis插入数据失败");
    }

    @Test
    void hasKey() {
        boolean hasKey = redisUtil.hasKey("52cbc2725f1243da8132e741984f0289");
        Assert.isTrue(hasKey, "redis key不存在");
    }

    @Test
    void hashSet(){
        User user = new User();
        user.setNickName("测试人员");
        user.setId(12345L);
    }

    @Test
    void zSetIsMember(){
        String key = "star:user:14";
        boolean isExist = redisUtil.zSetIsMember(key, 50);
        Assert.isTrue(isExist, "用户14收藏了房源50");
        isExist = redisUtil.zSetIsMember(key, 60);
        Assert.isTrue(isExist, "用户14未收藏了房源60");
    }
}