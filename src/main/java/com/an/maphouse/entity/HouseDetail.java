package com.an.maphouse.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 房屋详情实体类
 * @author Annan An
 * @date 2020/5/9 14:22
 */
@Entity
@Data
@Table(name="tbl_house_detail")
public class HouseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    /* 户型介绍 */
    private String layoutDesc;

    /* 交通出行介绍 */
    private String traffic;

    /* 周边配套设施 */
    private String roundService;

    /* 出租方式, 1:新房  2: 二手房 */
    private Integer houseType;

    private String address;

    /* 地铁线路id */
    private Long subwayId;

    /* 地铁线路名称 */
    private String subwayLineName;

    private Long subwayStationId;

    private String subwayStationName;

    private Long houseId;
}
