package ccu.iot.cloud.service;

public interface MqttCliService {
    Boolean updateCliInfo(String clientId, String clientName, String remark);
}
