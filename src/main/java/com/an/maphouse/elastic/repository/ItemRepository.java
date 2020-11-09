package com.an.maphouse.elastic.repository;

import com.an.maphouse.elastic.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Annan An
 * @date 2020/5/20 10:09
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

}
