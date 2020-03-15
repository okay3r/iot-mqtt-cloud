package ccu.iot.cloud.service;

import ccu.iot.cloud.entity.MqttMessage;

import java.util.Date;
import java.util.List;

public interface MqttMessageService {
    List<MqttMessage> queryAll();

    List<MqttMessage> queryByTime(Date start, Date end);

    List<MqttMessage> queryByTopic(String topic);
}
