package com.an.maphouse.repository;

import com.an.maphouse.MapHouseApplicationTests;
import com.an.maphouse.entity.SupportAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * @author Annan An
 * @date 2020/5/11 15:07
 */
class SupportAddressRepositoryTest extends MapHouseApplicationTests {

    @Autowired
    private SupportAddressRepository supportAddressRepository;

    @Test
    void findAllByEnNameIn() {
        List<SupportAddress> list = supportAddressRepository.findAllByEnNameIn(Arrays.asList("bj", "dcq"));
        Assert.isTrue(list.size() == 2, "查询的区域结果不符和");
    }
}