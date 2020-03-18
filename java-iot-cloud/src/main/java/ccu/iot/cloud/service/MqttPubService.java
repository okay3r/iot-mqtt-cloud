package ccu.iot.cloud.service;

import ccu.iot.cloud.entity.MqttPub;

import java.util.Date;
import java.util.List;

public interface MqttPubService {
    List<MqttPub> queryAll();

    List<MqttPub> queryByTime(Date start, Date end);

    List<MqttPub> queryByTopic(String topic);
}
