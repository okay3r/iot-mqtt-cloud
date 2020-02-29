package ccu.iot.cloud.service;

import ccu.iot.cloud.entity.MqttMessage;

import java.util.Date;
import java.util.List;

public interface MqttMessageService {
    List<MqttMessage> queryMqttMsgHistory();

    List<MqttMessage> queryMqttMsgByTimeLimit(Date start, Date end);

    List<MqttMessage> queryMqttMsgByTopic(String topic);
}
