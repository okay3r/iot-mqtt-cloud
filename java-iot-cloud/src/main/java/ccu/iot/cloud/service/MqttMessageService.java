package ccu.iot.cloud.service;

import ccu.iot.cloud.entity.MqttMessage;

import java.util.List;

public interface MqttMessageService {
    List<MqttMessage> queryMqttMsgHistory();
}
