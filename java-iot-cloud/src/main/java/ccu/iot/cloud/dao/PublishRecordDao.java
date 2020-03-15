package ccu.iot.cloud.dao;

import ccu.iot.cloud.entity.MqttMessage;
import ccu.iot.cloud.entity.PublishRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface PublishRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(PublishRecord record);

    int insertSelective(PublishRecord record);

    PublishRecord selectByPrimaryKey(Long id);

    List<PublishRecord> selectBySelective(PublishRecord publishRecord);

    int updateByPrimaryKeySelective(PublishRecord record);

    int updateByPrimaryKey(PublishRecord record);

    List<PublishRecord> selectByTimeLimit(@Param("start") Date start, @Param("end") Date end);

    List<PublishRecord> selectPubByVague(String topic);
}