package ccu.iot.cloud.service;

import ccu.iot.cloud.entity.ClientTopicInfo;
import ccu.iot.cloud.entity.mqttapi.PublishEntity;

import java.util.List;

public interface MqttApiService {

    List<ClientTopicInfo> queryCurrentSubscriptions() throws Exception;

    Boolean publish(String username, PublishEntity publishEntity);
}
