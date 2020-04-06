package ccuiot.iotc.enums;

public enum RedisKeyEnum {
    USER_KEY("user"),
    CLIENT_INFO_NAME("clientInfoName"),
    CLIENT_INFO_REMARK("clientInfoRemark");

    public String value;

    RedisKeyEnum(String value) {
        this.value = value;
    }
}
