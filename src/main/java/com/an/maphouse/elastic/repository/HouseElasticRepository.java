package com.an.maphouse.elastic.repository;

import com.an.maphouse.elastic.entity.HouseElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 *  房源elastic dao
 * @author Annan An
 * @date 2020/5/19 18:38
 */
public interface HouseElasticRepository extends ElasticsearchRepository<HouseElastic, Long> {

}
