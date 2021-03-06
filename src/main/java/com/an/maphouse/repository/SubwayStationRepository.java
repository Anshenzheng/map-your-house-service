package com.an.maphouse.repository;

import com.an.maphouse.entity.SubwayStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  地铁站dao
 * @author Annan An
 * @date 2020/5/9 10:06
 */
public interface SubwayStationRepository  extends JpaRepository<SubwayStation, Long> {

    /**
     * 通过地铁线路id查找地铁站
     * @param lineId 地铁线路id
     * @return 地铁站列表
     */
    List<SubwayStation> getAllByLineIdLike(String lineId);

    /**
     * 查询所有在id列表中的地铁
     * @param idList id列表
     */
    List<SubwayStation> getAllByIdIn(List<Long> idList);

}
