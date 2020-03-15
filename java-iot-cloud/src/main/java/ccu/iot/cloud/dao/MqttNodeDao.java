package ccu.iot.cloud.dao;


import ccu.iot.cloud.entity.MqttNode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MqttNodeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MqttNode record);

    int insertSelective(MqttNode record);

    MqttNode selectByPrimaryKey(Integer id);

    List<MqttNode> selectBySelective(MqttNode mqttNode);

    int updateByPrimaryKeySelective(MqttNode record);

    int updateByPrimaryKey(MqttNode record);
}