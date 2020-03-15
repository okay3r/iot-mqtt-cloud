package ccu.iot.cloud.service;

import ccu.iot.cloud.entity.mqttapi.CurrentMqttSub;
import ccu.iot.cloud.entity.mqttapi.PublishEntity;

import java.util.List;

public interface MqttApiService {

    List<CurrentMqttSub> queryCurrentSubscriptions() throws Exception;

    Boolean publish(String username, PublishEntity publishEntity);
}
