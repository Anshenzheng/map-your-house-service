package com.an.maphouse.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 地铁站实体类
 * @author Annan An
 * @date 2020/5/9 10:04
 */
@Entity
@Data
@Table(name="tbl_subway_station")
public class SubwayStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityId;
    private String cityName;
    private String stationId;
    private String name;
    private String longitude;
    private String latitude;
    private String lineId;

}
