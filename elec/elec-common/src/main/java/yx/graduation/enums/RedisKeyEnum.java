package yx.graduation.enums;

import lombok.Data;

public enum RedisKeyEnum {

    USER_DEVICE("user_device"),
    PARAMS("params"),
    CATS("cats"),
    PARAM_KV("param"),  //name-unit
    DEVICE_KV("device"); //id-name

    public String value;

    RedisKeyEnum( String value) {
        this.value = value;
    }
}
