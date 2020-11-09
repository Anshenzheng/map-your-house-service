package com.an.maphouse.base;

import com.an.maphouse.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Annan An
 * @date 2020/5/13 9:42
 */

public enum  HouseOperationEnum {
    PULL_OUT(0, "下架/重新审核"),
    PASS(1, "审核通过"),
    SALE(2, "在售"),
    DELETE(3, "删除");

    private int code;

    private String message;

    public static HouseOperationEnum of(Integer code){
        Optional<HouseOperationEnum> operateOptional = new ArrayList<HouseOperationEnum>(Arrays.asList(HouseOperationEnum.values()))
                .stream().filter(item -> item.code == code).findFirst();
        return operateOptional.orElseThrow(() -> new BusinessException(ApiResponseEnum.UNSUPPORTED_OPERATION));
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    HouseOperationEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
