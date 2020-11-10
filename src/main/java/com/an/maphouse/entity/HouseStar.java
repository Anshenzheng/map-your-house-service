package com.an.maphouse.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Annan An
 * @date 2020/7/27 16:41
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert
@Table(name="tbl_house_star")
public class HouseStar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long houseId;

    private Long userId;

    private Date createTime;

    private Date lastUpdateTime;

}
