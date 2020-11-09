package com.an.maphouse.repository;

import com.an.maphouse.entity.Subway;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *  地铁线路dao
 * @author Annan An
 * @date 2020/5/9 10:05
 */
public interface SubwayRepository extends JpaRepository<Subway, Long> {

    /**
     * 通过城市拼音缩写查找所有地铁线路
     * @param cityEnName 城市拼音缩写
     * @return 地铁线路列表
     */
    List<Subway> findAllByCityEnName(String cityEnName);
}
