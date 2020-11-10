package com.an.maphouse.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 角色实体
 * @author Annan An
 * @date 2020/5/8 14:31
 */
@Entity
@Data
@Table(name="tbl_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long userId;

}
