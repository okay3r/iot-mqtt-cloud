package ccu.iot.cloud.dao;


import ccu.iot.cloud.entity.MqttNode;

import java.util.List;

public interface MqttNodeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MqttNode record);

    int insertSelective(MqttNode record);

    MqttNode selectByPrimaryKey(Integer id);

    List<MqttNode> selectBySelective(MqttNode mqttNode);

    int updateByPrimaryKeySelective(MqttNode record);

    int updateByPrimaryKey(MqttNode record);
}