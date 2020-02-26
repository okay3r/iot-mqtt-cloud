package ccu.iot.cloud.dao;


import ccu.iot.cloud.entity.MqttMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MqttMessageDao {
    int deleteByPrimaryKey(Long id);

    int insert(MqttMessage record);

    int insertSelective(MqttMessage record);

    MqttMessage selectByPrimaryKey(Long id);

    List<MqttMessage> selectBySelective(MqttMessage mqttMessage);

    int updateByPrimaryKeySelective(MqttMessage record);

    int updateByPrimaryKey(MqttMessage record);
}