package com.an.maphouse.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 出租方式枚举
 * @author Annan An
 * @date 2020/5/14 11:42
 */
@AllArgsConstructor
@Getter
public enum HouseTypeEnum {

    SHARE(0, "二手房"),
    WHOLE(1, "新房");

    private int value;

    private String message;

    public static Optional<HouseTypeEnum> fromValue(int value){
        return Arrays.stream(HouseTypeEnum.values()).filter(item -> item.getValue() == value).findFirst();
    }
}
