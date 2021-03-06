package com.an.maphouse.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.util.Date;

/**
 * 房屋实体类
 * @author Annan An
 * @date 2020/5/9 14:14
 */
@Entity
@Data
@Table(name = "tbl_house")
@DynamicUpdate
@DynamicInsert
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer price;

    private Integer area;

    private Integer room;

    private String floor;

    private String totalFloor;

    private Integer watchTimes;

    private Integer buildYear;

    private Integer status;

    private Date createTime;

    private Date lastUpdateTime;

    private String cityEnName;

    private String regionEnName;

    private String cover;

    /* 房屋朝向: 1:东 2:南 3:西  4:北 */
    private Integer direction;

    /* 到地铁的距离 */
    private Integer distanceToSubway;

    /** 客厅数量 **/
    private Integer parlour;

    /* 小区名称 */
    private String district;

    /* 所属管理员id */
    private Long adminId;

    private Integer bathroom;

    /* 街道 */
    private String street;


}
