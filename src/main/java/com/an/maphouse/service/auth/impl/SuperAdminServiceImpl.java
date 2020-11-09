package com.an.maphouse.service.auth.impl;

import com.an.maphouse.service.cache.RedisStarService;
import com.an.maphouse.entity.HouseStar;
import com.an.maphouse.repository.HouseRepository;
import com.an.maphouse.repository.HouseStarRepository;
import com.an.maphouse.service.auth.SuperAdminService;
import com.an.maphouse.service.cache.RedisStarService;
import com.an.maphouse.service.house.HouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 超级管理员服务实现
 * @author Annan An
 * @date 2020/9/4 10:49
 */
@Service
public class SuperAdminServiceImpl implements SuperAdminService {

    @Resource
    private RedisStarService redisStarService;
    

    @Override
    public void syncStarToRedisFromDatabase() {
        redisStarService.syncStarFromDatabase();
    }

    @Override
    public void syncStarToDatabaseFromRedis() {
        redisStarService.syncStarToDatabase();
    }
}
