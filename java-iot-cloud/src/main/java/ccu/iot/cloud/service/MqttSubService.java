package ccu.iot.cloud.service;

import ccu.iot.cloud.entity.MqttSub;

import java.util.Date;
import java.util.List;

public interface MqttSubService {
    List<MqttSub> queryAll();

    List<MqttSub> queryByTime(Date start, Date end);

    List<MqttSub> queryByTopic(String topic);
}
