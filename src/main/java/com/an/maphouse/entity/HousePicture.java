package com.an.maphouse.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 房屋图片dao
 * @author Annan An
 * @date 2020/5/9 14:27
 */
@Entity
@Data
@Table(name="tbl_house_picture")
public class HousePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long houseId;

    /* cdn图片url */
    private String cdnPrefix;

    private Integer width;

    private Integer height;

    /* 图片文件位置 */
    private String path;

}
