package ccu.iot.cloud.dao;


import ccu.iot.cloud.entity.MqttTopic;

import java.util.List;

public interface MqttTopicDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MqttTopic record);

    int insertSelective(MqttTopic record);

    MqttTopic selectByPrimaryKey(Integer id);

    List<MqttTopic> selectBySelective(MqttTopic mqttTopic);

    int updateByPrimaryKeySelective(MqttTopic record);

    int updateByPrimaryKey(MqttTopic record);
}