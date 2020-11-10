package com.an.maphouse.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Annan An
 * @date 2020/5/9 14:13
 */
@Entity
@Data
@Table(name="tbl_house_tag")
public class HouseTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long houseId;

    private String name;
}
