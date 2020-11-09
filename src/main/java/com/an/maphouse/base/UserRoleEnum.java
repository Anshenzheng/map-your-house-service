package com.an.maphouse.base;


/**
 *  用户角色枚举
 * @author Annan An
 * @date 2020/5/22 15:46
 */

public enum  UserRoleEnum {
    ADMIN("ADMIN", "管理员"),
    USER("USER", "用户"),;

    private String value;

    private String message;

    UserRoleEnum(String value, String message) {
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
