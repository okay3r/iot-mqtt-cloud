package ccu.iot.cloud.dao;


import ccu.iot.cloud.entity.MqttMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    List<MqttMessage> selectByTimeLimit(@Param("start") Date start, @Param("end") Date end);

    List<MqttMessage> selectMsgByVague(String topic);
}