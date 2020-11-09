package com.an.maphouse.base;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Annan An
 * @date 2020/7/27 11:33
 */
public enum  AggTypeEnum {

    REGION("region", "区县"),
    DISTRICT("region", "小区"),
        ;

    private String value;

    private String message;

    public static Optional<AggTypeEnum> fromValue(String value){
        return Arrays.stream(AggTypeEnum.values()).filter(item -> StringUtils.equals(item.getValue(), value)).findFirst();
    }

    AggTypeEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
