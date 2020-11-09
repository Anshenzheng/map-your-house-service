package com.an.maphouse.repository;

import com.an.maphouse.entity.HouseDetail;
import com.an.maphouse.entity.HouseStar;
import com.an.maphouse.entity.HouseSubscribe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Annan An
 * @date 2020/7/27 17:07
 */
public interface HouseStarRepository  extends JpaRepository<HouseStar, Long> {

    Page<HouseStar> findAllByUserId(Long userId, Pageable pageable);

    int countByHouseId(Long userId);

    void deleteByHouseIdAndUserId(Long houseId, Long userId);

    boolean existsByHouseIdAndUserId(Long houseId, Long userId);

    void deleteAllByIdIn(List<Long> idList);

}
