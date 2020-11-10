package com.an.maphouse.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 地铁线路实体类
 * @author Annan An
 * @date 2020/5/9 10:02
 */
@Entity
@Data
@Table(name="tbl_subway_line")
public class Subway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityId;
    private String cityName;
    private String lineId;
    private String lineName;
    private String cityEnName;
}
