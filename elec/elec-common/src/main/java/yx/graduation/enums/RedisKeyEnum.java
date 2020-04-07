package yx.graduation.enums;

import lombok.Data;

public enum RedisKeyEnum {

    DEVICE_LIST("deviceList"),
    PARAMS("params"),
    CATS("cats"),
    PARAM_KV("param"),  //name-unit
    DEVICE_KV("device"), //id-name
    ALARM_UP("alarmUp"),
    ALARM_DOWN("alarmDown"),
    HAS_ALARM("hasAlarm"),
    CUR_VALUE("curValue");

    public String value;

    RedisKeyEnum(String value) {
        this.value = value;
    }
}
