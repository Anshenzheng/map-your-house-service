package com.an.maphouse.repository;

import com.an.maphouse.MapHouseApplicationTests;
import com.an.maphouse.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * @author Annan An
 * @date 2020/5/7 16:17
 */

public class UserRepositoryTest extends MapHouseApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void  findOneTest(){
        User user = userRepository.findById(2L).orElse(null);
        Assert.notNull(user, "用户为空");
        Assert.isTrue("admin".equals(user.getName()), "id为2的用户名称不为admin");
    }
}